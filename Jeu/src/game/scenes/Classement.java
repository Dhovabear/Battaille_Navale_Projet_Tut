package game.scenes;

import game.Objects.PoliceIndex;
import game.Objects.Score;
import game.Objects.SpriteIndex;
import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;

public class Classement extends Scene {
    private Label titreFac;
    private Label titreMoy;
    private Label titreDur;
    private BouttonSansFond boutRetour;


    public Classement() throws IOException, FontFormatException {
        titreFac = new Label(250,80,40f, PoliceIndex.bitcrusher,"Facile");
        titreMoy = new Label(550,80,40f, PoliceIndex.bitcrusher,"Moyen");
        titreDur = new Label(850,80,40f, PoliceIndex.bitcrusher,"Difficile");
        boutRetour = new BouttonSansFond(5,510,50,"Retour"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(0);
                boutRetour.setEnabled(true);
            }
        };
        boutRetour.setEnabled(true);
    }

    @Override
    public void update() throws IOException, FontFormatException {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());
        Menu.fond.draw(g, p);

        titreFac.draw(g, p);
        titreMoy.draw(g, p);
        titreDur.draw(g, p);

        boutRetour.draw(g, p);

        g.setFont(PoliceIndex.autoradio.deriveFont(20f));
        for (int i = 0; i < Menu.scoreboard.getEasyScore().size() ; i++) {
            g.drawImage(SpriteIndex.imagesFlags[Menu.scoreboard.getEasyScore().get(i).getFlagId()],180,90+(i*100) + 10,50,25,p);
            if(Menu.scoreboard.getEasyScore().get(i).getName().length() > 12){
                g.drawString(Menu.scoreboard.getEasyScore().get(i).getName().substring(12),180 +60 ,90+(i*100) + 30);
            }else{
                g.drawString(Menu.scoreboard.getEasyScore().get(i).getName(),180 +60 ,90+(i*100) + 30);
            }

            g.drawString(Integer.toString(Menu.scoreboard.getEasyScore().get(i).getScore()),180 +60 + 125,90+(i*100) + 30);
        }

        for (int i = 0; i < Menu.scoreboard.getMoyenScore().size() ; i++) {
            g.drawImage(SpriteIndex.imagesFlags[Menu.scoreboard.getMoyenScore().get(i).getFlagId()],490,90+(i*100) + 10,50,25,p);
            if(Menu.scoreboard.getMoyenScore().get(i).getName().length() > 12){
                g.drawString(Menu.scoreboard.getMoyenScore().get(i).getName().substring(12),490 +60 ,90+(i*100) + 30);
            }else{
                g.drawString(Menu.scoreboard.getMoyenScore().get(i).getName(),490 +60 ,90+(i*100) + 30);
            }
            g.drawString(Integer.toString(Menu.scoreboard.getMoyenScore().get(i).getScore()),490 +60 + 125,90+(i*100) + 30);
        }

        for (int i = 0; i < Menu.scoreboard.getDifficileScore().size() ; i++) {
            g.drawImage(SpriteIndex.imagesFlags[Menu.scoreboard.getDifficileScore().get(i).getFlagId()],790,90+(i*100) + 10,50,25,p);
            if(Menu.scoreboard.getDifficileScore().get(i).getName().length() > 12){
                g.drawString(Menu.scoreboard.getDifficileScore().get(i).getName().substring(12),790 +60 ,90+(i*100) + 30);
            }else{
                g.drawString(Menu.scoreboard.getDifficileScore().get(i).getName(),790 +60 ,90+(i*100) + 30);
            }
            g.drawString(Integer.toString(Menu.scoreboard.getDifficileScore().get(i).getScore()),790 +60 + 125,90+(i*100) + 30);
        }

    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        boutRetour.checkMouse(e, typeOfInput);
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {

    }
}
