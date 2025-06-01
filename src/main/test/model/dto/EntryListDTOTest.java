package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntryListDTOTest {

    private EntryListDTO entry;

    @BeforeEach
    void setUp() {
        entry = new EntryListDTO();
    }

    /**
     * デフォルトコンストラクタの初期状態をテストする。
     * event, user は null、points は 0 であることを確認。
     */
    @Test
    void testDefaultConstructor() {
        assertNull(entry.getEvent());
        assertNull(entry.getUser());
        assertEquals(0, entry.getPoints());
    }

    /**
     * event と user をセットするコンストラクタの動作をテストする。
     * points は初期値 0 であることも確認。
     */
    @Test
    void testConstructorWithEventAndUser() {
        EventDTO event = new EventDTO(1, "Test Event");
        UsersDTO user = new UsersDTO("testuser", "testdancer");

        EntryListDTO entry = new EntryListDTO(event, user);

        assertEquals(event, entry.getEvent());
        assertEquals(user, entry.getUser());
        assertEquals(0, entry.getPoints());
    }

    /**
     * event, user, points をセットするコンストラクタの動作をテストする。
     */
    @Test
    void testConstructorWithAllFields() {
        EventDTO event = new EventDTO(2, "Another Event");
        UsersDTO user = new UsersDTO("user2", "dancer2");
        int points = 10;

        EntryListDTO entry = new EntryListDTO(event, user, points);

        assertEquals(event, entry.getEvent());
        assertEquals(user, entry.getUser());
        assertEquals(points, entry.getPoints());
    }

    /**
     * セッターとゲッターの動作をテストする。
     * セッターでセットした値が正しくゲッターで取得できることを確認。
     */
    @Test
    void testSettersAndGetters() {
        EventDTO event = new EventDTO(3, "Set Event");
        UsersDTO user = new UsersDTO("setuser", "setdancer");
        int points = 7;

        entry.setEvent(event);
        entry.setUser(user);
        entry.setPoints(points);

        assertEquals(event, entry.getEvent());
        assertEquals(user, entry.getUser());
        assertEquals(points, entry.getPoints());
    }
}
