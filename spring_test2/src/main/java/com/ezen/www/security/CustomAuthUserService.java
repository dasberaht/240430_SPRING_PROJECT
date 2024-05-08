package com.ezen.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomAuthUserService implements UserDetailsService {

	@Inject
	private UserDAO udao;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username의 parameter = 로그인을 시도한 email
		// UserDetails = 인증용 객체
		// username의 parameter가 DB에 있는지 확인하여, 인증용 객체를 리턴하도록 처리
		
		// 로그인 시도한 객체
		UserVO uvo = udao.selectEmail(username);
		if(uvo == null) {
			throw new UsernameNotFoundException(username); 
		}
		uvo.setAuthList(udao.selectAuths(username));
		log.info("> userDetails uvo {}", uvo);
		
		return new AuthUser(uvo);
	}

}
