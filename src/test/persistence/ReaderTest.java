package persistence;

import model.Participant;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testConstructor() {
        new Reader();
    }
    @Test
    void testParseAccountFile1() {
        try  {
            List<Participant> participants = Reader.readParticipant(new File("./data/testFile1.txt"));
            Participant participant1 = participants.get(0);
            assertEquals("Joe", participant1.getName());
            assertEquals(1, participant1.getId());
            assertEquals(4.0, participant1.getMean());

            Participant participant2 = participants.get(1);
            assertEquals("John Stamos", participant2.getName());
            assertEquals(2, participant2.getId());
            assertEquals(3.34, participant2.getMean());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
    @Test
    void testParseAccountFile2() {
        try  {
            List<Participant> participants = Reader.readParticipant(new File("./data/testParticipantFile2.txt"));
            Participant participant1 = participants.get(0);
            assertEquals("Joseph", participant1.getName());
            assertEquals(5, participant1.getId());
            assertEquals(2.98, participant1.getMean());

            Participant participant2 = participants.get(1);
            assertEquals("Louise", participant2.getName());
            assertEquals(7, participant2.getId());
            assertEquals(4.5, participant2.getMean());
        } catch (IOException e) {
            fail("IO exception should not have been thrown");
        }

    }
    @Test
    void testIOException() {
        try {
            Reader.readParticipant(new File("./path/does/not/exist/testAccount.txt")) ;
        } catch (IOException e) {
            // expected
        }
    }


}
