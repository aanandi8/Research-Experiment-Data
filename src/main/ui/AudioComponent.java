package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioComponent {
    Clip clip;

    public void setFile(String soundName) {

        try {
            File file = new File(soundName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
            while (!clip.isRunning()) {
                Thread.sleep(10);
            }
            while (clip.isRunning()) {
                Thread.sleep(10);
            }
            clip.close();

        } catch (Exception e) {
            // programming error
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
