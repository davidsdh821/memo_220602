<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%-- d-flex와 justify-content-between: logo와 로그인 정보를 양쪽 끝으로 떨어뜨린다. --%>
<div class="header bg-secondary d-flex justify-content-between mb-5">
	<div class="logo">
		<h1 class="text-white p-4 font-weight-bold">메모 게시판</h1>
	</div>
	
	<div class="login-info">
		
		<%-- session에 정보가 있을 때, 로그인 했을때 세션에 데이터를 넣어줬다.--%>
		<c:if test="${not empty userName}">
		<div class="mt-5 mr-4">
			<span class="text-white">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</div>
		</c:if>
	</div>
</div>