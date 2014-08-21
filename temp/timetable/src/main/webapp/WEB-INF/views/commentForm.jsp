<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" title="Default"
	href="css/css3-formular-styles.css" type="text/css" media="screen">
</head>
<body>
	<form class="commentForm" action="<c:if test="${null eq comment }">/comment/insert</c:if><c:if test="${null ne comment }">/comment/update</c:if>" method="post">
		<fieldset>
			<legend>강의평가등록</legend>
			<ol class="clearfix">
				<li><label for="studentNum">학번 : </label> <input size="10"
					name="studentNum" id="studentNum" type="text" value="${sessionScope.user.studentNum }" readonly></li>
				<li><label for="lectureID">학수번호 : </label> 
				<input size="10" name="lectureID" id="lectureID" type="text" value="${commentLecture.lectureID}" readonly>
				</li>
				<li><label for="lectureName">강의명 : </label> 
				<input size="10" name="lectureName" id="lectureName" type="text" value="${commentLecture.lectureName}" readonly>
				</li>
				<li><label for="prof">교수님 : </label> 
				<input size="10" name="prof" id="prof" type="text" value="${commentLecture.prof}">
				</li>
				<li><label for="point">점수 : </label> 
				<input size="10" name="point" id="point" type="text" value="0">
				</li>
				<li><label for="text">평가내용 :</label> <textarea cols="32"
						rows="7" name="text" id="text"><c:if test="${null ne comment }">${comment.text }</c:if></textarea></li>
				<li class="last"><input name="submit" id="submit"
					value="등록" type="submit"></li>
			</ol>
		</fieldset>
	</form>

</body>
</html>