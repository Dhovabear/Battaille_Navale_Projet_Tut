package game.scenes.tutorials;

import game.Objects.SoundLibrary;
import game.engine.Game;
import game.engine.GenerateurDeParticules;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.SplashText;
import game.scenes.*;
import game.scenes.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChoixAide extends Scene {

    private BouttonSansFond bouttonAideRegles;
    private BouttonSansFond bouttonAideModes;
    private BouttonSansFond bouttonAideDragAndDrop;
    private BouttonSansFond bouttonOptions;
    private BouttonSansFond bouttonQuitter;
    private BouttonSansFond bouttonCredits;
    private BouttonSansFond bouttonRetour;


    private SplashText astuce;

    private BufferedImage m_titrePart1;
    private BufferedImage m_titrePart2;

    public ChoixAide() throws IOException, FontFormatException {
        bouttonAideRegles = new BouttonSansFond(22 ,120,40,"Voir les règles du jeu"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(11);
                setEnabled(true); //Ligne qui corrigera le bug du "spasme" quand on reviens
            }
        };
        bouttonAideModes = new BouttonSansFond(22,220,40,"Voir les explications des différents modes de jeux"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(12);
                setEnabled(true); //Ligne qui corrigera le bug du "spasme" quand on reviens
            }
        };
        bouttonAideDragAndDrop = new BouttonSansFond(22,320,40,"Aller au menu des tutoriels"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(11);
                setEnabled(true); //Ligne qui corrigera le bug du "spasme" quand on reviens
            }
        };
        bouttonRetour = new BouttonSansFond(22,520,40,"Retour"){
            @Override
            public void action() throws  IOException, FontFormatException{
                Game.switchScene(0);
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
        bouttonAideModes.draw(g,p);
        bouttonAideRegles.draw(g,p);
        //bouttonAideDragAndDrop.draw(g,p);
        bouttonRetour.draw(g,p);
        g.drawImage(m_titrePart1,300,20,300,150,p);
        g.drawImage(m_titrePart2,620,20,300,150,p);

    }

    @Override
    public void startEvent() {
        astuce.setM_posX(Game.fenetre.getWidth()-500);
        //bouttonAideDragAndDrop.setEnabled(true);
        bouttonAideModes.setEnabled(true);
        bouttonAideRegles.setEnabled(true);
        bouttonRetour.setEnabled(true);
        astuce.reRollText();
        //SoundLibrary.playJingleDebutPartie();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        bouttonAideRegles.checkMouse(e,typeOfInput);
        bouttonAideModes.checkMouse(e,typeOfInput);
        bouttonAideDragAndDrop.checkMouse(e,typeOfInput);
        bouttonRetour.checkMouse(e, typeOfInput);

    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
    }
}
