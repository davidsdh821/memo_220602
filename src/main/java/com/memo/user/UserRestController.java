package com.memo.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	
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
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request //서블릿에 세션 저장하기
			) {
		//서블릿에 세션을 저장하면 서버에 해당 유저의 로그인 기록이 저장된다.
		
		//비밀 번호 해싱
		String encryptPassword = EncryptUtils.md5(password);
		
		//loginId, 해싱 비번=> db select => 존재 유무
		User user = userBO.getUserByLoginIdPassword(loginId, encryptPassword);
		
		//로그인 성공시 세션에 정보를 담는다
		//실패 하면 실패 응답
		
		//로그인 성공 유무 resposne
		Map<String, Object> result = new HashMap<>();
		if(user != null) { //성공
			//세션에 정보를 담는다
			//너무 난발하여 저장하지말고 필요한 데이터만 저장하자
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());			
			result.put("result", "success");
		} else {
			
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
			
		} //실패
		
		
		
		return result;
	}
	
	
	 
}
