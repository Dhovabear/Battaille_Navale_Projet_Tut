package game.scenes.tutorials;

import game.Objects.SoundLibrary;
import game.Objects.SpriteIndex;
import game.engine.Game;
import game.engine.GenerateurDeParticules;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.SplashText;
import game.scenes.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExplicationsModes extends Scene {


    private BouttonSansFond bouttonRetour;
    private BouttonSansFond bouttonModeClassique;
    private BouttonSansFond bouttonModeTirsSpeciaux;
    private BouttonSansFond bouttonModeIle;
    private BouttonSansFond bouttonimpacts;
    private int choixMode = 0;
    private BufferedImage m_titrePart1;
    private BufferedImage m_titrePart2;

    public ExplicationsModes() throws IOException, FontFormatException {
        bouttonModeClassique = new BouttonSansFond(22,120,40,"Mode Classique"){
            @Override
            public void action() throws  IOException, FontFormatException{
                //Game.switchScene(10);
                setEnabled(true);
                choixMode = 1;
            }
        };
        bouttonModeIle = new BouttonSansFond(22,320,40,"Mode Ile"){
            @Override
            public void action() throws  IOException, FontFormatException{
                //Game.switchScene(10);
                setEnabled(true);
                choixMode = 3;
            }
        };
        bouttonRetour = new BouttonSansFond(22,520,40,"Retour"){
            @Override
            public void action() throws  IOException, FontFormatException{
                Game.switchScene(10);
                setEnabled(true);
            }
        };
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());

        Menu.fond.draw(g,p);
        bouttonModeIle.draw(g,p);
        bouttonModeClassique.draw(g,p);
        bouttonRetour.draw(g,p);
        if(choixMode==1) {
            g.drawImage(SpriteIndex.classique, 300, 225, 850, 100, Color.WHITE, p);
        }
        if(choixMode==2) {
            g.drawImage(SpriteIndex.tirsSpeciaux, 300, 225, 850, 100, Color.WHITE, p);
        }
        if(choixMode==3) {
            g.drawImage(SpriteIndex.ile, 300, 225, 850, 100, Color.WHITE, p);
        }

    }

    @Override
    public void startEvent() {
       //astuce.setM_posX(Game.fenetre.getWidth()-500);
        bouttonModeClassique.setEnabled(true);
        bouttonModeIle.setEnabled(true);
        bouttonRetour.setEnabled(true);
        //SoundLibrary.playJingleDebutPartie();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        bouttonRetour.checkMouse(e, typeOfInput);
        bouttonModeClassique.checkMouse(e, typeOfInput);
        bouttonModeIle.checkMouse(e, typeOfInput);

    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
    }
}
