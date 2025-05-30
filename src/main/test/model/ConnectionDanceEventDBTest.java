package model;

import java.sql.Connection;

public class ConnectionDanceEventDBTest {

    public static void main(String[] args) {
        // ① JDBCドライバが読み込めるか
        loadJDBC();
        
        // ② データベースに接続できるか
        connectToDB();
        
        // ③ コネクションを閉じられるか
        closeConnection();
    }
    
    /**
     * ① JDBCドライバの読み込みができるかのテスト
     */
    private static void loadJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("① JDBCドライバの読み込みに成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("① JDBCドライバの読み込みに失敗");
            e.printStackTrace();
        }
    }
    
    /**
     * ② データベースに接続できるかのテスト
     */
    private static void connectToDB() {
        try (ConnectionDance_event_db db = new ConnectionDance_event_db()) {
            Connection con = db.getConnection();
            if (con != null && !con.isClosed()) {
                System.out.println("② データベースへの接続に成功！");
            } else {
                System.out.println("② データベースへの接続に失敗");
            }
        } catch (Exception e) {
            System.out.println("② データベースへの接続中に例外が発生");
            e.printStackTrace();
        }
    }
    
    /**
     * ③ コネクションのクローズ動作のテスト
     */
    private static void closeConnection() {
        try {
            ConnectionDance_event_db db = new ConnectionDance_event_db();
            Connection con = db.getConnection();
            if (con != null && !con.isClosed()) {
                System.out.println("③ 接続確立済み");
            }
            db.close();
            if (con.isClosed()) {
                System.out.println("③ 接続のクローズに成功！");
            } else {
                System.out.println("③ 接続のクローズに失敗");
            }
        } catch (Exception e) {
            System.out.println("③ コネクションのクローズ中に例外が発生");
            e.printStackTrace();
        }
    }
}
