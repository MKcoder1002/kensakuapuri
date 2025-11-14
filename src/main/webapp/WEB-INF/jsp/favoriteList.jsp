<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>お気に入り一覧</title>
  <style>
    body {
      font-family: sans-serif;
      background: #f0f8ff;
      padding: 2em;
      text-align: center;
    }
    .product-card {
      background: white;
      border: 1px solid #ccc;
      border-radius: 8px;
      padding: 1em;
      margin: 1em auto;
      width: 80%;
      box-shadow: 2px 2px 6px rgba(0,0,0,0.1);
    }
    .product-card h3 {
      margin: 0.5em 0;
    }
    .product-card button {
      background: #d9534f;
      color: white;
      border: none;
      padding: 0.5em 1em;
      border-radius: 4px;
      cursor: pointer;
    }
    .product-card button:hover {
      background: #c9302c;
    }
  </style>
</head>
<body>
  <h1>お気に入り一覧</h1>

 <c:choose>
  <c:when test="${empty favoriteList}">
    <p>お気に入りはまだ登録されていません。</p>
  </c:when>
  <c:otherwise>
    <c:forEach var="product" items="${favoriteList}">
      <div class="product-card">
        <h3>${product.name}</h3>
        <p>${product.category}</p> <!-- descriptionがなければcategoryで代用 -->

        <form action="FavoriteRemoveServlet" method="post">
          <input type="hidden" name="productId" value="${product.id}">
          <button type="submit">お気に入り解除</button>
        </form>
       
      </div>
    </c:forEach>
     <p><a href="mainMenu.jsp">メインメニューに戻る</a></p>
  </c:otherwise>
</c:choose>

</body>
</html>
