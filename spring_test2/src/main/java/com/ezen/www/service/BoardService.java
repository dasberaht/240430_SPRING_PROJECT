package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO bdto);

	List<BoardVO> list(PagingVO pgvo);

	BoardVO detail(int bno);

	void modify(BoardVO bvo);

	void delete(int bno);

	int getTotal(PagingVO pgvo);







}
