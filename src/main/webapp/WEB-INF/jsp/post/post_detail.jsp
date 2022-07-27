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
				<button id="saveBtn" type="button" class="btn btn-pirmary" data-post-id="${post.id}">저장</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#postListBtn').on('click', function() {
			location.href = "/post/post_list_view";

		});
		$('#saveBtn').on('click', function() {
			//validation
			let subject = $("#subject").val().trim();
			let content = $("#content").val().trim();
			if(subject == "" ) {
				alert("제목을 입력하세요");
				return;
			}
			if(content == "" ) {
				alert("내용을 입력하세요");
				return;
			}
			
			//이미지 확장자 검증 - 파일이 업로드 된 경우
			let file = $('#file').val(); //pc 파일의 경로를 가져옴
			console.log(file)

			if (file != "") { //파일이 비어있지 않는 경우만 검증
				let ext = file.split(".").pop().toLowerCase(); //파일 경로를 .으로 나누고 마지막 문자열 배열 저장후 소문자로 변경
				if ($.inArray(ext, [ "gif", "jpg", "jpeg", "png" ]) == -1) { //해당문자와 같지 않으면 -1로 표시가 된다
					//js에서는 []안에 값만 넣으면 바로 배열이 된다.
					alert("gif, jpg, jpeg, png 파일만 업로드 할 수 있습니다")
					$("#file").val(""); //파일을 비운다

					return;
				}

			}
			
			let postId = $(this).data("post-id")
//			alert(postId) 이런식으로 계속 보내는 데이터를 확인해주자

			//이미지를 보내야 하므로 form객체를 구성한다
			let formData = new FormData();
			formData.append("postId", postId); 
			formData.append("subject", subject); 
			formData.append("content", content); 
			formData.append("file", $('#file')[0].files[1]);
			
			//ajax 
			//외울 수 없기 때문에 검색하면서 찾아보자
			$.ajax({
				type: "put" //post도 되지만 세분화를 하기 위해서 put으로 해준다 put은 post의 계열이다
				,url: "/post/update"
				,data: formData //위에서 만들어놨다
				,enctype: "multipart/form-data" //파일 업로드 필수
				,processData: false
				,contentType: false
				
				,success:function(data) {
					if(data.result =="success") {
						alert("수정 되었습니다");
						location.reload(true); //새로고침
					}
				} 
				,error:function(e) {
					alert("메모저장실패")
				}
				
			});
			
			
			
			
		});
	
		
		
		
		
		
		
		
	});
</script>