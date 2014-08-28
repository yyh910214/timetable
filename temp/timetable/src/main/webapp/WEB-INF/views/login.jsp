<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div >

	<h1>Login</h1>
	<c:if test="${null ne retryMessage}">
		${retryMessage}
	</c:if>
	<form class="form-horizontal" role="form" action="/login/login" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
    </div>
  </div>
  
<!--   <div class="form-group"> -->
<!--     <label for="studentNum" class="col-sm-2 control-label">학번</label> -->
<!--     <div class="col-sm-10"> -->
<!--       <input type="text" class="form-control" id="studentNum" placeholder="studentNum" name="studentNum"> -->
<!--     </div> -->
<!--   </div> -->
  
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="passwd">
    </div>
  </div>
<!--   <div class="form-group"> -->
<!--     <div class="col-sm-offset-2 col-sm-10"> -->
<!--       <div class="checkbox"> -->
<!--         <label> -->
<!--           <input type="checkbox"> Remember me(아직) -->
<!--         </label> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">로그인</button>
    </div>
  </div>
  <div class="form-group">
  	<div class="col-sm-offset-2 col-sm-10">
  		<a href="/login/join">회원가입</a>
  	</div>
  </div>
</form>


</div>


</body>
</html>