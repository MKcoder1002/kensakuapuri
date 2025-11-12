<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>商品検索フォーム</title>
  <link rel = "stylesheet" href="css/serchForm.css">

  
</head>
<body>
  <h2>商品検索フォーム</h2>

  <form   action="SearchResultServlet" method="get">
  
  <div class = "search-form">
    <!-- 商品名 -->
    <label>商品名:</label>
    <input type="text" name="keyword">

    <!-- カテゴリ -->
    <label>カテゴリ:</label>
    <select name="category">
      <option value="">-- 全て --</option>
      <c:forEach var="cat" items="${categoryList}">
        <option value="${cat}">${cat}</option>
      </c:forEach>
    </select>
</div>
    <!-- アレルゲン除外 -->
   <br><label>除外したいアレルゲン:</label>

<!-- 特定原材料 -->
<h3>特定原材料</h3>
<div class="allergen-grid" id="tokutei-container">
  <c:forEach var="a" items="${allergenList}">
    <c:if test="${a.type == '特定原材料'}">
      <button type="button" class="allergen-btn" data-allergen="${a.name}">
        ${a.name}
      </button>
    </c:if>
  </c:forEach>
</div>

<!-- 準ずるもの -->
<h3>特定原材料に準ずるもの</h3>
<div class="allergen-grid" id="junzuru-container">
  <c:forEach var="a" items="${allergenList}">
    <c:if test="${a.type == '準ずるもの'}">
      <button type="button" class="allergen-btn" data-allergen="${a.name}">
        ${a.name}
      </button>
    </c:if>
  </c:forEach>
</div>

<!-- hiddenフィールド -->
<input type="hidden" name="excludedAllergens" id="excludedAllergens">

    <input type="submit" value="検索">
  </form>
    <script src="js/allergenFilter.js"></script>
</body>
</html>
