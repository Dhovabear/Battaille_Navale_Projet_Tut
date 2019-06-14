package game.scenes;

import game.Objects.IdentityCard;
import game.Objects.SoundLibrary;
import game.Objects.SpriteIndex;
import game.engine.Game;
import game.engine.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;

public class VSshow extends Scene {

    private IdentityCard j1;
    private IdentityCard j2;

    private int ystop = 203;
    private int speed = 10;

    private int vsPanYPos;

    public VSshow(){

    }

    @Override
    public void update() throws IOException, FontFormatException {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,p.getWidth(),p.getHeight());

        if(j1.getY() < ystop){
            j1.setY(j1.getY()+speed);
        }

        if(j2.getY() < ystop){
            j2.setY(j2.getY()+speed);
        }

        if(vsPanYPos > ystop+40){
            vsPanYPos -= (speed/1.5);
        }

        j1.draw(g, p);
        j2.draw(g,p);
        g.drawImage(SpriteIndex.vsPannel,(p.getWidth()/2) - (160/2),vsPanYPos,160,160,p);
    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        SoundLibrary.stopMusicMenu();
        j1 = new IdentityCard(38,-450,DrapeauNom.getDrapeauJoueur(),DrapeauNom.getNomJoueur());
        j2 = new IdentityCard(Game.fenetre.getWidth()-450,-450 , DrapeauNom.getDrapeauOrdi(),DrapeauNom.getNomOrdi());
        vsPanYPos = 700;
        SoundLibrary.playJingleDebutPartie();
        Thread wait = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Game.switchScene(5);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        };
        wait.start();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {

    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }


    @Override
    public void exitEvent() {
        j1 = null;
        j2 = null;
    }
}
