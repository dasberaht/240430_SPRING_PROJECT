package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.www.security.CustomAuthUserService;
import com.ezen.www.security.LoginFailureHandler;
import com.ezen.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// 비밀번호 암호화객체 'PasswordEncoder' Bean 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 'SuccessHandler' 객체 Bean 생성(Success / Fail Handler 커스텀 설정; 필수사항X)(사용자 커스텀 객체)
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();		// 생성예정
	}
	
	// 'FailHandler' 객체 Bean 생성(사용자 커스텀 객체)
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();		// 생성예정
	}
	
	// 'UserDetail' 객체 Bean 생성(사용자 커스텀 객체)
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthUserService();
	}
	
	
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증되는 객체로 설정
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소에 Mapping 설정
		// csrf() : form태그와의 관계 확인?
		http.csrf().disable();	// 공격에 대한 설정 푸는 명령어
		///////////////////////////////////////////////
		
		// 권한 승인요청
		// antMatchers : 접근을 허용하는 값(경로)
		// permitAll : 누구나 접근이 가능한 경로(권한을 모두에게 주는..)
		// authenticated() : 인증된 사용자만 접근이 가능한 경로
		// auth >>> hasRole : 권한 확인
		// 일반적으로 권한을 줄 때 : USER, ADMIN, MANAGER 등
		// .anyRequest().authenticated(); : 해당 모든 곳은 권한이 있어야 접근 가능하여야  
		http.authorizeRequests()
		.antMatchers("/user/list").hasRole("ADMIN")
		.antMatchers("/", "/board/list", "/board/detail", "/comment/**", "/up/**", "/re/**", "/user/register", "/user/login", "/user/chkemail/*").permitAll()
		.anyRequest().authenticated();
		
		
		// 커스텀 로그인 페이지 구성
		// Controller의 주소요청에 대한 맴핑이 같이 있어야 한다(필수).
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/user/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailureHandler());
		
		
		// 로그아웃 페이지는 반드시 method = "post"
		http.logout()
		.logoutUrl("/user/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
	}

	
	
}
