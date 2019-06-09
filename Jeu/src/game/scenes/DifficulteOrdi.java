package game.scenes;

import game.engine.Game;
import game.engine.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Scène 3 - DifficulteOrdi

public class DifficulteOrdi extends Scene {

	private static int choixDifficulte;
	private String texteDifficulte;


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
	}
	
	public void startEvent(){
		this.choixDifficulte = 0;
		this.texteDifficulte = null;
		System.out.println("plop");
		//[AFFICHER MENU DIFFICULTE ORDI]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU DIFFICULTE ORDI]
	}

	public static int getDifficulteOrdi(){ //Utilisée dans la classe Jouer et WinLoseScore
		return choixDifficulte;
	}

	public String getTexteDifficulte(){ //Utilisée dans la classe DrapeauNom
		return this.texteDifficulte;
	}
}