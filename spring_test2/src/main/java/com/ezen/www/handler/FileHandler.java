package com.ezen.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component	// 사용자 클래스를 빈으로 등록
public class FileHandler {

	private final String UP_DIR="D:\\W\\Spring\\_myProject\\_java\\_fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<FileVO>();
		// FileVO 생성 >>> 파일 저장 >>> 썸네일 저장
		// 날짜로 폴더를 생성하여, 업로드 파일을 관리하도록 처리
		LocalDate date = LocalDate.now();	// 오늘날짜 값 : 2024-05-03
		String today = date.toString();		// String으로 형변환
		today = today.replace("-", File.separator);		// "-"를 파일 경로 표시로 변환("-" >>> "\")
		
		// ex. D:\\W\\Spring\\_myProject\\_java\\_fileUpload\\2024\\05\\03의 형태
		File folders = new File(UP_DIR, today);
		// 폴더생성(폴더가 없으면 생성)
		if(!folders.exists()) {
			folders.mkdirs();	// mkdirs() : 여러 폴더를 생성
		}
		
		// files에 대한 객체생성
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			
			// 파일저장 기능
			// 디스크에 파일 저장 >>> 저장 객체생성
			// ex. D:\\W\\Spring\\_myProject\\_java\\_fileUpload\\2024\\05\\03\\uuid_name.jpg
			String fullFileName = uuidStr + "_" + fileName;
			// 실제 파일이 저장되려면, 첫 경로부터 모두 설정되어있어야 저장된다.
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				
				// 이미지만! 썸네일 저장 기능
				// 이미지파일 여부 확인
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);		// 이미지파일의 타입을 1로 설정
					// 썸네일 생성
					File thumbNail = new File(folders, uuidStr + "_th_" + fileName);
					// 썸네일 파일저장
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("파일 저장 오류발생");
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;
	}
	
	
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);		// type : image/jpg...
		return mimeType.startsWith("image") ? true : false;
	}
	
	
}
