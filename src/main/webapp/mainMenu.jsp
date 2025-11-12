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
  <style>
    body { 
    font-family: sans-serif; padding: 2em; background: aliceblue;
    text-align: center;
     }
    h1 {
     color: #333;
      }
    .menu {
     margin-top: 2em;
      }
    .menu a {
      display: inline-block;
      padding: 1em 2em;
      background: #0078d4;
      color: white;
      text-decoration: none;
      border-radius: 8px;
      font-weight: bold;
    }
    .menu a:hover {
      background: #005fa3;
    }
  </style>
</head>
<body>
  <h1>ようこそ、<%= loginUser.getName() %>さん！</h1>

  <div class="menu">
    <a href="SearchFormServlet">🔍 検索する</a>
  </div>
</body>
</html>
