<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>検索メニュー</title>
 <link rel="stylesheet"  href="css/mainMenu.css">
</head>
<body>
  <h1>ようこそ、<%= loginUser.getName() %>さん！</h1>

  <div class="menu">
    <a href="SearchFormServlet">🔍 検索</a>
    <a href="FavoriteListServlet">⭐ お気に入り</a>
    <a href = "index.jsp">🔐ログイン画面</a>
    
  </div>
</body>
</html>
