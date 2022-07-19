package com.memo.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.user.model.User;

@Repository
public interface UserDAO {

	public List<User> SelectUserList();
	
	public boolean existLoginId(String loginId); //sql문의 resulttype가 boolean이어도 된다.
	
	
	public void insertUser(
		@Param("loginId") String loginId, 
		@Param("password") String password, 
		@Param("name") String name, 
		@Param("email") String email);
}
