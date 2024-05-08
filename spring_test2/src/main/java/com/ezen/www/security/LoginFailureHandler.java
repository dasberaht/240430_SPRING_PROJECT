package com.ezen.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private String authEmail;
	private String errorMessage;
	
	// CustomAuthUserService에서 로그인 시도한 username에 대한 처리 시 실패한 경우
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		setAuthEmail(request.getParameter("email"));
		
		// exception 발생 시 메세지 저장
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			setErrorMessage(exception.getMessage().toString());
		}
		
		log.info("> errMsg {}", this.errorMessage);
		
		request.setAttribute("email", authEmail);
		request.setAttribute("errMsg", errorMessage);
		
		// usercontroller의 login으로 이동하고, error메세지를 보내는 처리(error라는 값을 인지하도록 설정 처리)
		request.getRequestDispatcher("/user/login?error").forward(request, response);				
		
		
	}

}
