<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="d-flex justify-content-center">
	<div class="login-box">
		<h1 class="mb-4">로그인</h1>
		
		<%-- 폼을 사용하는 이유는 enter 키를 누를 때도 자동으로 이동 될 수 있기 때문이다.--%>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="input-group mb-3">

				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>
	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			<input type="submit" class="btn btn-block btn-primary" value="로그인">
			<a class="btn btn-block btn-dark" href="/user/sign_up_view">회원가입</a>
			
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#loginForm').on('submit', function(e) {
		e.preventDefault(); //서브밋 중단
		
		let loginId = $('input[name=loginId]').val().trim();
		if(loginId.length < 1) {
			alert('아이디를 입력하세요');
			return false;
		}
		
		let password = $('#password').val();
		if(loginId.length == "") {
			alert('비밀번호를 입력하세요');
			return false;
		}
		
		//ajax호출 로그인은 폼방식이 유리하다
		let url = $(this).attr("action");
		let params = $(this).serialize(); //form태그에 있는 nama 값들을 qurey스트링으로 구성
		$.post(url, params) //위에 저장한 id와 password 함수를 써도 된다
		.done(function(data) {
			if(data.result =="success") {
				alert('성공');
				//로그인이 성공하면 글 목록으로 이동
				location.herf = "/post/post_list_view";
				
			} else {
				alert(data.errorMessage);
			}
		});
		
		
	});
	
	
	
	
	
	
});	
</script>