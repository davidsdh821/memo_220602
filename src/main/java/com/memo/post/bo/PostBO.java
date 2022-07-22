package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		String imagePath = null; //이곳에다 null로 만들어 놓으면 아래에서 파일이 있거나 없을때의 경우 둘다를 처리할 수 있다.
		
		//파일이 있으면 파일 업로드 => path(경로) 받아옴(리턴)
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
				
		}
		
		//dao insert
		postDAO.insertPost(userId, subject, content, imagePath);
		
		
	}
	
	//list
	public List<Post> getPostList() {
		
		return postDAO.selectPostList();
	}
	
	
	
}
