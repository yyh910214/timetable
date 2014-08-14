<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<style>body{background:url(/img/bg.png) center;margin: 0 auto;width: 960px;padding-top: 50px}.footer{margin-top:50px;text-align:center;color:#666;font:bold 14px Arial}.footer a{color:#999;text-decoration:none}.login-form{margin: 50px auto;}</style>
<link rel="stylesheet" href="/css/login.css" />
<script type="text/javascript">
$(document).ready(function() {

	// Check if JavaScript is enabled
	$('body').addClass('js');

	// Make the checkbox checked on load
	$('.login-form span').addClass('checked').children('input').attr('checked', true);

	// Click function
	$('.login-form span').on('click', function() {

		if ($(this).children('input').attr('checked')) {
			$(this).children('input').attr('checked', false);
			$(this).removeClass('checked');
		}

		else {
			$(this).children('input').attr('checked', true);
			$(this).addClass('checked');
		}
	
	});

});
</script>
</head>
<body>
<div >

	<h1>Login</h1>

	<form class="login-form" action="/login/login" method="post">

		<input type="text" name="email" placeholder="email">
		
		<input type="password" name="passwd" placeholder="password">
		
		<span>
			<input type="checkbox" name="checkbox">
			<label for="checkbox">회원가입</label>
		</span>
		
		<input type="submit" value="log in">

	</form>

</div>


</body>
</html>