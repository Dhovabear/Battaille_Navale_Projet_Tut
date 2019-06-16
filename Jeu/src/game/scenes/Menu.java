package game.scenes;

import game.Objects.PauseMenu;
import game.Objects.Scoreboard;
import game.Objects.SoundLibrary;
import game.Objects.SpriteIndex;
import game.engine.Game;
import game.engine.GenerateurDeParticules;
import game.engine.Scene;
import game.engine.ui.BouttonImage;
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

    public static Scoreboard scoreboard;
    private BouttonSansFond bouttonJouer;
    private BouttonSansFond bouttonAide;
    private BouttonSansFond bouttonScores;
    private BouttonSansFond bouttonScore;
    private BouttonSansFond bouttonQuitter;
    private BouttonSansFond bouttonCredits;

    public static GenerateurDeParticules fond;
    public static PauseMenu pause;
    private SplashText astuce;

    private BufferedImage m_titrePart1;
    private BufferedImage m_titrePart2;

    private BouttonImage muteButton;

    public Menu() throws IOException, FontFormatException {
        scoreboard = Scoreboard.loadScores();
        scoreboard.printScores();
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
                Game.switchScene(10);
            }
        };
        bouttonScore = new BouttonSansFond(22,360,40,"Scores"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(13);
            }
        };
        bouttonQuitter = new BouttonSansFond(22,480,40,"Quitter"){
            @Override
            public void action() {
                Game.quit();
            }
        };
        bouttonCredits = new BouttonSansFond(22,420,40,"Crédits"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(9);
                SoundLibrary.stopMusicMenu();
            }
        };
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
        bouttonScore.draw(g,p);
        bouttonCredits.draw(g,p);
        bouttonQuitter.draw(g,p);
        bouttonAide.draw(g, p);
        astuce.draw(g);
        muteButton.draw(g, p);
        g.drawImage(m_titrePart1,300,20,300,150,p);
        g.drawImage(m_titrePart2,620,20,300,150,p);

    }

    @Override
    public void startEvent() {
        fond.setM_ypos(Game.fenetre.getHeight()-1);
        fond.setM_heigth(1);
        fond.setM_width(Game.fenetre.getWidth());
        astuce.setM_posX(Game.fenetre.getWidth()-500);
        bouttonScore.setEnabled(true);
        bouttonCredits.setEnabled(true);
        bouttonAide.setEnabled(true);
        astuce.reRollText();
        pause = new PauseMenu();

        BufferedImage img;
        if(SoundLibrary.isMute()){
            img = SpriteIndex.bouttonMuteDisabled;
        }else{
            img = SpriteIndex.bouttonMuteEnabled;
        }

        muteButton = new BouttonImage(1125,510,50,50, img,SpriteIndex.bouttonMuteDisabled){
            @Override
            public void action() throws IOException, FontFormatException {
                SoundLibrary.setMute(!SoundLibrary.isMute());

                if(SoundLibrary.isMute()){
                    this.setEnabledImage(SpriteIndex.bouttonMuteDisabled);
                }else{
                    this.setEnabledImage(SpriteIndex.bouttonMuteEnabled);
                    if(!SoundLibrary.isMusicMenuRunning()){
                        SoundLibrary.startMusicMenu();
                    }
                }
            }
        };
        muteButton.setEnabled(true);
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
        bouttonScore.checkMouse(e,typeOfInput);
        bouttonCredits.checkMouse(e,typeOfInput);
        bouttonQuitter.checkMouse(e,typeOfInput);
        bouttonAide.checkMouse(e, typeOfInput);
        muteButton.checkMouse(e, typeOfInput);
    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {

    }

    @Override
    public void exitEvent() {
    }
}
