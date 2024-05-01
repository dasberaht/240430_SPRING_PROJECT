package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardService {

	void insert(BoardVO bvo);

	List<BoardVO> list(BoardVO bvo);

	BoardVO detail(int bno);

	void modify(BoardVO bvo);

	void delete(int bno);







}
