package game.scenes;

import game.Objects.SoundLibrary;
import game.Objects.SpriteIndex;
import game.engine.Game;
import game.engine.GenerateurDeParticules;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.SplashText;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Regles extends Scene {


    private BouttonSansFond bouttonRetour;

    private SplashText astuce;

    private BufferedImage m_titrePart1;
    private BufferedImage m_titrePart2;

    public Regles() throws IOException, FontFormatException {
        bouttonRetour = new BouttonSansFond(22,520,40,"Retour"){
            @Override
            public void action() throws  IOException, FontFormatException{
                Game.switchScene(10);
                setEnabled(true);
            }
        };
        SplashText.LoadSplash();
        astuce = new SplashText(0,500);


    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());
        Menu.fond.draw(g,p);
        g.drawImage(SpriteIndex.regles,20,20,800,500,Color.WHITE,p);
        bouttonRetour.draw(g,p);
        g.drawImage(m_titrePart1,300,20,300,150,p);
        g.drawImage(m_titrePart2,620,20,300,150,p);

    }

    @Override
    public void startEvent() {
        astuce.setM_posX(Game.fenetre.getWidth()-500);
        bouttonRetour.setEnabled(true);
        astuce.reRollText();
        //SoundLibrary.playJingleDebutPartie();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        bouttonRetour.checkMouse(e, typeOfInput);

    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
    }
}
