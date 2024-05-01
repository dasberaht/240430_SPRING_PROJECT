package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bdao;

	@Override
	public void insert(BoardVO bvo) {
		log.info(">> Board insert Service in !!!");
		bdao.insert(bvo);		
	}

	@Override
	public List<BoardVO> list(BoardVO bvo) {
		log.info(">> Board insert Service in !!!");
		return bdao.list(bvo);
	}

	@Override
	public BoardVO detail(int bno) {
		log.info(">> Board detail Service in !!!");
		return bdao.detail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info(">> Board modify Service in !!!");
		bdao.modify(bvo);
	}

	@Override
	public void delete(int bno) {
		log.info(">> Board delete Service in !!!");
		bdao.delete(bno);
	}




}
