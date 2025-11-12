package model;
import java.io.Serializable;

public class User implements Serializable {
  private String name; // ユーザー名
  private String password; // パスワード

  public User() { }
  public User(String name, String pass) {
    this.name = name;
    this.password = pass;
  }
  public String getName() { return name; }
  public String getPass() { return password; }
}