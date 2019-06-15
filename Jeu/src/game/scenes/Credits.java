package game.scenes;

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

public class Credits extends Scene {


    private int yPos;
    private int speed;

    public Credits(){
        speed = 1;
    }

    @Override
    public void update() throws IOException, FontFormatException {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,p.getWidth(),p.getHeight());

        if(yPos > -SpriteIndex.credits.getHeight() + 500){
            yPos -= speed;
        }
        g.drawImage(SpriteIndex.credits,(p.getWidth()/2)-(SpriteIndex.credits.getWidth()/2),yPos,p);
    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        yPos = Game.fenetre.getHeight();
        SoundLibrary.playCreditSong();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        if(typeOfInput != "mP"){
            return;
        }
        try {
            Game.switchScene(0);
            SoundLibrary.stopCreditSon();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (FontFormatException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
        SoundLibrary.stopCreditSon();
    }
}
