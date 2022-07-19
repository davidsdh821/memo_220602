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
	
	public boolean existLoginId(String loginId) { //중복되는지 안되는지만 확인하면 되기때문에 boolean으로 받으면 된다
		
		
		return userDAO.existLoginId(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) { //굳이 encrpty를 할 필요가 없다
		
		userDAO.insertUser(loginId, password, name, email);
	}
 	
	
	
	
	
}
