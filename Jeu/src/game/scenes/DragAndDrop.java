package game.scenes;

import game.Objects.DGBateau;
import game.Objects.Grid;
import game.engine.Scene;

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

    private Font bitcrusher;

    public DragAndDrop() throws IOException, FontFormatException {
        bitcrusher = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/polices/bitcrusher.ttf"));
        grilleJoueur = new Grid(60,40,50,10,false);
        grilleVisuJoueur = new Grid(460,40,40,10,true);
        grilleOrdi = new Grid(0,0,40,10,false);
        grilleVisuOrdi  = new Grid(0,0,40,10,true);
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
    }

    @Override
    public void startEvent() throws IOException, FontFormatException {
        grilleJoueur.remplissage();
        grilleOrdi.remplissage();

        m_boatToPlace = new ArrayList<DGBateau>();
        m_boatToPlace.add(new DGBateau(700,200,0,grilleJoueur));
        m_boatToPlace.add(new DGBateau(760,200,1,grilleJoueur));
        m_boatToPlace.add(new DGBateau(820,200,2,grilleJoueur));
        m_boatToPlace.add(new DGBateau(880,200,3,grilleJoueur));
        m_boatToPlace.add(new DGBateau(940,200,4,grilleJoueur));
    }

    @Override
    public void input(KeyEvent e, String typeOfInput) {

    }

    @Override
    public void mouseInput(MouseEvent e, String typeOfInput) {
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
