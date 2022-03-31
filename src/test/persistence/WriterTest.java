package persistence;

import model.Experiment;
import model.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testParticipants.txt";
    private Writer testWriter;
    private Participant participant1;
    private Participant participant2;
    private ArrayList<Double> dataEntry;
    private Experiment expt;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        expt = new Experiment();
        testWriter = new Writer(new File(TEST_FILE));
        participant1 = new Participant("Rawn", 3);
        participant2 = new Participant("Chris", 6);
        expt = new Experiment();
        expt.addParticipant("Rawn",3);
        expt.addParticipant("Chris",6);

    }

    @Test
    void testWriteParticipant() {
        //save participant data to file
        testWriter.write(expt);
      //  testWriter.write(participant1);
        //testWriter.write(participant2);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Participant> participants = Reader.readParticipant(new File(TEST_FILE));
            Participant participant1 = participants.get(0);
            assertEquals(3, participant1.getId());
            assertEquals("Rawn", participant1.getName());
            assertEquals(0.0, participant1.getMean());
            assertEquals("Rawn",expt.getNamesOfParticipants().get(0));
            assertEquals(3,expt.getListOfParticipants().get(0).getId());


            Participant participant2 = participants.get(1);
            assertEquals(6, participant2.getId());
            assertEquals("Chris", participant2.getName());
            assertEquals(0.0, participant2.getMean());
            assertEquals("Chris",expt.getNamesOfParticipants().get(1));
            assertEquals(6,expt.getListOfParticipants().get(1).getId());



//            // verify that ID of next account created is 3 (checks that nextAccountId was restored)
//            Account next = new Account("Chris", 0.00);
//            assertEquals(3, next.getId());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
