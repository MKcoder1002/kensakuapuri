package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            // MySQL JDBC ドライバ
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL接続開始");

            String url = "jdbc:mysql://localhost:3306/arerugen";
            String user = "root";
            String password = "root"; 

            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBCドライバ読み込み失敗");
            throw new SQLException("MySQL JDBCドライバのロードに失敗しました", e);
        }
    }
}
