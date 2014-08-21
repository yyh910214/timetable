<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 관리</title>
<link rel="stylesheet" href="/css/jAutoCheckList/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/bootstrap/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/jAutochecklist.min.css" />
<link rel="stylesheet" href="/css/jAutoCheckList/style.css" />
<link rel="stylesheet" href="/css/table.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="/js/jAutoCheckList/jAutochecklist.min.js"></script>
<script src="/js/jAutoCheckList/bootstrap/bootstrap.min.js"></script>
<script src="/js/jAutoCheckList/script.js"></script>
</head>
<body>
	<h1>회원 관리</h1>
	<table class="bordered">
		<thead>
			<tr>
				<th>학번</th>
				<th>전공</th>
				<th>email</th>
				<th>성별</th>
				<th>등급</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="user">
			<form action="/admin/userList" method="get">
				<tr>
					<td>
						<input type="text" name="studentNum" />
					</td>
					<td>
						<input type="text" name="major" />
					</td>
					<td>
						<input type="email" name="email" />
					</td>
					<td>
						<select name="sex">
							<option value="">전체</option>
							<option value="MALE">남자</option>
							<option value="FEMALE">여자</option>
						</select>
					</td>
					<td>
						<select name="userLevel">
							<option value="0">전체</option>
							<option value="1">관리</option>
							<option value="2">일반</option>
						</select>
					</td>
					<td>
						<input type="submit" value="검색"/>
					</td>
				</tr>
			</form>
			<c:forEach var="user" varStatus="idx" items="${userList}">
					<tr>
					<td>
						${user.studentNum }
					</td>
					<td>
						${user.major }
					</td>
					<td>
						${user.email }
					</td>
					<td>
						${user.sex }
					</td>
					<td>
						${user.userLevel }
					</td>
					<td>
						<a href="/user/editUser?email=${user.email}">수정</a>
					</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>