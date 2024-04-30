package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {

//	- list 화면 하단의 페이지 조작부 기능을 담당하는 변수들을 생성
//	- 시작페이지 번호 / 끝페이지 번호 (*한 페이지에서 보여지는 페이지 네이션)
//	- 최종 끝번호
//	- 이전, 다음버튼의 생성 여부 조건
//	> 전체 게시글 수, 현재 페이지 번호, 한 페이지에 들어갈 게시글 수를 매개변수로 받기
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int totalCount;
	private int realEndPage;
	
	// 매개변수로 값을 받기 위함
	private PagingVO pgvo;
	
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		// controller에서 받아서 넣기
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// 1page : 1~10 / 2page : 11~20 / 3page : 21~30 ...
		// 페이징 규칙 : pageNo > 1 / 10 = 0.1(올림) > '1' * 10 > '10'
		// pageNo > 11 / 10 = 1.1(올림) > '2' * 10 > '20' 
		// (단, 정수/정수 = 정수 이므로, 소수점을 살려서 처리)
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10)*10;
		this.startPage = endPage - 9;
		
		// 최종페이지
		// 만약, 총 게시글이 103개일 경우 > 103 / 10 = 10.3(올림) > '11'페이지 
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		
		this.prev = this.startPage > 1;		// 1 > 11 > 21 ...
		this.next = this.endPage < realEndPage;
		
		
	}
	
	
	
	
	
	
}
