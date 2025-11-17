<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>検索結果</title>
  <link rel="stylesheet" href="css/searchResult.css">
</head>
<body>
 <div class="top-right-nav">
      <a href="FavoriteListServlet">⭐ お気に入り</a>
      <a href="SearchFormServlet">🔍 検索に戻る</a>
      <a href="index.jsp">🔐 ログイン</a>
  </div>
 
  <h2>検索結果</h2>
 
  <c:choose>
    <c:when test="${empty resultList}">
      <p>該当する商品は見つかりませんでした。</p>
    </c:when>
    <c:otherwise>
      <table>
        <tr>
          <th>商品名</th>
          <th>カテゴリ</th>
          <th>アレルギー</th>
          <th>お気に入り</th>
        </tr>
 <c:forEach var="item" items="${resultList}">
  <tr>
    <td>${item.name}</td>
    <td>${item.category}</td>
    <td>
      <c:if test="${not empty item.allergens}">
        <c:forEach var="a" items="${item.allergens}">
          ${a.name}<br />
        </c:forEach>
      </c:if>
    </td>
    <td>
<span class="favorite-btn ${item.favorited ? 'active' : ''}"
      data-product-id="${item.id}"
      data-favorited="${item.favorited}">
    ★
</span>
<!-- 検索条件は JS 送信用に hidden で保持 -->
<input type="hidden" class="keyword" value="${param.keyword}">
<input type="hidden" class="category" value="${param.category}">
<input type="hidden" class="excluded" value="${param.excludedAllergens}">
    </td>
  </tr>
</c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
  <p><a href="<c:url value='/SearchFormServlet' />">検索フォームに戻る</a></p>
  <script src="js/favorite.js"></script> <!-- 外部JS読み込み -->
</body>
</html>
