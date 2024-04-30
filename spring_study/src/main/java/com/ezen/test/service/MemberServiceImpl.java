package com.ezen.test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;
	
	// httpservlet 객체 생성
	final HttpServletRequest request;
	

	// 암호화 관련
	final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int insert(MemberVO mvo) {
		// id가 중복되는지 여부를 체크하여, 회원가입 여부를 결정
		// mvo를 가져왔지만, id만 주고 db에서 일치하는 mvo객체가 있는지 체크하여 mvo객체를 리턴 > 일치하는 객체가 있다면, 가입실패 / 없다면, 가입성공(단, controller에서 처리해도 무방)
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo != null) {
			// 중복아이디가 있는 경우
			return 0;
		}
		// 중복아이디가 없는 경우 회원가입 처리
		// 아이디/패스워드가 null이거나, 값이 없다면 가입 불가
		if(mvo.getId()== null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		// 회원가입 처리
		// 가입 시 password는 암호화 처리
		// encode() : 암호화 / matches(입력된 비밀번호, 암호화된 비밀번호) > true/false 리턴
//		String pw = mvo.getPw();
//		String encodiPW = passwordEncoder.encode(pw);
//		mvo.setPw(encodiPW);   (아래 구문과 같다)		
		mvo.setPw(passwordEncoder.encode(mvo.getPw()));
		// 회원가입
		int isOk = mdao.insert(mvo);
		log.info(">> member insert {}", (isOk > 0 ? "OK" : "FAIL"));

		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		log.info(">>> login Service in");
		// 로그인 유저 확인
		MemberVO tempMvo = mdao.getUser(mvo.getId());		// 회원가입 했을 때 사용했던 메서드 활용
		
		// 아이디가 없다면, 
		if(tempMvo == null) {
			return null;
		}

		// 아이디가 있다면, matches(원래 비밀번호, 암호화된 비밀번호) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
				
		
		return null;
	}

	@Override
	public void lastloginUpdate(String id) {
		mdao.lastLoginUpdate(id);
	}

	@Override
	public void modify(MemberVO mvo) {
		log.info(">>> member modify Service in");
		// pw 여부에 따라 변경사항을 나누어서 처리
		// pw의 수정값이 (없다면, 기존 값 설정 / 있다면, 암호화처리하여 수정)
		if(mvo.getPw()==null || mvo.getPw().length()==0) {
			MemberVO sesMvo = (MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMvo.getPw());
		}else {
			String setPw = passwordEncoder.encode(mvo.getPw());
			mvo.setPw(setPw);
		}
		log.info(">>>> pw 수정 후 mvo {}", mvo);
		
		mdao.update(mvo);
		
	}

	@Override
	public void remove(String id) {
		log.info(">>> member remove Service in");
		mdao.remove(id);
	}
	
	
	
	
}
