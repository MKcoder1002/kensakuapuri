<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>検索結果</title>
  <style>
    body { font-family: sans-serif; padding: 2em; background: #f0f0f0; }
    h2 { color: #333; }
    table { border-collapse: collapse; width: 100%; margin-top: 1em; }
    th, td { border: 1px solid #ccc; padding: 0.5em; text-align: left; }
    th { background-color: #eee; }
  </style>
</head>
<body>
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
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>

  <p><a href="<c:url value='/SearchFormServlet' />">検索フォームに戻る</a></p>
</body>
</html>
