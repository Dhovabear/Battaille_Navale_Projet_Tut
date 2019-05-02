import java.awt.*;
import java.awt.event.*;

//Scène 10 - Scores

public class Scores extends Scene{

	private static int gSC;
	private static ??? clicRetour;

	public abstract void update(){
		if(clicRetour == ???){ //Bouton Retour
			Game.switchScene(9); //Retour vers la scène ScoresDifficulte
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		gSC = getChoixScore();

		if(gSC == 1){
			//[AFFICHER SCORES FACILE]
		}
		else if(gSC == 2){
			//[AFFICHER SCORES NORMAL]
		}
		else if(gSC == 3){
			//[AFFICHER SCORES DIFFICILE]
		}
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		if(gSC == 1){
			//[ENLEVER SCORES FACILE]
		}
		else if(gSC == 2){
			//[ENLEVER SCORES NORMAL]
		}
		else if(gSC == 3){
			//[ENLEVER SCORES DIFFICILE]
		}
	}
}