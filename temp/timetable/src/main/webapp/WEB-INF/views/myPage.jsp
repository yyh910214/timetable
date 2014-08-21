<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>강의 평가</title>
<link rel="stylesheet"
	href="/css/jAutoCheckList/bootstrap/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/jAutoCheckList/bootstrap/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/jAutochecklist.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/style.css" />
<link rel="stylesheet" href="/css/table.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="/js/jAutoCheckList/jAutochecklist.min.js"></script>
<script src="/js/jAutoCheckList/bootstrap/bootstrap.min.js"></script>
<script src="/js/jAutoCheckList/script.js"></script>
</head>
<body>
<div class="row">
<div class="col-md-12"><div class="row">
<div class="col-md-1"><a href="/user/editUser?email=${sessionScope.user.email}">개인정보수정</a></div>
	<div class="col-md-11">
	<h1>내 평가</h1>
	<table class="bordered">
		<thead>
			<tr>
				<th>학수번호</th>
				<th>과목명</th>
				<th>교수님</th>
				<th>작성자</th>
				<th>내용</th>
				<th>점수</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="comment">
			<c:if test="${fn:length(comments) eq 0}">
				<tr>
					<td colspan="6">등록한 강의평가가 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="comment" varStatus="idx" items="${comments}">
				<tr>
					<td>${comment.lectureNum }</td>
					<td>${comment.lectureName }</td>
					<td>${comment.prof }</td>
					<td>${comment.studentNum }</td>
					<td>${comment.text }</td>
					<td>${comment.point }</td>
					<td><a
						href="/comment/delete?lectureID=${comment.lectureID }&studentNum=${comment.studentNum }">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/comment/commentList?studentNum=${searchParam.studentNum }">내
		강의평가 더보기</a>
</div></div></div></div>
</body>
</html>