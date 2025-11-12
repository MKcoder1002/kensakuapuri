package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
  private int id;
  private String name;
  private String category;
  private List<Allergen> allergens = new ArrayList<>();// 商品に紐づくアレルゲン一覧

  // --- Getter / Setter ---
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }

  public List<Allergen> getAllergens() { return allergens; }
  public void setAllergens(List<Allergen> allergens) { this.allergens = allergens; }
}
