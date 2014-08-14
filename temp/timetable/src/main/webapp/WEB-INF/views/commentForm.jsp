<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" title="Default" href="css/css3-formular-styles.css" type="text/css" media="screen">
</head>
<body>

<form class="commentForm" action="#" method="post">
<fieldset>
<legend>Kontakformular</legend>
<ol class="clearfix">
<li>
<label for="firstname">Vorname:</label>
<input gtbfieldid="1" size="35" name="firstname" id="firstname" type="text">
</li>
<li>
<label for="email">E-Mail: (optional)</label>
<input gtbfieldid="2" size="35" name="email" id="email" type="text">
</li>
<li>
<label for="content">Inhalt:</label>
<textarea cols="32" rows="7" name="content" id="content"></textarea>
</li>
<li class="last">
<input name="submit" id="submit" value="Absenden" type="submit">
</li>
</ol>
</fieldset>
</form>
	
</body>
</html>