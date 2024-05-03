package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("Comment post Service in");
		int isOK = cdao.post(cvo);
		return isOK;
	}

	@Override
	public PagingHandler list(int bno, PagingVO pgvo) {
		log.info("Comment list Service in");
		List<CommentVO> list = cdao.list(bno, pgvo); 
		
		// cmtList ph 객체 안에 삽입
		// totalCount 구해오기
		int totalCount = cdao.getSelectOneBnoTotalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph; 
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("Comment modify Service in");
		return cdao.modify(cvo);
	}

	@Override
	public int delete(int cno) {
		log.info("Comment modify Service in");
		return cdao.delete(cno);
	}

	@Override
	public int modify2(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.modify2(cvo);
	}

	@Override
	public int delete2(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete2(cno);
	}
	
}
