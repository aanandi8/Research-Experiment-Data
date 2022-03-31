package persistence;

import model.Participant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read participant data to file
public class Reader {
    public static final String SEPARATOR = ",";

    // EFFECTS: returns a list of participants parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Participant> readParticipant(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of participants parsed from list of strings
    // where each string contains data for one account
    private static List<Participant> parseContent(List<String> fileContent) {
        List<Participant> participants = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            participants.add(parseAccount(lineComponents));
        }

        return participants;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(SEPARATOR);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 3 where element 0 represents
    // the id, elements 2 represents the name and element 3 represents
    // the mean of the participant
    // EFFECTS: returns an account constructed from components
    private static Participant parseAccount(List<String> components) {
        int id = Integer.parseInt(components.get(0));
        String name = components.get(1);
        double mean = Double.parseDouble(components.get(2));
        return new Participant(id, name, mean);
    }
}
