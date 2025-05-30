package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.dto.EntryListDTO;

	class EntryListDTOTest {

	    static class EventDTO {} // Dummy class for testing
	    static class UsersDTO {} // Dummy class for testing

	    @Test
	    void testDefaultConstructor() {
	        EntryListDTO entry = new EntryListDTO();
	        assertNull(entry.getEvent());
	        assertNull(entry.getUser());
	        assertEquals(0, entry.getPoints());
	    }

		/*    @Test
		    void testConstructorWithEventAndUser() {
		        EventDTO event = new EventDTO();
		        UsersDTO user = new UsersDTO();
		//	        EntryListDTO entry = new EntryListDTO(event, user);
		        assertEquals(event, entry.getEvent());
		        assertEquals(user, entry.getUser());
		        assertEquals(0, entry.getPoints());
		    }
		
		    @Test
		    void testConstructorWithEventUserAndPoints() {
		        EventDTO event = new EventDTO();
		        UsersDTO user = new UsersDTO();
		        EntryListDTO entry = new EntryListDTO(event, user, 50);
		        assertEquals(event, entry.getEvent());
		        assertEquals(user, entry.getUser());
		        assertEquals(50, entry.getPoints());
		    }
		
		    @Test
		    void testSetAndGetEvent() {
		        EntryListDTO entry = new EntryListDTO();
		        EventDTO event = new EventDTO();
		        entry.setEvent(event);
		        assertEquals(event, entry.getEvent());
		    }
		
		    @Test
		    void testSetAndGetUser() {
		        EntryListDTO entry = new EntryListDTO();
		        UsersDTO user = new UsersDTO();
		        entry.setUser(user);
		        assertEquals(user, entry.getUser());
		    }*/

	    @Test
	    void testSetAndGetPoints() {
	        EntryListDTO entry = new EntryListDTO();
	        entry.setPoints(99);
	        assertEquals(99, entry.getPoints());
	    }
	}
