package com.ezen.www.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
}
