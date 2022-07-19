package com.memo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.user.bo.UserBO;
import com.memo.user.model.User;

//데이터만 내려주는 api
@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	UserBO userBO;
	
	//http://localhost/user/user_list
	@RequestMapping("/user_list")
	public List<User> userList() {
		
		return userBO.getUserList();
	}
	@RequestMapping("is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId
			) {
		
		boolean existLoginId = userBO.existLoginId(loginId);
		
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", existLoginId);
		
		
		return result;	
		}
	
	 
}
