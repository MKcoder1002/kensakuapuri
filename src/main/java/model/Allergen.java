package model;

public class Allergen {
  private int allergenId;
  private String name;
  private String type;

  // getter/setter
  public int getAllergenId() { return allergenId; }
  public void setAllergenId(int id) { this.allergenId = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getType() { return type; }
  public void setType(String type) { this.type = type; }
}
