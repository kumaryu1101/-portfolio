package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventDTOTest {
    private EventDTO event;

    @BeforeEach
    void setUp() {
        event = new EventDTO();
    }

    /**
     * デフォルトコンストラクタの初期状態をテストする。
     * 全フィールドが初期値（nullまたは0）であることを確認する。
     */
    @Test
    void testDefaultConstructor() {
        assertEquals(0, event.getId());
        assertNull(event.getName());
        assertNull(event.getDate());
        assertNull(event.getOrganizerName());
        assertNull(event.getComment());
    }

    /**
     * IDと名前を指定するコンストラクタの動作をテストする。
     * フィールドが正しく初期化されることを確認する。
     */
    @Test
    void testConstructorWithIdAndName() {
        EventDTO event = new EventDTO(1, "Test Event");
        assertEquals(1, event.getId());
        assertEquals("Test Event", event.getName());
    }

    /**
     * 全フィールドを受け取るコンストラクタの動作をテストする。
     * すべてのフィールドが正しく初期化されることを確認する。
     */
    @Test
    void testConstructorWithAllFields() {
        String name = "Event Name";
        Date date = new Date();
        String organizer = "Organizer";
        String comment = "This is a comment.";

        EventDTO event = new EventDTO(name, date, organizer, comment);
        assertEquals(name, event.getName());
        assertEquals(date, event.getDate());
        assertEquals(organizer, event.getOrganizerName());
        assertEquals(comment, event.getComment());
    }

    /**
     * セッターとゲッターの動作をテストする。
     * それぞれのセッターで設定した値が、ゲッターで正しく取得できることを確認する。
     */
    @Test
    void testSettersAndGetters() {
        int id = 10;
        String name = "Sample Event";
        Date date = new Date();
        String organizer = "Organizer Name";
        String comment = "Event comment";

        event.setId(id);
        event.setName(name);
        event.setDate(date);
        event.setOrganizerName(organizer);
        event.setComment(comment);

        assertEquals(id, event.getId());
        assertEquals(name, event.getName());
        assertEquals(date, event.getDate());
        assertEquals(organizer, event.getOrganizerName());
        assertEquals(comment, event.getComment());
    }
}
