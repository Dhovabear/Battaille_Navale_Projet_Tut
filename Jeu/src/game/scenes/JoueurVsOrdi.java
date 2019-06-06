package game.scenes;

import game.engine.Game;
import game.engine.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//Scène 2 - JoueurVsOrdi

public class JoueurVsOrdi extends Scene {

	private static int selectionMode;


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

	}
	
	public void startEvent(){
		this.selectionMode = 0;

		//[AFFICHER MENU JOUEUR VS ORDI (Choix du Mode)]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

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