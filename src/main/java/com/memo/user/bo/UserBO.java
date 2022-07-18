package com.memo.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.dao.UserDAO;
import com.memo.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	UserDAO userDAO;
	
	
	public List<User> getUserList() {
		
		
		return userDAO.SelectUserList();
	}
	
}
