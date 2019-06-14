package game.Objects;

import game.engine.Son;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SoundLibrary {
    public static Son musicMenu;
    public static Son clickSound;
    public static Son ploufSound;


    public static Son currentMenuMusic;
    public static Son currentGameMusic;

    public static void loadSounds() throws IOException, UnsupportedAudioFileException {

        musicMenu = new Son("/sons/menuSong.wav",true);
        clickSound = new Son("/sons/clic.wav",false);
        ploufSound = new Son("/sons/plouf.wav",false);
    }

    public static void playClickSound(){
        Son tmp = new Son("/sons/clic.wav",false);
        tmp.start();
    }

    public static void playJingleDebutPartie(){
        Son tmp = new Son("/sons/jingle.wav",false);
        tmp.start();
    }

    public static void playPloufSong(){
        Son tmp = new Son("/sons/plouf.wav",false);
        tmp.start();
    }

    public static void playMissileSon(){
        Son tmp = new Son("/sons/missile.wav",false);
        tmp.start();
    }

    public static void playBoomSon(){
        Son tmp = new Son("/sons/boom.wav",false);
        tmp.start();
    }

    public static void startMusicMenu(){
        currentMenuMusic = new Son("/sons/menuSong.wav",true);
        currentMenuMusic.start();
    }

    public static void stopMusicMenu(){
        try {
            currentMenuMusic.stop();
        }catch (NullPointerException e) {
        }
        currentMenuMusic = null;
    }

    public static void playGameMusic(){
        currentGameMusic = new Son("/sons/musique.wav",true);
        currentGameMusic.start();
    }

    public static void stopGameMusic(){
        try{
            currentGameMusic.stop();
        }catch (NullPointerException e){

        }
        currentGameMusic = null;
    }

}
