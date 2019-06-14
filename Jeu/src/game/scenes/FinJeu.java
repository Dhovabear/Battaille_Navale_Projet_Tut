package game.scenes;

import game.Objects.PoliceIndex;
import game.Objects.SoundLibrary;
import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;

public class FinJeu extends Scene {

    private static boolean isJoueurWin;


    private BouttonSansFond m_bouttonRetourMenu;


    public FinJeu(){

    }

    @Override
    public void update() throws IOException, FontFormatException {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());

        g.setFont(PoliceIndex.bitcrusher.deriveFont(100f));
        if(isJoueurWin){
            g.setColor(new Color(255, 179, 41));
            g.drawString("Bravo !",(p.getWidth()/2) - (g.getFontMetrics(g.getFont()).stringWidth("Bravo !")/2),250);
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString("Vous avez réussit le mode " + DifficulteOrdi.getTexteDifficulte(),(p.getWidth()/2) - (g.getFontMetrics(g.getFont()).stringWidth("Vous avez vaincu le mode " + DifficulteOrdi.getTexteDifficulte())/2),300);
        }else{
            g.setColor(Color.RED);
            g.drawString("Perdu !",(p.getWidth()/2) - (g.getFontMetrics(g.getFont()).stringWidth("Perdu !")/2),250);
        }

        m_bouttonRetourMenu.draw(g, p);

    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        if(isJoueurWin){
            SoundLibrary.startVictorySon();
        }else {
        }

        m_bouttonRetourMenu = new BouttonSansFond(Game.fenetre.getWidth()-150,Game.fenetre.getHeight()-100,50,"Continuer"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(0);
            }
        };

    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        m_bouttonRetourMenu.checkMouse(e, typeOfInput);
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {

    }

    public static void setIsJoueurWin(boolean state){
        isJoueurWin = state;
    }

}
