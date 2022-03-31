package ui;

import model.Experiment;
import model.Participant;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

// Understood concepts from Teller Application

//Experimenter Application
public class ExperimenterApplication {
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private static final int WIDTH = 510;
    private static final int HEIGHT = 530;

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

    private static Scanner input;
    private static Experiment expt;

    JFrame frame1 = new JFrame("Experimenter Application");
    static JPopupMenu popupMenu1 = new JPopupMenu();
    static JPopupMenu popupMenu2 = new JPopupMenu();

    static JTextField text1 = new JTextField();
    static JTextField text2 = new JTextField();
    static JTextField text3 = new JTextField();
    JLabel label1 = new JLabel("Input name:");
    JLabel label2 = new JLabel("Input id:");
    JLabel label3 = new JLabel("Input Data:");
    protected static JButton button1 = new JButton("Add Participant");
    static JButton button2 = new JButton("Remove Participant");
    static JButton button3 = new JButton("Input DataEntry");
    static JButton button4 = new JButton("View Names");
    static JButton button5 = new JButton("View Mean");
    static JButton button6 = new JButton("Save Participant");
    MouseMovement soundMovement = new MouseMovement();
    static AudioComponent audio = new AudioComponent();
    static String clickSound;


    private static ArrayList<Double>  dataEntry;
    private Participant participant1;
    private Participant participant2;
    private Participant participant;


    // EFFECTS: runs the teller application
    public ExperimenterApplication() {
        expt = new Experiment();
        participant = new Participant("",0);
        graphicalInterface();
        runExpt();
    }

    //EFFECTS: sets bounds for labels, textbox and buttons on JFrame
    public void setBounds() {
        label1.setBounds(50, 50, 150, 30);
        label2.setBounds(50, 120, 150, 30);
        label3.setBounds(50, 190, 150, 30);
        text1.setBounds(200,50, 150, 50);
        text2.setBounds(200,110, 150, 50);
        text3.setBounds(200,170, 150, 50);
        button1.setBounds(50,250, 150,50);
        button2.setBounds(250,250, 150,50);
        button3.setBounds(50,(HEIGHT / 5) * 3, 150,50);
        button4.setBounds(250,(HEIGHT / 5) * 3, 150,50);
        button5.setBounds(50,380, 150,50);
        button6.setBounds(250,380, 150,50);
    }

    //EFFECTS: set location of popupMenu
    public void addSetLocation() {
        popupMenu1.setLocation(0,520);
        popupMenu2.setLocation(250,490);
    }

    //EFFECTS: adds mouse activity for each button
    public void addMouseActivity() {
        button1.addMouseListener(new MouseMovement());
        button2.addMouseListener(new MouseMovement());
        button3.addMouseListener(new MouseMovement());
        button4.addMouseListener(new MouseMovement());
        button5.addMouseListener(new MouseMovement());
        button6.addMouseListener(new MouseMovement());
    }

    //EFFECTS: add sound to use of buttons
    public void addActionListener() {
        button1.addActionListener(soundMovement);
        button2.addActionListener(soundMovement);
        button3.addActionListener(soundMovement);
        button4.addActionListener(soundMovement);
        button5.addActionListener(soundMovement);
        button6.addActionListener(soundMovement);
    }

    //EFFECTS: adds components to JFrame and sets the graphical interface
    public void graphicalInterface() {
        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        frame1.add(button4);
        frame1.add(button5);
        frame1.add(button6);
        frame1.add(label1);
        frame1.add(label2);
        frame1.add(text1);
        frame1.add(text2);
        frame1.add(label3);
        frame1.add(text3);
        frame1.setSize(WIDTH,HEIGHT);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.add(popupMenu1);
        frame1.add(popupMenu2);
        setBounds();
        addMouseActivity();
        addSetLocation();
        addActionListener();
        clickSound = "./data/shotgun-reload-mossberg590-RA_The_Sun_God-1398444554.wav";
    }



    // MODIFIES: this
    // EFFECTS: processes user input
    private void runExpt() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        loadParticipants();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadParticipants() {
        try {
            List<Participant> participants = Reader.readParticipant(new File(ACCOUNTS_FILE));
            participant1 = participants.get(0);
            expt.getListOfParticipants().addAll(participants);

        } catch (IOException e) {
            init();
        }
    }

    private void init() {
        participant1 = new Participant("Josh",23);
        participant2 = new Participant("Ron",34);

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Participant");
        System.out.println("\tb -> remove Participant");
        System.out.println("\tt -> Input data for a participant");
        System.out.println("\tw -> view Participants names");
        System.out.println("\ts -> save participants' data to file");
        System.out.println("\td -> view Participants' mean");
        System.out.println("\tq -> quit");
    }



    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            add();
        } else if (command.equals("b")) {
            remove();
        } else if (command.equals("t")) {
            inputData();
        } else if (command.equals("w")) {
            viewParticipantNames();
        } else if (command.equals("d")) {
            viewParticipantData();
        } else if (command.equals("s")) {
            saveParticipant();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    //EFFECTS: saves state of participant data to ACCOUNT_FILE
    protected static void saveParticipant() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(expt);
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a Participant to an experiment
    protected static void add() {
        String text = text1.getText();
        int id = Integer.parseInt(text2.getText());

        if (id >= 0) {
            expt.addParticipant(text, id);
        } else {
            System.out.println("Need a Positive Integer Value");
        }
        printParticipants(expt);
    }

    //REQUIRES: Participant with name and id is already present in expt
    // MODIFIES: this
    // EFFECTS: removes a Participant from an experiment
    protected static void remove() {
        String text = text1.getText();
        int id = Integer.parseInt(text2.getText());
        if (id >= 0) {
            expt.removeParticipant(text,id);
        } else {
            System.out.println("Need an Integer Value");
        }
        printParticipants(expt);
    }


    // EFFECTS: displays all Participant's names in the expt
    protected static void viewParticipantNames() {
        ArrayList<String> names = expt.getNamesOfParticipants();
        String listOfNames = "Participant names are:\n" + names;

        if (popupMenu1.isVisible()) {
            popupMenu1.remove(0);
        }
        popupMenu1.add(listOfNames);
        popupMenu1.setVisible(true);

        executor.schedule(() -> {
            popupMenu1.setVisible(false);
            popupMenu1.remove(0);
        }, 5, TimeUnit.SECONDS);

        System.out.printf("Participant names are:%s\n", names);
    }

    //REQUIRES: ID>= 0 and id has to be for participant that has already been added
    // EFFECTS: view Participants mean in an  experiment
    protected static void viewParticipantData() {
        int id = Integer.parseInt(text2.getText());
        Participant p = expt.findParticipantbyId(id);
        String meanOfParticipant = "Mean of participant:\n" + p.viewData(id);
        popupMenu2.add(meanOfParticipant);
        popupMenu2.setVisible(true);

        executor.schedule(() -> {
            popupMenu2.setVisible(false);
            popupMenu2.remove(0);
        }, 5, TimeUnit.SECONDS);

        System.out.printf("Mean of a participant:%.2f\n", p.viewData(id));
    }

    //REQUIRES: id has to be for participant that has already been added and
    // EFFECTS: input a data into a particular participant in an expt
    protected static void inputData() {
        dataEntry = new ArrayList<>();
        int id = Integer.parseInt(text2.getText());
        Participant p = expt.findParticipantbyId(id);
        double entry = Integer.parseInt(text3.getText());
        p.addEntry(entry);
    }


    // EFFECTS: prints names of participants in an expt
    private static void printParticipants(Experiment expt) {
        System.out.printf("Participants are:%s\n", expt.getNamesOfParticipants());
    }

}
