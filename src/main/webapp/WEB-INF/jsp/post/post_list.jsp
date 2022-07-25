<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글목록</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach var ="result" items="${result}">
				<tr>
					<td>${result.id}</td>
					<td>
					<a href="/post/post_detail_view?postId=${result.id}">${result.subject}</a>
					</td>
					<td><fmt:formatDate value="${result.createdAt}" pattern="yyyy년 MM월 dd일" var="date1"/>${date1}</td>
					<td><fmt:formatDate value="${result.updatedAt}" pattern="yyyy년 MM월 dd일" var="date2"/>${date2}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
			
		<div class="d-flex justify-content-end"><a herf="/post/post_create_view" class="btn btn-primary">글쓰기</a></div>
	</div>
</div>
    