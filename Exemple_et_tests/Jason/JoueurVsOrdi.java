import java.awt.*;
import java.awt.event.*;

//Scène 2 - JoueurVsOrdi

public class JoueurVsOrdi extends Scene{

	private int selectionMode;
	private static ??? clicSelectionMode;

	public void update(){
		if(clicSelectionMode == ???){ //Bouton Mode Classique
			selectionMode = 1;
		}
		else if(clicSelectionMode == ???){ //Bouton Mode Île
			selectionMode = 2;
		}
		else if(clicSelectionMode == ???){ //Bouton Mode Bateaux Tireurs
			selectionMode = 3;
		}
		else if(clicSelectionMode == ???){ //Bouton Retour
			selectionMode = -1;
		}

		if(selectionMode != 0){
			if(selectionMode != -1){
				Game.SceneSwitch(3); //Switch vers la scène DifficulteOrdi
			}
			else{
				Game.SceneSwitch(1); //Retour au Menu Principal
			}
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.selectionMode = 0;
		this.clicSelectionMode = ???; //Défaut

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

	public int getSelectionMode(){ //Utilisée dans la scène Jouer et WinLoseScore
		return this.selectionMode;
	}
}