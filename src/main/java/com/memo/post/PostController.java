package com.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//화면
@RequestMapping("/post")
@Controller
public class PostController {
	
	//http://localhost/post/post_list_view
	@RequestMapping("/post_list_view")
	public String postListView(Model model) {
		
		model.addAttribute("viewName", "post/post_list");
		
		return "template/layout";
	}
	
	/**
	 * 글쓰기 화면
	 * @param model
	 * @return
	 */
	
	//http://localhost/post/post_create_view
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model) {
		
		model.addAttribute("viewName", "post/post_create");
		
		return "template/layout";
	}
	
	
}
