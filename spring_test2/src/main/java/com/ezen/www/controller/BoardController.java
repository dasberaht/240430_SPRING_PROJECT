package com.ezen.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
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
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo) {	
		bsv.insert(bvo);	
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
		BoardVO bvo = bsv.detail(bno);
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		bsv.modify(bvo);
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno")int bno) {
		bsv.delete(bno);
		return "redirect:/board/list";
	}
	
	
	
}
