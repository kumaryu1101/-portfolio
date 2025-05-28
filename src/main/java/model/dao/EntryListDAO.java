package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionDance_event_db;
import model.dto.EntryListDTO;
import model.dto.EventDTO;
import model.dto.UsersDTO;

public class EntryListDAO {
    private ConnectionDance_event_db connection;


//    // すべてのエントリー者を表示
//    //イベントID,イベント名、ダンサーID、ダンサー名、ポイント
//    public List<EntryListDTO> selectAll() throws SQLException, ClassNotFoundException {
//        List<EntryListDTO> entryList = new ArrayList<>();
//
//        String sql = "select "
//        		+ "    e.id AS event_id,\n"
//        		+ "    e.name AS event_name,\n"
//        		+ "    u.id AS user_id,\n"
//        		+ "    u.dancer_name,\n"
//        		+ "    el.points\n"
//        		+ "FROM \n"
//        		+ "    entry_list el\n"
//        		+ "INNER JOIN \n"
//        		+ "    events e ON el.event_id = e.id\n"
//        		+ "INNER JOIN \n"
//        		+ "    users u ON el.user_id = u.id; ";
//        try (Connection con = connection.getConnection();
//             PreparedStatement pstmt = con.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                EventDTO event = new EventDTO();
//                event.setId(rs.getInt("event_id"));
//
//                UsersDTO user = new UsersDTO();
//                user.setId(rs.getInt("user_id"));
//
//                EntryListDTO entry = new EntryListDTO(event, user);
//                entry.setPoints(rs.getInt("points"));
//
//                entryList.add(entry);
//            }
//        }
//
//        return entryList;
//    }
    
    //引数をもらいその数だけポイント上位者を表示する
    public List<EntryListDTO> selectTopEntryList(int eventId, int limit) throws SQLException, ClassNotFoundException {
        List<EntryListDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                e.id AS event_id,
                e.name AS event_name,
                u.id AS user_id,
                u.dancer_name,
                el.points
            FROM 
                entrylist el
            INNER JOIN 
                events e ON el.event_id = e.id
            INNER JOIN 
                users u ON el.user_id = u.id
            WHERE 
                e.id = ?
            ORDER BY 
                el.points DESC
            LIMIT ?
        """;

        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EventDTO event = new EventDTO();
                    event.setId(rs.getInt("event_id"));
                    event.setName(rs.getString("event_name"));

                    UsersDTO user = new UsersDTO();
                    user.setId(rs.getInt("user_id"));
                    user.setDancerName(rs.getString("dancer_name"));

                    EntryListDTO entry = new EntryListDTO(event, user);
                    entry.setPoints(rs.getInt("points"));

                    result.add(entry);
                }
            }
        }

        return result;
    }


    // エントリーを追加
    //イベントidとユーザーのidを渡し、エントリーする
    public void insertEntry(EntryListDTO entry) throws SQLException, ClassNotFoundException {
        String sql = "insert into entrylist (event_id, dancer_id) values (?, ?)";
        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, entry.getEvent().getId());
            pstmt.setInt(2, entry.getUser().getId());

            pstmt.executeUpdate();
            System.out.println("エントリーを登録しました。");
        }
    }

    // ポイントを更新
    //ポイントの追加がちょっとわかんない
    public void updatePoints(EntryListDTO entry) throws SQLException, ClassNotFoundException {
        String sql = "update entrylist set points = points + ? where event_id = ? and dancer_id = ?";
        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, entry.getPoints());
            pstmt.setInt(2, entry.getEvent().getId());
            pstmt.setInt(3, entry.getUser().getId());

            pstmt.executeUpdate();
            System.out.println("ポイントを追加しました。");
        }
    }

    // エントリーを削除
    public void deleteEntry(EntryListDTO entry) throws SQLException, ClassNotFoundException {
        String sql = "delete from entrylist where event_id = ? and dancer_id = ?";
        try (Connection con = connection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, entry.getEvent().getId());
            pstmt.setInt(2, entry.getUser().getId());

            pstmt.executeUpdate();
            System.out.println("エントリーを削除しました。");
        }
    }
    
    // 指定したイベントのすべてのダンサー名を取得（）
    public List<EntryListDTO> selectEntryList(EventDTO eventdto) throws SQLException, ClassNotFoundException {
        List<EntryListDTO> entryList = new ArrayList<>();

        String sql = """
              SELECT u.id AS dancer_id, u.dancername, 
           e.name AS event_name, 
           e.id AS event_id, 
           e.event_date AS date, 
           e.organizer_name AS organizerName, 
           e.comment
		    FROM entrylist el
		    INNER JOIN users u ON el.dancer_id = u.id
		    INNER JOIN events e ON el.event_id = e.id
		    WHERE el.event_id = ?
        """;

        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, eventdto.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // EventDTOの作成
                    EventDTO event = new EventDTO();
                    event.setId(rs.getInt("event_id"));
                    event.setName(rs.getString("event_name"));
                    event.setDate(rs.getDate("date"));
                    event.setOrganizerName(rs.getString("organizerName"));
                    event.setComment(rs.getString("comment"));

                    // UsersDTOの作成
                    UsersDTO user = new UsersDTO();
                    user.setId(rs.getInt("dancer_id"));
                    user.setDancerName(rs.getString("dancername"));

                    // EntryListDTOに格納
                    EntryListDTO entry = new EntryListDTO(event, user);
                    entryList.add(entry);
                }
            }
        }

        return entryList;
    }
    //指定したイベントの結果表示
    public List<EntryListDTO> resultEntryList(int eventId) throws SQLException, ClassNotFoundException {
        List<EntryListDTO> result = new ArrayList<>();

        String sql = """
            SELECT 
                e.id AS event_id,
                e.name AS event_name,
                u.id AS dancer_id,
                u.dancername,
                el.points
            FROM 
                entrylist el
            INNER JOIN 
                events e ON el.event_id = e.id
            INNER JOIN 
                users u ON el.dancer_id = u.id
            WHERE 
                e.id = ?
            ORDER BY 
                el.points DESC;
        """;

        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    
            pstmt.setInt(1, eventId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EventDTO event = new EventDTO();
                    event.setId(rs.getInt("event_id"));
                    event.setName(rs.getString("event_name"));

                    UsersDTO user = new UsersDTO();
                    user.setId(rs.getInt("dancer_id"));
                    user.setDancerName(rs.getString("dancername"));

                    EntryListDTO entry = new EntryListDTO(event, user);
                    entry.setPoints(rs.getInt("points"));

                    result.add(entry);
                }
            }
        }

        return result;
    }
    
    // 指定したイベントのユーザーが重複してないか （重複していたらTrueを返す)
    public boolean checkEntryList(EventDTO eventdto, UsersDTO userdto) throws SQLException, ClassNotFoundException {
        boolean isCheck = false;

        String sql = """
              SELECT u.id AS dancer_id  
		    FROM entrylist el
		    INNER JOIN users u ON el.dancer_id = u.id
		    INNER JOIN events e ON el.event_id = e.id
		    WHERE el.event_id = ?
        """;

        try(ConnectionDance_event_db db = new ConnectionDance_event_db()) {
		    Connection con = db.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, eventdto.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	if(rs.getInt("dancer_id") == userdto.getId()) {
                		isCheck = true;
                	} 
                }
            }
        }

        return isCheck;
    }
}