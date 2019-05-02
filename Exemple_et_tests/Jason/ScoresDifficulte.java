import java.awt.*;
import java.awt.event.*;

//Scène 9 - ScoresDifficulte

public class ScoresDifficulte extends Scene{

	private int choixScore;
	private static ??? clicChoixScore;

	public void update(){
		if(clicChoixScore == ???){
			choixScore = 1;
		}
		else if(clicChoixScore == ???){
			choixScore = 2;
		}
		else if(clicChoixScore == ???){
			choixScore = 3;
		}
		else if(clicChoixScore == ???){
			choixScore = -1;
		}

		if(choixScore != 0){
			if(choixScore != -1){
				Game.switchScene(10); //Switch vers la scène Scores
			}
			else{
				Game.switchScene(1); //Retour au Menu Principal
			}
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.choixScore = 0;
		this.clicChoixScore = ???; //Défaut

		//[AFFICHER MENU SCORES DIFFICULTE]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU SCORES DIFFICULTE]
	}

	public int getChoixScore(){ //Utilisée dans la scène Scores
		return this.choixScore;
	}
}