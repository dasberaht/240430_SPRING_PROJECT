package com.ezen.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/comment/*")
@RequiredArgsConstructor
@Slf4j
@RestController
public class CommentController {

	private final CommentService csv;
		
	
	// consumes : 들어오는 값, produces : 나가는 값
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info("> Comment post cvo {}", cvo);
		int isOk = csv.post(cvo);				
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno")int bno, @PathVariable("page")int page){
		log.info("> Comment list bno {}", bno);
		// pagingVO, PagingHandler 사용
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = csv.list(bno, pgvo);
		
		// 리스트만 작업했을 경우
		// List<CommentVO> list = csv.list(bno);
		
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}
	
//	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
//		log.info("> Comment modify cvo {}", cvo);
//		int isOk = csv.modify(cvo);
//		
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
//	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> delete(@PathVariable("cno")int cno){
//		log.info("> Comment delete cno {}", cno);
//		int isOk = csv.delete(cno);
//		
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	
	// responseBody 사용방식(body만 보낼 때)
	@ResponseBody
	@PutMapping("/modify")
	public String modify(@RequestBody CommentVO cvo) {
		log.info("> Comment modify cvo {}", cvo);
		int isOk = csv.modify2(cvo);
		return isOk > 0 ? "1" : "0";
	}
	
	@ResponseBody
	@DeleteMapping("/{cno}")
	public String delete(@PathVariable("cno")int cno) {
		int isOk = csv.delete2(cno);
		return isOk > 0 ? "1" : "0";
	}
	
	
	
	
	
}
