package com.ezen.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class PagingVO {

	// select * from board limit 0, 10;		0, 10의 데이터를 가져올 매개변수 생성
	// 페이징 > pageNo / qty
	// 검색 > type / keyword
	
	private int pageNo;
	private int qty;	// 한 화면에 보여줄 게시글 수(10개)
	
	private String type;		// 검색유형(제목, 작성자, 내용)
	private String keyword;		// 검색어
	
	public PagingVO() {
		this.pageNo = 1;	// 기본값
		this.qty = 10;		// 기본값
	}
	
	// 별도 getter 생성(페이지네이션)
	public int getPageStart() {
		// DB상 limit의 시작번지를 구하는 메서드
		// 1page > 0부터 시작
		// 2page > 10 ~
		// 3page > 20 ~
		return (this.pageNo-1)*qty;
	}
	
	
	// 여러 type을 같이 검색하기 위해, type을 배열로 구분
	// ex. {tcw} > {t} {c} {w}
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
		
	
}
