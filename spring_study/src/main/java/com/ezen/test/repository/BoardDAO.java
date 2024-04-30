package com.ezen.test.repository;

import java.util.List;


import com.ezen.test.domain.BoardVO;

import com.ezen.test.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void remove(int bno);

	void updateReadCount(int bno);

	int getTotalCount(PagingVO pgvo);

	int selectBno();

	void cmtCountUpdate();

	void fileCountUpdate();

	int cmtCount(int bno);
	
//	int cmtCount(@Param('bno')int bno, @Param('cnt')int cnt);








	

}
