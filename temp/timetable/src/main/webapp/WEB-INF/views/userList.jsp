<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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
			</tr>
		</thead>
		<tbody id="user">
			<c:forEach var="user" varStatus="idx" items="${userList}">
					<tr>
					<td>
						${user.studNum }
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
					</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>