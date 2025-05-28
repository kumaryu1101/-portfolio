package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ConnectionDance_event_db;
import model.dto.UsersDTO;

public class UsersDAO {
	
	private ConnectionDance_event_db connection;
	
	//idからUserを検索
	public UsersDTO findByid(int id) throws ClassNotFoundException {
		UsersDTO user = new UsersDTO();
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from users where id = ?");
			
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();//select文実行
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setDancerName(rs.getString("dancername"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	//select文 idで検索 または、usernameで検索？(かぶらないようにSQLで制約をつけているから)
	public void selectUser(UsersDTO user) throws SQLException, ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from users where id = ?");
			
			pstmt.setInt(1,user.getId());
			ResultSet rs = pstmt.executeQuery();//select文実行
			if(rs.next()) {
				System.out.println("ID:"+ rs.getInt("id") +"/ ユーザー名:"+rs.getString("username")+"/ ダンサー名:"+ rs.getString("dancername"));
			}else {
				System.out.println("IDが間違っているか、登録されていません");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insert文 idは自動追加のため、user名とdancer名を受け取り登録
	public  void intsertUser(String username, String dancername) throws SQLException, ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("insert into users values (? , ?)");
			
			pstmt.setString(1, username);
			pstmt.setString(2, dancername);
			pstmt.executeUpdate();//更新系の実行
			System.out.println("insertしました");
			
			pstmt = con.prepareStatement("select * from users where username = ? and dancername = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, dancername);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("あなたの名前とIDです");
				System.out.println("[ID:"+ rs.getInt("id") +"/ ユーザー名:"+rs.getString("username")+"/ ダンサー名:"+ rs.getString("dancername]"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete文 id検索して削除
	public void deleteUser(UsersDTO user) throws SQLException, ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from users where id = ?");
			
			pstmt.setInt(1, user.getId());
			pstmt.executeUpdate();
			System.out.println("deleteしました");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update 基本dancernameのみを想定
	public void updateUser(UsersDTO user ,String dancername) throws ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("update users set dancername = ? where id = ?");
			
			pstmt.setString(1, dancername);
			pstmt.setInt(2, user.getId());
			pstmt.executeUpdate();
			System.out.println("updateしました");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}	

}
