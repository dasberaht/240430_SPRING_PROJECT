package com.ezen.test.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoardDTO {

	// board = 1 / file = n..
	private BoardVO bvo;
	private List<FileVO> flist;
	
}
