package com.memo.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	//http://localhost/test/1
	@ResponseBody
	@RequestMapping("/test/1")
	public String test1() {
		
		return "Hello World!";
	}
	
	@ResponseBody
	@RequestMapping("/test/2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("aaaa", 1234);
		map.put("bbb", 1234);
		map.put("ccc", 1234);
		map.put("ddd", 1234);
	
	return map;
	}
	
	//http://localhost/test/3
	@RequestMapping("/test/3")
	public String test3() {
		
		return "template/layout";
	}
	
}
