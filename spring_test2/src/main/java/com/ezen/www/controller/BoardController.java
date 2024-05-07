package com.ezen.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService bsv;
	private final FileHandler fh;
	
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo, @RequestParam(name="files", required=false)MultipartFile[] files) {	
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			// 파일이 있다면, FileHandler~
			flist = fh.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.insert(bdto);	
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {			// BoardVO bvo >>> PagingVO pgvo로 변경
		log.info("> board list pgvo {}", pgvo);
		List<BoardVO> list = bsv.list(pgvo);
		// 가져온 리스트를 >>> /board/list.jsp로 전달
		m.addAttribute("list", list);

		
		// totalCount 구해오기(pgvo는 검색 시 사용해야하므로 미리 설정)
		int totalCount = bsv.getTotal(pgvo);
		
		// PagingHandler 설정
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("ph", ph);
				
		
		return "/board/list";
	}
		
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno) {
		BoardDTO bdto = bsv.detail(bno);
		m.addAttribute("bdto", bdto);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name="files", required=false) MultipartFile[] files) {
		// 파일 수정업로드
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fh.uploadFiles(files);
		}
		
		
		BoardDTO bdto = new BoardDTO(bvo, flist);
		// 게시글 수정
		bsv.modify(bdto);
		
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno")int bno) {
		bsv.delete(bno);
		return "redirect:/board/list";
	}
	
//	@DeleteMapping(value = "/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> deleteFile(@PathVariable("uuid")String uuid){
//		int isOk = bsv.deleteFile(uuid);
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@DeleteMapping("/file/{uuid}")
	@ResponseBody
	public String deleteFile(@PathVariable("uuid")String uuid) {
		int isOk = bsv.deleteFile(uuid);
		return isOk > 0 ? "1" : "0";
	}
	
	
	
}
