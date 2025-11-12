package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Allergen;
import util.DBUtil;

public class AllergenDAO {
  public List<Allergen> findAll() {
    List<Allergen> list = new ArrayList<>();
    String sql = "SELECT allergen_id, name, type FROM allergens ORDER BY name";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Allergen a = new Allergen();
        a.setAllergenId(rs.getInt("allergen_id"));
        a.setName(rs.getString("name"));
        a.setType(rs.getString("type"));
        list.add(a);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return list;
  }
  public List<Allergen> findAllergensByProductId(int productId) {
	  List<Allergen> list = new ArrayList<>();
	  String sql = """
	    SELECT a.allergen_id, a.name, a.type
	    FROM allergens a
	    JOIN product_allergens pa ON a.allergen_id = pa.allergen_id
	    WHERE pa.product_id = ?
	    ORDER BY a.name
	  """;

	  try (Connection conn = DBUtil.getConnection();
	       PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setInt(1, productId);
	    try (ResultSet rs = stmt.executeQuery()) {
	      while (rs.next()) {
	        Allergen a = new Allergen();
	        a.setAllergenId(rs.getInt("allergen_id"));
	        a.setName(rs.getString("name"));
	        a.setType(rs.getString("type"));
	        list.add(a);
	      }
	    }

	  } catch (SQLException e) {
	    e.printStackTrace();
	  }

	  return list;
	}

}
