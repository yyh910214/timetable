<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원 가입</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
		<h1>회원가입</h1>

		<form class="form-horizontal" method="post" action="/login/join">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="email">Email : </label> <input class="col-sm-10" type="email"
					class="form-control" name="email" id="email"
					placeholder="email을 입력하세요" required>

			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="passwd">비밀번호 :</label> <input type="password"
					name="passwd" id="passwd" placeholder="비밀번호" required> <label
					for="passwd_confirm">비밀번호 확인 :</label> <input type="password"
					name="passwd_confirm" id="passwd_confirm" placeholder="비민벌호확인" required>

			</div>

			<div class="form-group">
			<label class="col-sm-2 control-label" for="major" >전공 :</label>
				<input class="col-sm-10" type="text" id="major" name="major" placeholder="전공" required>
			<label for="studNum">학번 :</label>	
				<input type="text" id="studNum" name="studNum" placeholder="학번" required>
			</div>

			<div>
				<label class="col-sm-2 control-label">성별 :</label>
					<label><input type="radio" name="sex" value="FEMALE"
						checked />여자</label><label><input type="radio"
						name="sex" value="MALE" />남자</label>
			</div>
			<button type="submit" class="btn btn-default">가입</button>
		</form>


</body>
</html>