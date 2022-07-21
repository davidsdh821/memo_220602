package com.memo.post.bo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostBO {

	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		//파일이 있으면 파일 업로드 => path(경로) 리턴받음
		
		
		//dao insert
	}
	
}
