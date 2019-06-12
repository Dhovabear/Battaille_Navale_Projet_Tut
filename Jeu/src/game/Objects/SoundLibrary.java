package game.Objects;

import game.engine.Son;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SoundLibrary {
    public static Son musicMenu;
    public static Son clickSound;


    public static void loadSounds() throws IOException, UnsupportedAudioFileException {

        musicMenu = new Son("/sons/menuSong.wav",true);
        clickSound = new Son("/sons/clic.wav",false);
    }

    public static void playClickSound(){
        Son tmp = new Son("/sons/clic.wav",false);
        tmp.start();
    }
}
