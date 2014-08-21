<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<decorator:head />
</head>
<body>
 <div class="container">
      <div class="header">
        <ul class="nav nav-pills pull-right">
          <li><a href="/admin/index">강의갱신</a></li>
          <li><a href="/admin/userList">회원관리</a></li>
          <li><a href="/lecture/index">MAIN</a></li>
        </ul>
        <h3 class="text-muted">관리자페이지</h3>
      </div>

      <decorator:body />

      <div class="footer">
        <p>&copy; HUFS 2014</p>
      </div>

    </div> <!-- /container -->
</body>
</html>