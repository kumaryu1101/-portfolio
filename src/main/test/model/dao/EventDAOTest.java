package model.dao;

import java.util.ArrayList;
import java.util.Date;

import model.dto.EventDTO;

public class EventDAOTest {

    public static void main(String[] args) throws Exception {
        EventDAOTest test = new EventDAOTest();

        //① findById メソッドのテスト開始
        test.testFindById();
        System.out.println("① テスト終了\n");

        //② selectEvent メソッドのテスト開始
        test.testSelectEvent();
        System.out.println("② テスト終了\n");

        //③ insertEvent メソッドのテスト開始
        test.testInsertEvent();
        System.out.println("③ テスト終了\n");
    }
    
    
    
    

    // ① IDからイベントを検索するテスト
    public void testFindById() throws Exception {
        EventDAO dao = new EventDAO();
        int testId = 1; // 存在するイベントIDをセットしてください

        EventDTO event = dao.findById(testId);
        if (event != null && event.getId() == testId) {
            System.out.println("イベント取得成功: ID=" + event.getId() + ", Name=" + event.getName() + ", Organizer=" + event.getOrganizerName());
        } else {
            System.out.println("イベント取得失敗: ID=" + testId + "は存在しません");
        }
    }

    // ② 全イベント取得のテスト
    public void testSelectEvent() throws Exception {
        EventDAO dao = new EventDAO();
        ArrayList<EventDTO> events = dao.selectEvent();

        System.out.println("取得イベント数: " + events.size());
        for (EventDTO event : events) {
            System.out.println("ID=" + event.getId() + ", Name=" + event.getName());
        }
    }

    // ③ イベント登録のテスト
    public void testInsertEvent() throws Exception {
        EventDAO dao = new EventDAO();
        String name = "テストイベント";
        Date date = new Date(); // 現在日時
        String organizer = "テスト主催者";
        String comment = "テストコメント";

        dao.insertEvent(name, date, organizer, comment);
        System.out.println("insert処理完了");
        
        
    }

}
