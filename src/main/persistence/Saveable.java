package persistence;

import java.io.PrintWriter;

public interface Saveable {
    //MODIFES: printWriter
    //EFFECTS: writes the saveable to printWriter

    void save(PrintWriter printWriter);

}
