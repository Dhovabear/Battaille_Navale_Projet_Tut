package game.scenes;

import game.Objects.PoliceIndex;
import game.Objects.Score;
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

    private static boolean isJoueurWin = true;


    private BouttonSansFond m_bouttonRetourMenu;
    private String messageScore;
    private String messageComplementaire;


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

        if(JoueurVsOrdi.getSelectionMode() == 1){
            g.drawString(messageScore,(p.getWidth()/2) - (g.getFontMetrics(g.getFont()).stringWidth(messageScore)/2) , 350);
            g.drawString(messageComplementaire,(p.getWidth()/2) - (g.getFontMetrics(g.getFont()).stringWidth(messageComplementaire)/2) , 400);
        }


        m_bouttonRetourMenu.draw(g, p);

    }

    @Override
    public void startEvent() throws IOException, FontFormatException {

        if(isJoueurWin){
            SoundLibrary.startVictorySon();
            messageScore = "Et votre score est de: ";
        }else {
            SoundLibrary.startPerduSon();
            messageScore = "Néamoins votre score est de: ";
        }

        m_bouttonRetourMenu = new BouttonSansFond(Game.fenetre.getWidth()-150,Game.fenetre.getHeight()-100,50,"Continuer"){
            @Override
            public void action() throws IOException, FontFormatException {
                Game.switchScene(0);
            }
        };

        if(JoueurVsOrdi.getSelectionMode() != 1){
            return;
        }

        int score = ((Jouer)Game.sceneIndex[6]).getScore();

        messageScore += score;

        int pos = Menu.scoreboard.addScore(new Score(DrapeauNom.getIdDrapeauJoueur(),DrapeauNom.getNomJoueur(),score),DifficulteOrdi.getDifficulteOrdi()-1);

        switch (pos){
            case 0:
                messageComplementaire = "Félicitation vous avez la première place au classement de ce mode !";
                break;
            case 1:
                messageComplementaire = "Félicitation vous avez la seconde place au classement de ce mode !";
                break;
            case 2:
                messageComplementaire = "Félicitation vous avez la troisième place au classement de ce mode !";
                break;
            case 3:
                messageComplementaire = "Félicitation vous avez la quatrième place au classement de ce mode !";
                break;
            case 4:
                messageComplementaire = "Félicitation vous avez la cinquième place au classement de ce mode !";
                break;
             default:
                 messageComplementaire = "Dommage vous n'êtes pas dans le classement !";
                 break;
        }
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
        SoundLibrary.stopVictorySon();
        SoundLibrary.startPerduSon();
    }

    public static void setIsJoueurWin(boolean state){
        isJoueurWin = state;
    }

}
