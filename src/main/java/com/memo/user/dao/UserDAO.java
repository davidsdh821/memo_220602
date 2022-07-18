package com.memo.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.memo.user.model.User;

@Repository
public interface UserDAO {

	public List<User> SelectUserList();
	
	
}
