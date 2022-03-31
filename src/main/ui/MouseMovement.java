package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMovement extends MouseAdapter implements ActionListener {


    //EFFECTS: override mousePressed method to use in gui
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == ExperimenterApplication.button1) {
            ExperimenterApplication.add();
        } else if (e.getSource() == ExperimenterApplication.button2) {
            ExperimenterApplication.remove();
        } else if (e.getSource() == ExperimenterApplication.button3) {
            ExperimenterApplication.inputData();
        } else if (e.getSource() == ExperimenterApplication.button4) {
            ExperimenterApplication.viewParticipantNames();
        } else if (e.getSource() == ExperimenterApplication.button5) {
            ExperimenterApplication.viewParticipantData();
        } else if (e.getSource() == ExperimenterApplication.button6) {
            ExperimenterApplication.saveParticipant();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExperimenterApplication.audio.setFile(ExperimenterApplication.clickSound);
        ExperimenterApplication.audio.play();
    }
}
