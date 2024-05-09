package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserDAO {

	int register(UserVO uvo);

	int insertAuthInit(String email);

	UserVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<UserVO> selectUserList(UserVO uvo);

	List<UserVO> getList();

	void modify(UserVO uvo);

	void pwdmodify(UserVO uvo);

	void authdelete(String name);

	void delete(String name);

	UserVO getUserEmail(String email);

	UserVO chkemail(String email);






}
