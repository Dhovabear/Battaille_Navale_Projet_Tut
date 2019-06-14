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

    public static Son currentVictoryMusic;

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

    public static boolean isMusicMenuRunning(){
        return currentMenuMusic != null;
    }

    public static void stopMusicMenu(){
        try {
            currentMenuMusic.stop();
        }catch (NullPointerException e) {
        }
        currentMenuMusic = null;
    }

    public static void startGameMusic(){
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

    public static void startVictorySon(){
        currentVictoryMusic  = new Son("/sons/victory.wav",false);
        currentVictoryMusic.start();
    }

    public static void stopVictorySon(){
        try {
            currentVictoryMusic.stop();
        }catch (NullPointerException e){
        }
        currentVictoryMusic = null;
    }

   /* public  void playDefaiteSon(){
        Son tmp;
        int hasard = (int)(Math.random() * 5);
        System.out.println(hasard);
        switch(hasard){

            case 0:
                tmp = new Son("/sons/01_AS_TIME_GOES_BY_A_t_chapman_HIBOU597_LC06881.wav",true);
                tmp.start();
                break;
            case 1:
                tmp = new Son("/sons/13_MONDAY_F_a_custer_HIB357_LC06881.wav",true);
                tmp.start();
                break;
            case 2:
                tmp = new Son("/sons/16_TUESDAY_B_g_shess_HIB357_LC06881.wav",true);
                tmp.start();
                break;
            case 3:
                tmp = new Son("/sons/21_HEARTGUITARS_g_sandeur_HIB07_LC06881.wav",true);
                tmp.start();
                break;
            case 4:
                tmp = new Son("/sons/30_PIANISSIMA_SIX_A_p_brejean_HIBOU597_LC06881.wav",true);
                tmp.start();
                break;
        }
    }*/



}
