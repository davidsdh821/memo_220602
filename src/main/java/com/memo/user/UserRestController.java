package com.memo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
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
	
	@PostMapping("/sign_up")
	public Map<String, Object> signup(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		//아까 params에 전부 집어 넣어졌기 때문에 가져올수 있다.
		
		//비밀번호 해싱(md5) 복호화가능, 진짜 비밀번호는 SHA256방식으로 하자.
		String encryptPassword = EncryptUtils.md5(password);
		
		//db insert
		userBO.addUser(loginId, encryptPassword, name, email);
		
		//결과 return
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	
	}
	 
}
