package game.scenes;

import game.Objects.SoundLibrary;
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
import java.io.IOException;

public class Menu extends Scene {

    private BouttonSansFond bouttonJouer;
    private BouttonSansFond bouttonAide;
    private BouttonSansFond bouttonScores;
    private BouttonSansFond bouttonOptions;
    private BouttonSansFond bouttonQuitter;
    private BouttonSansFond bouttonCredits;

    public static GenerateurDeParticules fond;
    private SplashText astuce;

    private BufferedImage m_titrePart1;
    private BufferedImage m_titrePart2;

    public Menu() throws IOException, FontFormatException {
        bouttonJouer = new BouttonSansFond(22 ,240,40,"Jouer"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(2);
                setEnabled(true); //Ligne qui corrigera le bug du "spasme" quand on reviens
            }
        };
        bouttonAide = new BouttonSansFond(22,300,40,"Aide"){
            @Override
            public void action() throws IOException, FontFormatException {
                //Switch vers aide
            }
        };
        bouttonOptions = new BouttonSansFond(22,360,40,"Options");
        bouttonQuitter = new BouttonSansFond(22,480,40,"Quitter"){
            @Override
            public void action() {
                Game.quit();
            }
        };
        bouttonCredits = new BouttonSansFond(22,420,40,"Crédits");
        fond = new GenerateurDeParticules(0, 0,0,0,100,100,
                ImageIO.read(Game.class.getResourceAsStream("/images/bulle_1.png")));
        SplashText.LoadSplash();
        astuce = new SplashText(0,500);

        m_titrePart1 = ImageIO.read(getClass().getResourceAsStream("/images/TitrePart1.png"));
        m_titrePart2 = ImageIO.read(getClass().getResourceAsStream("/images/TitrePart2.png"));
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(new Color(188, 209, 255));
        g.fillRect(0,0,p.getWidth(),p.getHeight());
        fond.draw(g,p);
        bouttonJouer.draw(g,p);
        bouttonOptions.draw(g,p);
        bouttonCredits.draw(g,p);
        bouttonQuitter.draw(g,p);
        bouttonAide.draw(g, p);
        astuce.draw(g);
        g.drawImage(m_titrePart1,300,20,300,150,p);
        g.drawImage(m_titrePart2,620,20,300,150,p);

    }

    @Override
    public void startEvent() {
        fond.setM_ypos(Game.fenetre.getHeight()-1);
        fond.setM_heigth(1);
        fond.setM_width(Game.fenetre.getWidth());
        astuce.setM_posX(Game.fenetre.getWidth()-500);
        bouttonOptions.setEnabled(false);
        bouttonCredits.setEnabled(false);
        bouttonAide.setEnabled(false);
        astuce.reRollText();

        if(!SoundLibrary.isMusicMenuRunning()){
            SoundLibrary.startMusicMenu();
        }

        //SoundLibrary.playJingleDebutPartie();
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        bouttonJouer.checkMouse(e,typeOfInput);
        bouttonOptions.checkMouse(e,typeOfInput);
        bouttonCredits.checkMouse(e,typeOfInput);
        bouttonQuitter.checkMouse(e,typeOfInput);
        bouttonAide.checkMouse(e, typeOfInput);
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
    }
}
