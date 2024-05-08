package com.ezen.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/**")
@Controller
public class UserController {

	private final UserService usv;
	private final BCryptPasswordEncoder bcEncoder;
	
	
	// Controller mapping명과 경로가 같으면, void 가능(return값 생략 가능)
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(UserVO uvo) {
		log.info("> UserController register uvo {}", uvo);
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		int isOk = usv.register(uvo);
		
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		// 로그인 실패 시 다시 로그인 페이지로 돌아와서, 오류메세지를 전송하는 역할 및 재로그인을 유도하는 역할
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
				
			log.info(">>> errMsg {}", request.getAttribute("errMsg").toString());
		
		return "redirect:/user/login";
	}
	
		
	@GetMapping("/userlist")
	public String userlist(Model m) {
		List<UserVO> userList = usv.getList();
		m.addAttribute("userList", userList);
				
		return "/user/userlist";
	}
	/*
	 * @GetMapping("/userlist") public String userlist(Model m, UserVO uvo) {
	 * List<UserVO> list = usv.selectUserList(uvo); m.addAttribute("list", list);
	 * 
	 * return "/user/userlist"; }
	 */	
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(UserVO uvo) {
		usv.modify(uvo);
		return "/user/modify";
	}
	
	
	
	
}
