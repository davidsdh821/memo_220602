package com.memo.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//회면
@RequestMapping("/user")
@Controller
public class UserController {
	
	//http://localhost/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/sign_up");
		
		return "template/layout";
	}
	
	//http://localhost/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		
		model.addAttribute("viewName", "user/sign_in");
		
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpServletRequest session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
		
	}
	
	
	
//	//ajax가 아니기 때문에 화면에서 처리한다
//	@PostMapping("/sign_up_for_submit")
//	public String signUpForSubmit(
//			@RequestParam("loginId") String loginId,
//			@RequestParam("password") String password,
//			@RequestParam("name") String name,
//			@RequestParam("email") String email
//			) {
//		//DB에 저장(생략)
//		
//		return "redirect:/user/sign_in_view";
//	}
}
