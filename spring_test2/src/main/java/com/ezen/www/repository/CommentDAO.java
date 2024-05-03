package com.ezen.www.repository;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;



public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> list(@Param("bno")int bno, @Param("pgvo")PagingVO pgvo);

	int getSelectOneBnoTotalCount(int bno);

	int modify(CommentVO cvo);

	int delete(int cno);

	int modify2(CommentVO cvo);

	int delete2(int cno);

	





	
	
}
