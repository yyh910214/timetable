<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ����</title>
</head>
<body>
<h1>���� ����</h1>
<form method="post" action="/admin/tableParsing">
<select name="year" class="form-control">
  <option value="2011">2011</option>
  <option value="2012">2012</option>
  <option value="2013">2013</option>
  <option value="2014">2014</option>
  <option value="2015">2015</option>
</select>

<select name="season" class="form-control">
  <option value="1">1�б�</option>
  <option value="2">�����б�</option>
  <option value="3">2�б�</option>
  <option value="4">�ܿ��б�</option>
</select>

<input type="submit" value="����" />

</form>
</body>
</html>