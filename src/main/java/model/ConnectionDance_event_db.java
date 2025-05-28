package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//  PostgreSQL データベースへの接続を管理するクラス
 
public class ConnectionDance_event_db implements AutoCloseable {
    private static final String URL = "jdbc:postgresql://localhost:5432/dance_event_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private Connection connection = null;

    
      //データベース接続を取得する。
      //既に接続済みの場合は再利用する。
     
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
            	Class.forName("org.postgresql.Driver");

                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e; // キャッチした例外を再スロー
            }
        }
        return this.connection;
    }

    
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.connection = null;
            }
        }
    }
}
