<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글상세/수정</h1>
		<input type="text" id="subject" class="form-control"
			placeholder="제목을 입력하세요" value="${post.subject}">
		<textarea id="content" class="form-control" rows="10"
			placeholder="내용을 입력하세요" >${post.content}</textarea>

		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jpg,.png,.gif,.jpeg">
		</div>
		<%-- 이미지가 있는 것만 노출 --%>
		<c:if test="${not empty post.imagePath}">
		<div class="image-area mb-3">
			<img alt="업로드 이미지" src="${post.imagePath}" width="400">
		</div>
		</c:if>
		<div class="d-flex justify-content-between">
		<button id="postDeleteBtn" type="button" class="btn btn-secondary">삭제</button>
			<div>
				<button id="postListBtn" type="button" class="btn btn-primary">목록</button>
				<button id="saveBtn" type="button" class="btn btn-pirmary">저장</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#postListBtn').on('click', function() {
			location.href = "/post/post_list_view";

		});
	
		
		
		
		
		
		
		
	});
</script>