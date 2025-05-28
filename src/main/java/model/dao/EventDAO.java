package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.ConnectionDance_event_db;
import model.dto.EventDTO;

public class EventDAO {
	
	private ConnectionDance_event_db connection ;
	
	//IDのみでイベントを検索するため
	public EventDTO findById(int id) throws Exception {
		EventDTO event = new EventDTO();
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from events where id = ?");
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();//select文実行
			if(rs.next()) {
		            event.setId(rs.getInt("id"));
		            event.setName(rs.getString("name"));
		            event.setDate(rs.getTimestamp("event_date"));
		            event.setOrganizerName(rs.getString("organizer_name"));
		            event.setComment(rs.getString("comment"));   
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return event;
		
	}
	
	//select文 すべてのイベント表示
	public ArrayList<EventDTO> selectEvent() throws SQLException, ClassNotFoundException {
		ArrayList<EventDTO> events = new ArrayList<>();
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from events");
			
			ResultSet rs = pstmt.executeQuery();//select文実行
			while(rs.next()) {
				 EventDTO event = new EventDTO();
		            event.setId(rs.getInt("id"));
		            event.setName(rs.getString("name"));
		            event.setDate(rs.getTimestamp("event_date"));
		            event.setOrganizerName(rs.getString("organizer_name"));
		            event.setComment(rs.getString("comment"));

		            events.add(event);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	//insert文　イベント登録
	public  void insertEvent(String name, Date date, String organizername, String comment) throws SQLException, ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("insert into events(name,event_date,organizer_name,comment) values (?,?,?,?)");
			
			pstmt.setString(1, name);
			Timestamp ts = new Timestamp(date.getTime());
			pstmt.setTimestamp(2, ts);
			
			pstmt.setString(3, organizername);
			pstmt.setString(4, comment);
			pstmt.executeUpdate();//更新系の実行
			System.out.println("insertしました");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//引数eventDTOのインサート
	public void insertEvent(EventDTO event) throws Exception {
		insertEvent(event.getName(), event.getDate(),event.getOrganizerName(),event.getComment());
	}
	
	
	//delete文 id検索して削除
	public void deleteEvent(EventDTO event) throws SQLException, ClassNotFoundException {
		try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from events where id = ?");
			
			pstmt.setInt(1, event.getId());
			pstmt.executeUpdate();
			System.out.println("deleteしました");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/*//update 基本dancernameのみを想定
	public void updateEvent(UsersDTO user ,String dancername) {
		try(Connection con = this.database.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("update users set dancername = ? where id = ?");
			
			pstmt.setString(1, dancername);
			pstmt.setInt(2, user.getId());
			pstmt.executeUpdate();
			System.out.println("updateしました");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}*/
}
