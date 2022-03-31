package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipantTest {
    Participant p;
    Participant q;
    Participant r;

    @BeforeEach
    void runBefore() {
        p = new Participant("Laura", 20);
        q = new Participant("Krool", 30);
        r = new Participant("Jacob", 200);
    }

    @Test
    void testConstructor() {
        assertEquals(0,p.getMean());
        assertEquals(0,p.viewData(20));
        assertEquals("Laura",p.getName());
        assertEquals(20,p.getId());
    }

    @Test
    void  testgetName() {

        assertTrue("Laura".equals(p.getName()));
        assertFalse("Laura".equals(q.getName()));
    }

    @Test
    void testaddEntry() {
        assertTrue(0==q.getMean());
        assertEquals(0,p.getMean());

        assertTrue(0==p.getMean());
        assertTrue(0==r.getMean());

        p.addEntry(20);

        assertTrue(20==p.getMean());
        assertTrue(0==q.getMean());

        p.addEntry(40);

        assertTrue(30==p.getMean());
    }

    @Test
   void testviewData() {
        assertEquals(0, p.viewData(20));
        p.addEntry(20);
        assertEquals(20, p.viewData(20));

        p.addEntry(40);

        assertEquals(30, p.viewData(20));
        assertEquals(0, q.viewData(20));
    }

    @Test
    void testgetMean() {
        assertTrue(0==q.getMean());
        assertTrue(0==p.getMean());
        assertTrue(0==r.getMean());

        p.addEntry(20);

        assertTrue(20==p.getMean());
        assertTrue(0==q.getMean());

    }

    @Test
    void testtoString() {
        assertTrue(p.toString().contains("id = 20, name = Laura"));
    }
}
