package game.scenes;

import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Scène 3 - DifficulteOrdi

public class DifficulteOrdi extends Scene {

	private static int choixDifficulte;
	private static String texteDifficulte;

	private Label m_label;

	private BouttonSansFond m_boutFacile;
	private BouttonSansFond m_boutMoyen;
	private BouttonSansFond m_boutDur;

	private BouttonSansFond m_boutRetour;

	public DifficulteOrdi() throws IOException, FontFormatException {
		this.m_label = new Label(450,65,50f,"bitcrusher.ttf","Choisir une difficultée");
		m_boutFacile = new BouttonSansFond(300,250,50,"Facile"){
			@Override
			public void action() throws IOException, FontFormatException {
				choixDifficulte = 1;
				texteDifficulte = "Facile";
				Game.switchScene(4);
			}
		};
		m_boutMoyen = new BouttonSansFond(500,250,50,"Moyen"){
			@Override
			public void action() throws IOException, FontFormatException {
				choixDifficulte = 2;
				texteDifficulte = "Normal";
				Game.switchScene(4);
			}
		};
		m_boutDur = new BouttonSansFond(700,250,50,"Difficile"){
			@Override
			public void action() throws IOException, FontFormatException {
				choixDifficulte = 3;
				texteDifficulte = "Difficille";
				Game.switchScene(4);
			}
		};
		m_boutRetour = new BouttonSansFond(5,510,50,"Retour"){
			@Override
			public void action() throws IOException, FontFormatException {
				Game.switchScene(2);
			}
		};
	}

	public void update() throws IOException, FontFormatException {



		/*if(choixDifficulte != 0){
			if(choixDifficulte != -1){
				Game.switchScene(4); //Switch vers la scène DrapeauNom
			}
			else {
				Game.switchScene(2); //Retour vers la scène JoueurVsOrdi
			}
		}*/
	}
								
	public void draw(Graphics g , JPanel p){
		g.setColor(new Color(188, 209, 255));
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		Menu.fond.draw(g,p);
		m_label.draw(g,p);

		m_boutFacile.draw(g,p);
		m_boutMoyen.draw(g,p);
		m_boutDur.draw(g,p);
		m_boutRetour.draw(g, p);
	}
	
	public void startEvent(){
		this.choixDifficulte = 0;
		this.texteDifficulte = null;



		//[AFFICHER MENU DIFFICULTE ORDI]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){
		m_boutFacile.checkMouse(e, typeOfInput);
		m_boutMoyen.checkMouse(e, typeOfInput);
		m_boutDur.checkMouse(e, typeOfInput);
		m_boutRetour.checkMouse(e, typeOfInput);
	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU DIFFICULTE ORDI]
		m_boutRetour.setEnabled(true);
	}

	public static int getDifficulteOrdi(){ //Utilisée dans la classe Jouer et WinLoseScore
		return choixDifficulte;
	}

	public static String getTexteDifficulte(){ //Utilisée dans la classe DrapeauNom
		return texteDifficulte;
	}

}