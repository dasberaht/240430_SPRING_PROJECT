package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Transactional	// 해당하는 값이 ok가 되어야지만, 다음 값을 처리하는 기능
	@Override
	public int insert(BoardDTO bdto) {
		log.info(">> Board insert Service in !!!");
		// bvo 저장 후 bno set한 후 FileVO에 저장
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist() == null) {
			return isOk;
		}
		if(isOk>0 && bdto.getFlist().size()>0) {
			// bno setting
			int bno = bdao.selectOneBno();	// 가장 마지막에 등록된 bno 처리
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
				
	}
//	@Override
//	public void insert(BoardVO bvo) {
//		log.info(">> Board insert Service in !!!");
//		bdao.insert(bvo);		
//	}

	@Override
	public List<BoardVO> list(PagingVO pgvo) {
		log.info(">> Board insert Service in !!!");
		return bdao.list(pgvo);
	}

	@Transactional			// 둘 중 하나라도 오류가 발생할 경우, 처리가 되지않도록 설정하는 기능
	@Override
	public BoardDTO detail(int bno) {
		log.info(">> Board detail Service in !!!");
		
		// bvo, flist를 묶어서 DTO를 리턴
		BoardVO bvo = bdao.detail(bno);
		List<FileVO> flist = fdao.getList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		
		
		
		return bdto;
	}

	@Override
	public void modify(BoardDTO bdto) {
		log.info(">> Board modify Service in !!!");
		int isOk = bdao.modify(bdto.getBvo());
		
		//파일 수정
		if(bdto.getFlist() == null) {
			return;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		
	}

	@Override
	public void delete(int bno) {
		log.info(">> Board delete Service in !!!");
		bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">> Board Total Service in !!!");
		return bdao.getTotal(pgvo);
	}

	@Override
	public int deleteFile(String uuid) {
		log.info(">> Board deleteFile Service in !!!");
		return fdao.deleteFile(uuid);
	}




}
