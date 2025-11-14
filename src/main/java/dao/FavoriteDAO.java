package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Allergen;
import model.Product;
import util.DBUtil;

public class FavoriteDAO {

  // お気に入り追加
  public void add(int userId, int productId) throws SQLException {
	  System.out.println("お気に入り追加開始: userId=" + userId + ", productId=" + productId);

	  String sql = "INSERT INTO FAVORITES (USER_ID, PRODUCT_ID) VALUES (?, ?)";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, userId);
      stmt.setInt(2, productId);
      stmt.executeUpdate();
      System.out.println("お気に入り追加成功");
    
    }
  }

  // お気に入り削除
  public void remove(int userId, int productId) throws SQLException {
    String sql = "DELETE FROM FAVORITES WHERE USER_ID = ? AND PRODUCT_ID = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, userId);
      stmt.setInt(2, productId);
      stmt.executeUpdate();
    }
  }

  // お気に入り一覧取得
  public  List<Product> findByUserId(int userId) throws SQLException {
    List<Product> list = new ArrayList<>();

    String sql = """
      SELECT P.PRODUCT_ID, P.NAME, P.CATEGORY
      FROM PRODUCTS P
      JOIN FAVORITES F ON P.PRODUCT_ID = F.PRODUCT_ID
      WHERE F.USER_ID = ?
    """;

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, userId);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        Product product = new Product();
        product.setId(rs.getInt("PRODUCT_ID"));
        product.setName(rs.getString("NAME"));
        product.setCategory(rs.getString("CATEGORY"));

        // アレルゲン情報も取得（必要なら）
        product.setAllergens(fetchAllergens(conn, product.getId()));

        list.add(product);
      }
    }

    return list;
  }

  // 補助：商品に紐づくアレルゲン一覧を取得
  private static List<Allergen> fetchAllergens(Connection conn, int productId) throws SQLException {
    List<Allergen> allergens = new ArrayList<>();

    String sql = """
      SELECT A.ALLERGEN_ID, A.NAME, A.TYPE
      FROM ALLERGENS A
      JOIN PRODUCT_ALLERGENS PA ON A.ALLERGEN_ID = PA.ALLERGEN_ID
      WHERE PA.PRODUCT_ID = ?
    """;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, productId);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        Allergen allergen = new Allergen();
        allergen.setAllergenId(rs.getInt("ALLERGEN_ID"));
        allergen.setName(rs.getString("NAME"));
        allergen.setType(rs.getString("TYPE"));
        allergens.add(allergen);
      }
    }

    return allergens;
  }
}
