package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

//Represents a participant's name, id and their mean performance
public class Participant implements Saveable {
    private String name;
    private int id;
    private ArrayList<Double> dataEntry;
    private int numOfDataPoints;
    private double totalData;
    protected double mean;

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: name of participant is set to name and different from every participant; experiment id is a
     *          positive integer not assigned to any other participant;
     */
    public Participant(String name, int id) {
        this.name = name;
        this.id = id;
        dataEntry = new ArrayList<>();
    }

    /*
    * REQUIRES: name is non-zero length, id>=  0
    * EFFECTS: constructs participant with id, name and mean,
    * NOTE: this constructor is to be used only when constructing
     * an account from data stored in file
     */
    public Participant(int id, String name, double mean) {
        this(name, id);
        this.mean = mean;
    }

    //MODIFES: this
    //EFFECTS: add a data into a list of data entries of a participant
    public void addEntry(double data) {
        dataEntry.add(data);
        numOfDataPoints++;
        totalData = totalData + data;
        mean = totalData / (double)numOfDataPoints;

    }


    //EFFECTS: returns name of participant
    public String getName() {
        return name;
    }

    //EFFECTS: returns id of participant
    public int getId() {
        return id;
    }


    //EFFECTS:
    public double getMean() {
        return mean;
    }

    //EFFECTS: to be able to view a participant's mean upon checking for its id
    public Double viewData(int id) {
        Double find = 0.0;
        if (id == getId()) {
            find = getMean();
        }
        return find;
    }

    /*
     * EFFECTS: returns a string representation of participant
     */
    @Override
    public String toString() {
        String meanStr = String.format("%.2f",mean);  // get mean to 2 decimal places as a string
        return "[ id = " + id + ", name = " + name + ", "
                + "mean = $" + meanStr + "]";
    }


    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(id);
        printWriter.print(Reader.SEPARATOR);
        printWriter.print(name);
        printWriter.print(Reader.SEPARATOR);
        printWriter.println(mean);
    }
}
