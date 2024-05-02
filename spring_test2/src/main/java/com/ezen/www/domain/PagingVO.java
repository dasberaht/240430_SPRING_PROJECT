package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class PagingVO {

	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	
	// 기본생성자 : 아무 값도 없을 경우, 기본페이지넘버 = 1 / 페이지당 표시 글 수 = 10
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	// DB에서 사용될 시작번지 구하기
	// select * from board limit 번지, 개수(10개) >>> 번지 : 0부터 시작하므로, 0번지부터 10개~ 라는 의미
	// ex. 1page >>> limit 0, 10 / 2page >>> limit 10, 10 ...
	public int getPageStart() {
		return (this.pageNo - 1) * this.qty;		// 시작번지 구하는 공식
	}
	
	
	// type의 복합타입을 배열로 생성
	// 복합타입의 키워드일 경우, 각각 검색되기 위해 '배열'로 생성
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
	
	// 댓글 관련
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	
	
	
	
}
