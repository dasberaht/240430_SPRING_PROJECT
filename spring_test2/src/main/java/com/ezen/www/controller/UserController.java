package com.ezen.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public void register() {
	}

	@PostMapping("/register")
	public String register(UserVO uvo, HttpServletRequest request, RedirectAttributes re) {
		log.info("> UserController register uvo {}", uvo);
		UserVO uvo2 = usv.getUserEmail(uvo.getEmail());		
		// email 중복
		if(uvo2!=null) {
			return "redirect:/user/register"; 
		}
		 
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		int isOk = usv.register(uvo);

		return "index";
	}

	@GetMapping("/login")
	public void login() {
	}

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
	public void modify() {
	}

	@PostMapping("/modify")
	public String modify(UserVO uvo, HttpServletRequest request, HttpServletResponse response) {
		usv.modify(uvo);
		logout(request, response);
		return "redirect:/";
	}

	// 로그아웃 관련(내부 호출용으로 mapping X)
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Authentication객체를
																								// SecurityContext에 저장하기
																								// 때문에, 이를 가져와서 사용
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}

	@GetMapping("/delete")
	public String delete(Principal princ, HttpServletRequest request, HttpServletResponse response) {
		log.info(">>>>>>>>>> User princ {}", princ);
		usv.delete(princ.getName());
		logout(request, response);
		return "redirect:/";
	}

	@GetMapping(value = "/chkemail/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> chkemail(@PathVariable("email") String email) {
		UserVO usvo = usv.chkemail(email);
		return usvo == null ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
