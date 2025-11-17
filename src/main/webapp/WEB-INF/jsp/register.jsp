<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet"  href="css/register.css">
</head>
<body>
<h1>新規ユーザー登録</h1>
<form action="Register" method="post">
  ユーザー名：<input type="text" name="name"><br>
  パスワード：<input type="password" name="pass"><br>
  <input type="submit" value="登録">
  
</form>
<a href="index.jsp">戻る</a>
</body>
</html>
