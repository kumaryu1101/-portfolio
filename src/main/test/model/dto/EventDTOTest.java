package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import model.dto.EventDTO;

class EventDTOTest {

    @Test
    void testDefaultConstructor() {
        EventDTO event = new EventDTO();
        assertEquals(0, event.getId());
        assertNull(event.getName());
        assertNull(event.getDate());
        assertNull(event.getOrganizerName());
        assertNull(event.getComment());
    }

    @Test
    void testConstructorWithIdAndName() {
        EventDTO event = new EventDTO(1, "Dance Party");
        assertEquals(1, event.getId());
        assertEquals("Dance Party", event.getName());
        assertNull(event.getDate());
        assertNull(event.getOrganizerName());
        assertNull(event.getComment());
    }

    @Test
    void testConstructorWithNameDateOrganizerComment() {
        Date date = new Date();
        EventDTO event = new EventDTO("EventX", date, "Alice", "Fun event");
        assertEquals(0, event.getId());
        assertEquals("EventX", event.getName());
        assertEquals(date, event.getDate());
        assertEquals("Alice", event.getOrganizerName());
        assertEquals("Fun event", event.getComment());
    }

    @Test
    void testSettersAndGetters() {
        EventDTO event = new EventDTO();
        Date date = new Date();
        event.setId(5);
        event.setName("Sample Event");
        event.setDate(date);
        event.setOrganizerName("Bob");
        event.setComment("Sample comment");

        assertEquals(5, event.getId());
        assertEquals("Sample Event", event.getName());
        assertEquals(date, event.getDate());
        assertEquals("Bob", event.getOrganizerName());
        assertEquals("Sample comment", event.getComment());
    }
}
