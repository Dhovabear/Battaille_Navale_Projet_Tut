package game.scenes;

import game.Objects.PoliceIndex;
import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Scène 2 - JoueurVsOrdi

public class JoueurVsOrdi extends Scene {

	private static int selectionMode;
	private Label m_titreMenu;

	private BouttonSansFond m_bouttonModeIle;
	private BouttonSansFond m_bouttonModeClassique;
	private BouttonSansFond m_bouttonRetour;

	public JoueurVsOrdi() throws IOException, FontFormatException {
		m_titreMenu = new Label(425,50,50, PoliceIndex.bitcrusher,"Chosissez un mode de jeu");
		m_bouttonModeClassique = new BouttonSansFond(350,250,40,"Classique"){
			@Override
			public void action() throws IOException, FontFormatException {
				selectionMode = 1;
				Game.switchScene(3);
			}
		};
		m_bouttonModeIle = new BouttonSansFond(650,250,40,"Iles"){
			@Override
			public void action() throws IOException, FontFormatException {
				selectionMode = 2;
				Game.switchScene(3);
			}
		};

		/*m_bouttonModeBateauTireur = new BouttonSansFond(650,250,40,"Bateaux tireurs"){
			@Override
			public void action() throws IOException, FontFormatException {
				selectionMode = 3;
				Game.switchScene(3);
			}
		};*/

		m_bouttonRetour = new BouttonSansFond(5,510,50,"Retour"){
			@Override
			public void action() throws IOException, FontFormatException {
				Game.switchScene(0);
			}
		};
	}

	public void update() throws IOException, FontFormatException {


		if(selectionMode != 0){
			if(selectionMode != -1){
				Game.switchScene(3); //Switch vers la scène DifficulteOrdi
			}
			else{
				Game.switchScene(1); //Retour au Menu Principal
			}
		}
	}
								
	public void draw(Graphics g , JPanel p){
		g.setColor(new Color(188, 209, 255));
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		Menu.fond.draw(g, p);
		m_titreMenu.draw(g, p);
		m_bouttonModeClassique.draw(g, p);
		m_bouttonModeIle.draw(g, p);
		m_bouttonRetour.draw(g, p);
	}
	
	public void startEvent(){
		this.selectionMode = 0;
		m_bouttonRetour.setEnabled(true);
		m_bouttonModeIle.setEnabled(true);
		m_bouttonModeClassique.setEnabled(true);
		//[AFFICHER MENU JOUEUR VS ORDI (Choix du Mode)]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){
		m_bouttonModeClassique.checkMouse(e, typeOfInput);
		m_bouttonModeIle.checkMouse(e, typeOfInput);
		m_bouttonRetour.checkMouse(e, typeOfInput);
	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU JOUEUR VS ORDI (Choix du Mode)]
	}

	public static int getSelectionMode(){ //Utilisée dans la scène Jouer et WinLoseScore
		return JoueurVsOrdi.selectionMode;
	}
}