package com.memo.interceptor;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Permissioninterceoptor implements HandlerInterceptor {
	//들어올때는 el문법( ${} ) 해석 전
	//나올때는 해석 후 
	//권한 검사
	//예) 로그인을 한 상태에서 로그인 페이지에 들어가려할 때 그 요청을 가로채서 주소를 변경시킨다
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request , HttpServletResponse response, Object handler) throws IOException {
		
		//세션이 있는지 확인한다 => 있으면 로그인 된 상태
		HttpSession session = request.getSession();
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		//url path 확인
		String uri = request.getRequestURI();
		logger.info("####################### uri:{}", uri);
		//비로그인 && /post => 로그인 페이지로 리다이렉트 잘못하면 무한 리다이렉트 할 수 있기 때문에 조심하자
		if(userLoginId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		//로그인 && /user => 글 목록 페이지로 리다이렉트 
		if(userLoginId != null && uri.startsWith("/user")) {
			response.sendRedirect("/post/post_list_view");
			return false;
		}
		
		
		return true; //요청된 path로 컨트롤러 수행
		//
	}
	
	@Override 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		
		//URI확인
		String uri = request.getRequestURI();
		logger.info("############### postHandler: {}", uri);
		
		
		
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		
		//URI확인
		String uri = request.getRequestURI();
		logger.info("############### afterCompletion: {}", uri);
		
	}
	
	
}
