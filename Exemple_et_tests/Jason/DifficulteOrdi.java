import java.awt.*;
import java.awt.event.*;

//Scène 3 - DifficulteOrdi

public class DifficulteOrdi extends Scene{

	private int choixDifficulte;
	private String texteDifficulte;
	private static ??? clicChoixDifficulte;

	public void update(){
		if(clicChoixDifficulte == ???){ //Bouton Facile
			choixDifficulte = 1;
			texteDifficulte = " (Facile)";
		}
		else if(clicChoixDifficulte == ???){ //Bouton Normal
			choixDifficulte = 2;
			texteDifficulte = " (Normal)";
		}
		else if(clicChoixDifficulte == ???){ //Bouton Difficulte
			choixDifficulte = 3;
			texteDifficulte = " (Difficile)";
		}
		else if(clicChoixDifficulte == ???){ //Bouton Retour
			choixDifficulte = -1;
		}

		if(choixDifficulte != 0){
			if(choixDifficulte != -1){
				Game.switchScene(4); //Switch vers la scène DrapeauNom
			}
			else {
				Game.switchScene(2); //Retour vers la scène JoueurVsOrdi
			}
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.clicChoixDifficulte = ???; //Défaut
		this.choixDifficulte = 0;
		this.texteDifficulte = null;

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

	public int getDifficulteOrdi(){ //Utilisée dans la classe Jouer et WinLoseScore
		return this.choixDifficulte;
	}

	public String getTexteDifficulte(){ //Utilisée dans la classe DrapeauNom
		return this.texteDifficulte;
	}
}