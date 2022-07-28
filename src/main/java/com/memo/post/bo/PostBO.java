package com.memo.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	//로거(로그의 내용을 걸러내는 것), slf4j 선택, 실무 위주의 기술이다
//	private Logger logger = LoggerFactory.getLogger(PostBO.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // 이방식이 편하다
	
	
	
	
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
	
	//게시판 글
	public Post getPostbyId(int postId) {
		
		return postDAO.selectPostbyId(postId);
	}
	
	//수정
	public int updatePost(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
		logger.info("updatePost postId:{} userId:{}", postId , userId);
		
		//기존에 저장된 글을 가져온다.
		Post post = getPostbyId(postId);
		if(post == null) {
			//웹 개발 할 때 system.out.println 함수를 절대 쓰면 안된다
			//싱글쓰레드기 때문 여러명이 사용할 때 누군가가 system.out.println가 출력되면 전부 멈춰버리기 때문이다
			
			logger.error("[update post] 수정할 게시물이 없습니다. postId:{}", postId); //원하는 단계부터 로그만을 출력
			//로그에 단서를 남겨 어디서 어디가 문제인지 알려준다
			
			
			//없으면 실행시키지 않고 return
			return 0;
		}
		
		//업로드할 파일이 있는지 확인 한후 서버에 업로드하고 imagePath를 받아온다
		//업로드 할 파일이 없는 경우 수정하지 않음
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
			
			//새로 업로드 된 이미지가 올라오면, 기존 이미지 삭제(기존이미지가 있을 때만)
			if(imagePath != null && post.getImagePath() != null) {
				//기존 이미지 삭제
				try {
					fileManager.deleteFile(post.getImagePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("이미지 삭제 실패. postId{}", postId);// 원래 e.printtrace였지만 logger로 바꿨다
				}
			}
			
		}
		
		// update

		return postDAO.updatePostByPostIdAndUserId(postId, userId, subject, content, imagePath);
		
	}
	
	public void deletePost(int postId, int userId) {
		//사진도 같이 지워줘야한다.
		//용량을 꽤 차지하기때문에 잘 지워줘야한다
		
		//삭제전 게시글을 먼저 가져와 본다(imagePath가 있을 수 있기 때문에)
		Post post = getPostbyId(postId); //NPE가 올 수도 있기 때문에 확인해줘야 한다
		if(post == null) {
			logger.error("[delete post] 삭제할 게시글이 존재하지 않습니다. psotId{}", postId); //이런식으로 계속 확인을 해줘야한다. 비정상적인 루트로 이곳에 들어 올 수 있기 때문이다
			return;
		}
		
		//imagePath가 있으면 이미지(파일) 삭제 처리
		if(post.getImagePath() != null) {
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("[delete post] 이미지 삭제 실패. postId{}, path{}", postId, post.getImagePath());// 원래 e.print trace였지만 logger로 바꿨다
			}
		}
		
		
		
		
		//db 행삭제
		postDAO.deletePost(postId, userId);
		
	}
	 
	
	
	
}
