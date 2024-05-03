package com.ezen.www.handler;

import java.util.List;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private int realEndPage;
	
	private List<CommentVO> cmtList;
	
	
	// 생성자의 모든 값들이 계산되어 설정되어야하므로, 기본생성자(임의생성자)를 넣지 않는다.
	// 리스트 관련
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// endPage는 10, 20, 30.. 의 형태로 값을 설정한다.
		// 따라서, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10을 클릭하더라도 값을 10으로 고정 >>> 11부터는 20으로 고정 >>> 21부터는 30으로 고정...
		// 공식 : (int) 1/10(double) = 0.1을 올림처리(Math.ceil) >>> 1 * 10
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(double)10)*10;
		this.startPage = endPage - 9;
		
		// 최종페이지
		// 전체 게시글 수 / 한 페이지에 표시되는 게시글 수의 값을 올림처리
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		// ex. realEndPage = 28page >>> 29, 30page는 출력되지 않도록 설정
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		// prev, next 설정
		this.prev = startPage > 1;
		this.next = endPage < realEndPage;		
	}
	
	
	// 댓글 관련(댓글 비동기처리로 인한 생성자 생성)
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO>cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	
}
