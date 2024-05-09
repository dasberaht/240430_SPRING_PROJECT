package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.UserVO;

public interface UserService {

	int register(UserVO uvo);

//	List<UserVO> selectUserList(UserVO uvo);

	List<UserVO> getList();

	void modify(UserVO uvo);

	void delete(String name);

	UserVO getUserEmail(String email);

	UserVO chkemail(String email);




	

//	int updateLastLogin(String authEmail);

}
