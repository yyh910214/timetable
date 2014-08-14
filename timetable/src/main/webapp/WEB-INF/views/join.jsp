<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<style>body{background:url(/img/bg.png) center;margin: 0 auto;width: 960px;padding-top: 50px}.footer{margin-top:50px;text-align:center;color:#666;font:bold 14px Arial}.footer a{color:#999;text-decoration:none}.login-form{margin: 50px auto;}</style>
<link rel="stylesheet" href="/css/join.css" />
<script type="text/javascript">
$(document).ready(function() {

	// Check if JavaScript is enabled
	$('body').addClass('js');

});
</script>
</head>
<body>
<div >

	<h1>Login</h1>

	<form class="login-form" action="/login/join" method="post">

		<input type="text" name="email" placeholder="email">
		
		<input type="password" name="passwd" placeholder="비밀번호">
		<input type="password" name="passwd_confirm" placeholder="비민벌호확인">
		
		<input type="text" name="major" placeholder="전공">
		<input type="text" name="studNum" placeholder="학번">
		
		<div>
			<label>성별 :</label>
			<p>
				<label><input type="radio" name="sex" value="FEMALE" checked />FEMALE</label><br/>
				<label><input type="radio" name="sex" value="MALE"/>MALE</label><br/>
			</p>
		</div>	
		

		<input type="submit" value="회원가입">

	</form>

</div>


</body>
</html>