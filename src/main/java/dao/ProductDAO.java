package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Allergen;
import model.Product;
import util.DBUtil;

public class ProductDAO {

	public List<Product> search(String keyword, String category, List<String> excludeAllergens) {
		  List<Product> resultList = new ArrayList<>();

		  // ① 除外アレルゲン名 → ID に変換
		  List<Integer> excludeIds = getAllergenIdsByNames(excludeAllergens);
		  boolean hasExcludes = excludeIds != null && !excludeIds.isEmpty();

		  // ② SQL構築（基本構文）
		  StringBuilder sql = new StringBuilder();
		  sql.append("SELECT p.PRODUCT_ID AS id, p.NAME, p.CATEGORY FROM PRODUCTS p WHERE 1=1 ");

		  // ③ キーワード正規化と分割（表記揺れ対応）
		  keyword = keyword != null ? keyword.trim() : "";
		  keyword = Normalizer.normalize(keyword, Normalizer.Form.NFKC);
		  keyword = keyword.replaceAll("・", "");
		  keyword = keyword.replaceAll("ポンデリング", "ポン・デ・リング");
		  String[] keywords = keyword.split("\\s+");

		  // ④ キーワードによる部分一致（OR条件）※空なら追加しない
		  boolean hasKeyword = false;
		  StringBuilder keywordBlock = new StringBuilder();
		  for (String k : keywords) {
		    if (k.isBlank()) continue;
		    if (hasKeyword) keywordBlock.append(" OR ");
		    keywordBlock.append("p.NAME LIKE ?");
		    hasKeyword = true;
		  }
		  if (hasKeyword) {
		    sql.append("AND (").append(keywordBlock).append(") ");
		  }

		  // ⑤ カテゴリ条件（完全一致）
		  if (category != null && !category.isEmpty()) {
		    sql.append("AND p.CATEGORY = ? ");
		  }

		  // ⑥ 除外アレルゲン条件（NOT EXISTS）
		  if (hasExcludes) {
		    sql.append("AND NOT EXISTS (");
		    sql.append("SELECT 1 FROM PRODUCT_ALLERGENS pa ");
		    sql.append("WHERE pa.PRODUCT_ID = p.PRODUCT_ID ");
		    sql.append("AND pa.ALLERGEN_ID IN (");
		    sql.append(String.join(",", Collections.nCopies(excludeIds.size(), "?")));
		    sql.append(")) ");
		  }

		  try (Connection conn = DBUtil.getConnection();
		       PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

		    int index = 1;

		    // ⑦ プレースホルダ設定（キーワード）
		    for (String k : keywords) {
		      if (k.isBlank()) continue;
		      stmt.setString(index++, "%" + k + "%");
		    }

		    // ⑧ プレースホルダ設定（カテゴリ）
		    if (category != null && !category.isEmpty()) {
		      stmt.setString(index++, category);
		    }

		    // ⑨ プレースホルダ設定（除外アレルゲンID）
		    if (hasExcludes) {
		      for (Integer allergenId : excludeIds) {
		        stmt.setInt(index++, allergenId);
		      }
		    }

		    // ⑩ 検索結果の取得と詰め込み
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      Product p = new Product();
		      p.setId(rs.getInt("id"));
		      p.setName(rs.getString("NAME"));
		      p.setCategory(rs.getString("CATEGORY"));
		      p.setAllergens(findAllergensByProductId(p.getId()));
		      resultList.add(p);
		    }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  }

		  return resultList;
		}
	private List<Integer> getAllergenIdsByNames(List<String> names) {
		  List<Integer> ids = new ArrayList<>();
		  if (names == null || names.isEmpty()) return ids;

		  StringBuilder sql = new StringBuilder();
		  sql.append("SELECT ALLERGEN_ID FROM ALLERGENS WHERE NAME IN (");
		  for (int i = 0; i < names.size(); i++) {
		    sql.append("?");
		    if (i < names.size() - 1) sql.append(", ");
		  }
		  sql.append(")");

		  try (Connection conn = DBUtil.getConnection();
		       PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
		    for (int i = 0; i < names.size(); i++) {
		      stmt.setString(i + 1, names.get(i));
		    }
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      ids.add(rs.getInt("ALLERGEN_ID"));
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }

		  return ids;
		}
	private List<Allergen> findAllergensByProductId(int productId) {
		  List<Allergen> list = new ArrayList<>();
		  String sql = """
		    SELECT a.ALLERGEN_ID, a.NAME, a.TYPE
		    FROM ALLERGENS a
		    JOIN PRODUCT_ALLERGENS pa ON a.ALLERGEN_ID = pa.ALLERGEN_ID
		    WHERE pa.PRODUCT_ID = ?
		  """;

		  try (Connection conn = DBUtil.getConnection();
		       PreparedStatement stmt = conn.prepareStatement(sql)) {
		    stmt.setInt(1, productId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      Allergen a = new Allergen();
		      a.setAllergenId(rs.getInt("ALLERGEN_ID"));
		      a.setName(rs.getString("NAME"));
		      a.setType(rs.getString("TYPE"));
		      list.add(a);
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }

		  return list;
		}
	public List<String> findAllCategories() {
		  List<String> list = new ArrayList<>();
		  String sql = "SELECT DISTINCT CATEGORY FROM PRODUCTS ORDER BY CATEGORY";

		  try (Connection conn = DBUtil.getConnection();
		       PreparedStatement stmt = conn.prepareStatement(sql);
		       ResultSet rs = stmt.executeQuery()) {
		    while (rs.next()) {
		      list.add(rs.getString("CATEGORY"));
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }

		  return list;
		}

}