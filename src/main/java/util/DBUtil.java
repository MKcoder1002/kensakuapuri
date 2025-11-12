package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            // JDBCドライバのロード
            Class.forName("org.h2.Driver");
            System.out.println("DB接続開始");
            // H2DBへの接続（ファイルモード）
            return DriverManager.getConnection("jdbc:h2:~/desktop/h2db/arerugen", "sa", "");
        } catch (ClassNotFoundException e) {
        	System.out.println("JDBCドライバ読み込み失敗");
            throw new SQLException("JDBCドライバのロードに失敗しました", e);
        }
    }
}
