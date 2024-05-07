package com.ezen.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ezen.www.domain.FileVO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class FileSweeper {

	// 직접 DB에 접속해서, DB데이터를 가져오도록 처리
	
	private final FileDAO fdao;
	
	private final String BASE_PATH = "D:\\W\\Spring\\_myProject\\_java\\_fileUpload\\";
	
	
	// 매일 정해진 시간에 스케쥴러 실행
	// 매일 등록된 파일과 DB <-> 해당 날짜에 저장폴더 내의 파일이 DB와 일치하는 파일은 남기고, 일치하지 않는 파일은 삭제 처리
	// cron 방식 : 초 분 시 일 월 요일 년도(생략가능) *참고 : https://zamezzz.tistory.com/197
	@Scheduled(cron = "0 37 14 * * *")
	public void fileSweeper() {
		log.info("> FileSweeper Running Strart {}", LocalDateTime.now());
		
		// DB에 등록된 파일목록 가져오기
		List<FileVO> dbList = fdao.selectListAllFile();
		
		// 저장폴더를 검색할 때, 필요한 파일경로 리스트(실제 존재해야하는 리스트)
		List<String> currFiles = new ArrayList<String>();		// 파일에 대한 이름/경로를 갖는 리스트
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir()+File.separator+fvo.getUuid();
			log.info("> filePath {}", filePath);
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);
			
			// 이미지 파일이라면, 썸네일 파일도 지우도록 처리
			if(fvo.getFileSize() > 0) {
				currFiles.add(BASE_PATH+filePath+"_th_"+fileName);
			}	
		}
		log.info("> currFiles {}", currFiles);
		
		// 오늘 날짜를 반영한 폴더구조 경로 생성
		LocalDate now = LocalDate.now();
		String today = now.toString();
		
		today = today.replace("-", File.separator);
		log.info("> folder today {}", today);
		
		// (오늘날짜)경로를 기반으로 저장되어있는 파일을 검색
		File dir = Paths.get(BASE_PATH+today).toFile();
		// listFiles() : 해당 경로에 있는 모든파일을 '배열'로 리던
		// allFileObject : 실제 저장되어있는 파일
		File[] allFileObject = dir.listFiles();
		log.info("> allFileObject {}", allFileObject.toString());
		
		// `저장되어있는 파일`과 `DB`에 존재하는 파일을 비교하여, 없는 파일은 삭제 처리
		for(File file : allFileObject) {
			String storedFileName = file.toPath().toString();
			
			// 비교 시 없으면 삭제
			if(!currFiles.contains(storedFileName)) {
				file.delete();	// 파일삭제
				log.info("> delete files {}", storedFileName);
			}
		}
		
		
		
		log.info("> FileSweeper Running End {}", LocalDateTime.now());
	}
	
}
