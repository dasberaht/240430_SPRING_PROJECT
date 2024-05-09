package com.ezen.www.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserDAO udao;
	
	private final BCryptPasswordEncoder bcEncoder;

	@Transactional
	@Override
	public int register(UserVO uvo) {
		log.info(">> UserService register in");
		int isOK = udao.register(uvo);
		// 권한 추가(Transactional 사용 시 if값을 따로 주지 않아도 된다.)		
		return udao.insertAuthInit(uvo.getEmail()); 
	}

	/*
	 * @Override public List<UserVO> selectUserList(UserVO uvo) {
	 * log.info(">> UserService selectUserList  in"); List<UserVO> userList =
	 * udao.selectUserList(uvo); for(UserVO u : userList) {
	 * u.setAuthList(udao.selectAuths(u.getEmail())); } return userList; }
	 */

	@Override
	public List<UserVO> getList() {
		List<UserVO> userList = udao.getList();
		for(UserVO u : userList) {
			u.setAuthList(udao.selectAuths(u.getEmail()));
		}
		return userList;
	}

	@Override
	public void modify(UserVO uvo) {
		if(uvo.getPwd()==null || uvo.getPwd().length()==0 || uvo.getPwd().equals("")) {
			udao.modify(uvo);
		}else {
			uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
			udao.pwdmodify(uvo);
		}
		log.info(">> 수정된 uvo {}", uvo);
		
		
	}

	@Transactional
	@Override
	public void delete(String name) {
		udao.authdelete(name);
		udao.delete(name);
		
	}

	@Override
	public UserVO getUserEmail(String email) {

		return udao.getUserEmail(email);
	}

	@Override
	public UserVO chkemail(String email) {
	
		return udao.chkemail(email);
	}





//	@Override
//	public int updateLastLogin(String authEmail) {
//		log.info(">> UserService updateLastLogin in");
//		return udao.updateLastLogin(authEmail);
//	}
	
}
