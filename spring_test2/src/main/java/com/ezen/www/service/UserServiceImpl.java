package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserDAO udao;

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
		udao.modify(uvo);
	}


//	@Override
//	public int updateLastLogin(String authEmail) {
//		log.info(">> UserService updateLastLogin in");
//		return udao.updateLastLogin(authEmail);
//	}
	
}
