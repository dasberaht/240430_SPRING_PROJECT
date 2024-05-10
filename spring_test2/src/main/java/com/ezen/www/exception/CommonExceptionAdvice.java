package com.ezen.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {

	// 일반적으로 404 오류가 자주 발생
	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String handler404(NoHandlerFoundException ex) {
//		log.info(">>> exception {}", ex.getMessage());
//		return "/custom404";		// custom404.jsp 페이지로 이동
//	}
	
	@ExceptionHandler(Exception.class)
	public String exceptipn(Exception ex) {
		log.info(">>> exception {}", ex.getMessage());
		return "/error_page";		
	}
	
	
}
