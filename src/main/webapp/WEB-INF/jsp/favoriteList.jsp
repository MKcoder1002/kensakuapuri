<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>お気に入り一覧</title>
<link rel="stylesheet" href="css/FavoriteList.css">
</head>
<body>
<div class="top-right-nav">
    <a href="mainMenu.jsp">🏠 メイン</a>
    <a href="SearchFormServlet">🔍 検索</a>
    <a href="FavoriteListServlet">⭐ お気に入り</a>
</div>
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
          <button type="submit" ;">お気に入り解除</button>
        </form>
       
      </div>
    </c:forEach>
     <p><a href="mainMenu.jsp">メインメニューに戻る</a></p>
  </c:otherwise>
</c:choose>

</body>
</html>
