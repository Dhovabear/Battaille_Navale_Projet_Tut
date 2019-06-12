package game.scenes;

import game.Objects.DGBateau;
import game.Objects.Grid;
import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.ArrayList;

public class DragAndDrop extends Scene {

    //pour le drag and drop
    private boolean m_IntroDragFini = false;
    private int m_timeintroDrag = 0;
    private ArrayList<DGBateau> m_boatToPlace;
    private Grid grilleJoueur; //Grille personnelle du joueur
    private Grid grilleVisuJoueur; //Servira au joueur pour voir la grille initiale de l'ordi
    private Grid grilleOrdi; //Grille personnelle de l'ordi
    private Grid grilleVisuOrdi;
    private BouttonImage m_nextButton;

    private Font bitcrusher;

    public DragAndDrop() throws IOException, FontFormatException {
        bitcrusher = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/polices/bitcrusher.ttf"));
        grilleJoueur = new Grid(60,20,50,10,false);
        grilleVisuJoueur = new Grid(460,40,40,10,true);
        grilleOrdi = new Grid(0,0,40,10,false);
        grilleVisuOrdi  = new Grid(0,0,40,10,true);
        m_nextButton = new BouttonImage(950,470,205,65,"/images/bouttonContinuer1.png","/images/bouttonContinuer0.png"){
            @Override
            public void action() throws IOException, FontFormatException {
                System.out.println("Prot");
                for (DGBateau b:m_boatToPlace) {
                    System.out.println("it");
                    if(!grilleJoueur.addBateau(b.getgridX(),b.getgridY(),b.getRotation(),b.getType())){
                        System.out.println("Mon petit , TG");
                    }else{
                        System.out.println("OK MONSIEUR");
                    }
                }
                ((Jouer)Game.sceneIndex[6]).setGrids(grilleJoueur,grilleVisuJoueur,grilleOrdi,grilleVisuOrdi);
                Game.switchScene(6);
            }
        };

    }

    @Override
    public void update() throws IOException, FontFormatException {

    }

    @Override
    public void draw(Graphics g, JPanel p) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,p.getWidth(),p.getHeight());
        if(!m_IntroDragFini){
            if(m_timeintroDrag < 120){
                g.setColor(Color.BLACK);
                g.setFont(bitcrusher.deriveFont(60f));
                g.drawString("Placer vos bateaux!",450,300);
                m_timeintroDrag++;
            }else{
                m_IntroDragFini = true;
            }
            return;
        }

        grilleJoueur.draw(g,p);
        for (DGBateau b: m_boatToPlace) {
            b.draw(g,p);
        }

        m_nextButton.draw(g, p);

        boolean bienMit = true;
        for (DGBateau b:m_boatToPlace) {
            if(!b.estBienPlacer()){
                bienMit = false;
            }
        }

        if(bienMit){
            if(!m_nextButton.isEnabled()){
                System.out.println("Delock");
                m_nextButton.setEnabled(true);
            }
        }else{
            if(m_nextButton.isEnabled()){
                m_nextButton.setEnabled(false);
            }
        }


    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        grilleJoueur.remplissage();
        grilleVisuOrdi.setIsland(grilleJoueur.getIleId());
        grilleOrdi.remplissage();
        grilleVisuJoueur.setIsland(grilleOrdi.getIleId());

        m_boatToPlace = new ArrayList<DGBateau>();
        m_boatToPlace.add(new DGBateau(700,200,0,grilleJoueur));
        m_boatToPlace.add(new DGBateau(760,200,1,grilleJoueur));
        m_boatToPlace.add(new DGBateau(820,200,2,grilleJoueur));
        m_boatToPlace.add(new DGBateau(880,200,3,grilleJoueur));
        m_boatToPlace.add(new DGBateau(940,200,4,grilleJoueur));
        m_nextButton.setEnabled(true);
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
        m_nextButton.checkMouse(e, typeOfInput);
        if(typeOfInput == "mP"){
            if(e.getButton() == 1){
                for (DGBateau b : m_boatToPlace ) {
                    b.isIn(e);
                }
            }
            if(e.getButton() == 3){
                for (DGBateau b : m_boatToPlace ) {
                    b.rotate();
                }
            }
        }else if(typeOfInput == "mD"){
            for (DGBateau b : m_boatToPlace ) {
                b.drag(e);
            }
        }else if(typeOfInput == "mR"){
            for (DGBateau b : m_boatToPlace ) {
                b.stopDrag();
            }
        }

    }

    @Override
    public void mouseWheelInput(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0){
            for (DGBateau b: m_boatToPlace) {
                b.rotateBack();
            }
        }else if(e.getWheelRotation() > 0){
            for (DGBateau b: m_boatToPlace) {
                b.rotate();
            }
        }
    }

    @Override
    public void exitEvent() {

    }
}
