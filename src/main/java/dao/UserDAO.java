package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean login(String name, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement stmt =this.conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void register(User user) throws SQLException {
        String sql = "INSERT INTO users (name,password) VALUES (?, ?)";
        try (PreparedStatement stmt =this.conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPass());
            stmt.executeUpdate();
        }
    }
    
    public User findByNameAndPass(String name, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE NAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setPass(rs.getString("PASSWORD"));
                return user;
            }
            return null;  
        }
    }
}
