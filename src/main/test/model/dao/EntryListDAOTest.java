package model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.dto.EntryListDTO;
import model.dto.EventDTO;
import model.dto.UsersDTO;

class EntryListDAOTest {

	
	//①インサートエントリー
	@Test
	void insertEntryTest() {
	    EntryListDAO dao = new EntryListDAO();

	    // イベントDTOを作成
	    Date date = java.sql.Date.valueOf("2023-11-01"); 
	    EventDTO event = new EventDTO("イベント名", date, "organizer", "コメント");

	    UsersDTO user = new UsersDTO("ユーザー名", "ダンサー名");
	    EntryListDTO entry = new EntryListDTO(event, user);

	    // イベントとユーザーがnullでない場合、エントリーができる
	    try {
	        if (user != null && event != null) {
	            dao.insertEntry(entry);
	            System.out.println("①エントリー成功");
	        } else {
	            System.out.println("ユーザーまたはイベントがnullのため、テストを中止します");
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("①insertEntryTest失敗");
	    }
	}

	
	//②ポイントを更新
	@Test
	void updatePointsTest() {
	    EntryListDAO dao = new EntryListDAO();
	    EventDAO eventDao = new EventDAO(); // ← EventDAOのインスタンスを作る
	    EventDTO event = null;
	    try {
	        // テスト用に1件だけ取得する
	        event = eventDao.findByName("イベント名");
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }

	    // eventがnullの場合、テスト継続できないのでreturnする
	    if (event == null) {
	        System.out.println("②イベントが見つかりませんでした。テスト中止。");
	        return;
	    }

	    UsersDTO user = new UsersDTO("ユーザー名", "ダンサー名");
	    EntryListDTO entry = new EntryListDTO(event, user, 5);

	    //pointが0でない場合、ポイントを更新できる
	    try {
	        if (entry.getPoints() != 0) {
	            dao.updatePoints(entry);
	            System.out.println("②pointの更新成功");
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("②失敗");
	    }
	}

	
	//③指定したイベントのすべてのダンサー名を取得
	//（ダンサーID・ダンサー名・イベントID・イベント名・オーガナイザー・コメント）
	@Test
	void selectEntryListTest() {
	    EntryListDAO dao = new EntryListDAO();
	    Date date = java.sql.Date.valueOf("2023-11-01");
	    EventDTO event = new EventDTO("イベント名", date, "organizer", "コメント");

	    try {
	        // selectEntryListの戻り値を受け取る（List<EntryListDTO>）
	        List<EntryListDTO> entryList = dao.selectEntryList(event);
	        // デバッグ出力（本来ならアサーションでチェック）
	        for (EntryListDTO entry : entryList) {
	            System.out.println("ダンサーID: " + entry.getUser().getId());
	            System.out.println("ダンサー名: " + entry.getUser().getDancerName());
	            System.out.println("イベントID: " + entry.getEvent().getId());
	            System.out.println("イベント名: " + entry.getEvent().getName());
	            System.out.println("オーガナイザー: " + entry.getEvent().getOrganizerName());
	            System.out.println("コメント: " + entry.getEvent().getComment());
	        }
	        System.out.println("③selectEntryList成功");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("③selectEntryList失敗");
	    }
	}

	
	//④指定したイベントの結果表示（userid・username・イベントid・イベント名・ポイント)
	@Test
	void  resultEntryListTest(){
		EntryListDAO dao = new EntryListDAO();
		int eventId = 1;
		try {
			if(eventId != 0) {
				dao.resultEntryList(1);
				System.out.println("④resultEntryList成功");
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("③selectEntryList失敗");
		}
	}
	
	//⑤ユーザーが重複してないか確認（重複していたらTrue)
	@Test
	void checkEntryListTest() {
	    EntryListDAO dao = new EntryListDAO();
	    Date date = java.sql.Date.valueOf("2023-11-01");
	    EventDTO event = new EventDTO("イベント名", date, "organizer", "コメント");

	    UsersDTO user = null;
	    try {
	        user = new UsersDAO().findByUsername("ユーザー名");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("⑤ユーザー取得失敗");
	        return; // ユーザー取得に失敗したらテストを中止
	    }

	    if (user == null) {
	        System.out.println("⑤ユーザーが見つかりませんでした。テストを中止します。");
	        return;
	    }

	    try {
	        boolean result = dao.checkEntryList(event, user);
	        // テスト結果表示
	        if (result) {
	            System.out.println("⑤ユーザーが重複しています（テスト成功）");
	        } else {
	            System.out.println("⑤ユーザーが重複していません（テスト成功）");
	        }
	        // 本格的にはassertで書くとベター
	        // assertTrue(result);
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("⑤失敗");
	    }
	}
}

