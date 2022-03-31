package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExperimentTest {
    Experiment e;

    Participant q = new Participant("Krool", 30);
    Participant r = new Participant("Jacob", 200);
    Participant p = new Participant("Laura", 20);


    @BeforeEach
    void runBefore() {
        e = new Experiment();
    }

    @Test
    void testConstructor() {
        assertTrue(0== e.getNumOfParticipants());

    }

    @Test
    void testgetNumOfParticipants() {
        assertTrue(0==e.getNumOfParticipants());


        e.addParticipant("Laura", 20);
        assertEquals(1,e.getNumOfParticipants());

        e.removeParticipant("Laura", 20);
        assertEquals(0, e.getNumOfParticipants());
    }

    @Test
    void testaddParticipant() {
        e.addParticipant("Laura", 20);
        e.addParticipant("Krool", 30);

        assertEquals(2,e.getNumOfParticipants());

        e.addParticipant("Laura", 30);
        assertEquals(2,e.getNumOfParticipants());

        e.addParticipant("Laura", 20);
        assertEquals(2,e.getNumOfParticipants());

        e.addParticipant("Luke", 20);
        assertEquals(2,e.getNumOfParticipants());

    }

    @Test
    void testremoveParticipant() {
        e.addParticipant("Laura", 20);
        assertEquals(1,e.getNumOfParticipants());

        e.removeParticipant("John", 20);
        assertEquals(1,e.getNumOfParticipants());

        e.removeParticipant("Laura", 30);
        assertEquals(1,e.getNumOfParticipants());

        e.removeParticipant("Laura", 20);
        assertEquals(0,e.getNumOfParticipants());
    }

    @Test

    void testgetNamesOfParticipants() {
        e.addParticipant("Laura", 20);
        e.addParticipant("John", 30);
        e.addParticipant("Stamos", 200);

        assertEquals(3, e.getNumOfParticipants());
        assertEquals("Laura", e.getNamesOfParticipants().get(0));
        assertEquals("John", e.getNamesOfParticipants().get(1));
        assertEquals("Stamos", e.getNamesOfParticipants().get(2));


    }

    @Test
    void testgetListofParticipants() {
        e.addParticipant("Laura", 20);
        e.addParticipant("John", 30);
        e.addParticipant("Stamos", 200);

        assertEquals("Laura", e.getListOfParticipants().get(0).getName());
        assertEquals("John", e.getListOfParticipants().get(1).getName());
        assertEquals("Stamos", e.getListOfParticipants().get(2).getName());

        assertFalse("Laura"== e.getListOfParticipants().get(1).getName());

    }

    @Test
    void testfindParticipantbyId() {
        e.addParticipant("Laura", 20);
        e.addParticipant("John", 30);
        e.addParticipant("Stamos", 200);

        assertEquals("John", e.findParticipantbyId(30).getName());
        assertEquals("Not found", e.findParticipantbyId(0).getName());

    }

}