package model;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;

//Represents an experiment with list of participants
public class Experiment implements Saveable {
    private ArrayList<Participant> part;


    //EFFECTS: Instantiates an empty experiment
    public Experiment() {
        part = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a participant to experiment where n is the name of the participant and num
    // is the id of the participant; if participant with same n or num has  previously been added to the experiment
    // then it doesn't get added to the experiment
    public void addParticipant(String n, int num) {
        boolean findPart = false;
        Participant p;
        if (part.size() == 0) {
            p = new Participant(n, num);
            part.add(p);
        } else {
            for (Participant e : part) {
                if ((!n.equals(e.getName())) && (num != e.getId())) {
                    findPart = true;
                } else {
                    findPart = false;
                    break;
                }
            }
            if (findPart) {
                p = new Participant(n, num);
                part.add(p);
            }
        }

    }

    //REQUIRES: list of participants in experiment is not empty
    //MODIFIES: this
    //EFFECTS: participant with name n and id num is removed  from the  list of  participants
    //present in experiment, if participant  with name n and  id num is not included in the list
    // then it does nothing
    public void removeParticipant(String n, int num) {
        Participant toRemove = null;
        for (Participant e : part) {
            if ((n.equals(e.getName())) && (num == e.getId())) {
                toRemove = e;
                break;
            }
        }
        if (toRemove != null) {
            part.remove(toRemove);
        }
    }

    //EFFECTS: returns number of participants in the experiment
    public int getNumOfParticipants() {
        return part.size();
    }



    //EFFECTS: return list of names of all participants in the experiment
    public ArrayList getNamesOfParticipants() {
        ArrayList<String> names = new ArrayList<String>();
        for (Participant e : part) {
            names.add(e.getName());
        }
        return names;
    }

    public ArrayList<Participant> getListOfParticipants() {
        return part;
    }


    public Participant findParticipantbyId(Integer id) {
        for (Participant p: part) {
            if (id == p.getId()) {
                return p;
            }
        }
        return new Participant("Not found", 0);
    }


    @Override
    public void save(PrintWriter printWriter) {
        for (Participant participant : part) {
            participant.save(printWriter);
        }
    }
}
