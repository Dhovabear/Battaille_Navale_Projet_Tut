package game.engine;

import game.Objects.SoundLibrary;

import java.io.*;
import java.util.zip.InflaterInputStream;
import javax.sound.sampled.*;
import javax.swing.*;

public class Son{

    private Clip c;
    private long tmcd;
    private String path;
    private AudioInputStream snd;
    private boolean isInLoop;


    public Son(String tpath , boolean loop){
        try{
            InputStream d = getClass().getResourceAsStream(tpath);
            snd = AudioSystem.getAudioInputStream(new BufferedInputStream(d));


            path = tpath;
            isInLoop = loop;
            tmcd = snd.getFrameLength();
            System.out.println(tmcd);
        }catch(Exception e){
            //System.out.println("Erreur lors du chargement de " + path + "verifier l'extension du fichier ou l'existence de ce dernier");
            e.printStackTrace();
        }
    }

    public Son(AudioInputStream input,boolean loop){
        try {
            snd = input;
            isInLoop = loop;
            tmcd = snd.getFrameLength();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){

        try {
            c = AudioSystem.getClip();
            c.addLineListener( new LineListener() {
                public void update(LineEvent evt) {
                    if (evt.getType() == LineEvent.Type.STOP) {

                        if(!isInLoop){
                            evt.getLine().close();
                            System.out.println("Closed !");
                        }else{
                            c.setMicrosecondPosition(0);
                            c.start();
                        }
                    }
                }
            });
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try{
            Son moi = this;
            c.open(snd);
            c.start();
        }catch(Exception e){

        }

    }

    public void resume(){
        c.start();
        if(isInLoop){
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void pause(){
        c.stop();
    }

    public void stop(){
        isInLoop = false;
        c.setMicrosecondPosition(c.getMicrosecondLength());
    }

    void reset() throws Exception{
        c.open(snd);
        if(isInLoop){
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

}