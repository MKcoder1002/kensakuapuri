package model;
import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String name; // ユーザー名
  private String password; // パスワード
  

  public int getId() {
	return id;
}
  public void setId(int Id) {
	this.id = Id;
  }
  public User() { }
  public User(String name, String pass) {
    this.name = name;
    this.password = pass;
  }
  public String getName() { return name; }
  

  public void setPass(String password) {
	this.password = password;
  }
  public void setName(String name) {
	this.name = name;
  }
  public String getPass() { return password; }
}