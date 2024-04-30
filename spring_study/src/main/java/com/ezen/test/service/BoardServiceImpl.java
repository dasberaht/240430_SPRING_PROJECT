package com.ezen.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;
import com.ezen.test.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
//	@Inject
	private final BoardDAO bdao;
	
	// fileDAO 생성하여, 파일처리를 진행
	private final FileDAO fdao;
	
	
	@Override
	public int insert(BoardDTO bdto) {
		log.info(">> board insert Service in");
		int isOk = bdao.insert(bdto.getBvo());
		
		//file 처리(FileDAO 생성) >>> bno 값이 없는 상태
		if(bdto.getFlist() == null) {
			return isOk;
		}else {
			// 파일이 있으므로, 저장 처리
			if(isOk > 0 && bdto.getFlist().size() > 0) {
				// bno 값이 없는 상태 >>> insert를 통해 자동생성이 되기 때문에, DB에서 검색해 가져온다.
				int bno = bdao.selectBno();
				
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					// 파일 저장 : 리스트로 가져오기 때문에 하나씩 저장처리
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">> board list Service in");
				
		return bdao.getList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {
		log.info(">> board detail Service in");
		
		// read_count 증가
		bdao.updateReadCount(bno);
		

		// detail.jsp에 file 출력 처리
		BoardDTO bdto = new BoardDTO();
		BoardVO bvo = bdao.getDetail(bno);		// 기존에 처리된 bvo 객체(detail.jsp 출력을 위한 객체) 
		bdto.setBvo(bvo);
		bdto.setFlist(fdao.getFilelist(bno));	// bno에 해당하는 모든 파일 리스트 검색
		
		return bdto; 
	}

	@Override
	public void update(BoardDTO bdto) {
		log.info(">> board update Service in");
		// 파일 추가 작업
		// bvo >> boardMapper / flist >> fileMapper
		// 게시글 수정 
		int isOk = bdao.update(bdto.getBvo());
		// 파일 값이 없다면, 리턴
		if(bdto.getFlist() == null) {
			return;
		}
		// bvo가 업데이트 완료 후 파일도 있다면, 파일 추가 처리
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			// bno setting 작업
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);		// 기존 isertFile 사용
			}
		}
		
		
	}

	@Override
	public void remove(int bno) {
		log.info(">> board remove Service in");
		bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info(">> board getTotalCount Service in");
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		log.info(">> board removeFile Service in");
		return fdao.removeFile(uuid);
	}

	@Override
	public void cmtFileUpdate() {
		log.info(">> board cmtFileUpdate Service in");
		bdao.cmtCountUpdate();
		bdao.fileCountUpdate();
	}







	
}