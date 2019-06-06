import java.awt.*;
import java.awt.event.*;

//Scène 1 - MenuPrincipal

public class MenuPrincipal extends Scene{

	private static int selectionMenu;
	private static ??? clicSelectionMenu;

	public void update(){
		if(clicSelectionMenu == ???){ //Bouton Joueur Vs Ordi
			selectionMenu = 1;
		}
		else if(clicSelectionMenu == ???){ //Bouton Joueur Vs Joueur
			selectionMenu = 2;
		}
		else if(clicSelectionMenu == ???){ //Bouton Meilleurs Scores
			selectionMenu = 3;
		}
		else if(clicSelectionMenu == ???){ //Bouton Tutoriels
			selectionMenu = 4;
		}
		else if(clicSelectionMenu == ???){ //Bouton Crédits
			selectionMenu = 5;
		}
		else if(clicSelectionMenu == ???){ //Bouton Quitter
			selectionMenu = -1;
		}

		switch(selectionMenu){
			case 1:
				Game.switchScene(2); //Switch vers la scène JoueurVsOrdi
				break;

			case 2:
				Game.switchScene(7); //Switch vers la scène JoueurVsJoueur
				break;

			case 3:
				Game.switchScene(9); //Switch vers la scène ScoresDifficulte
				break;

			case 4:
				Game.switchScene(13); //Switch vers la scène Tutoriel
				break;

			case 5:
				Game.switchScene(14); //Switch vers la scène Credits
				break;

			case -1:
				Game.quit();
				break;
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.selectionMenu = 0;
		this.clicSelectionMenu = ???; //Défaut

		//[AFFICHER ARRIERE PLAN]		

		//{Jouer Musique Menu}

		//[AFFICHER MENU PRINCIPAL]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU PRINCIPAL]
	}
}