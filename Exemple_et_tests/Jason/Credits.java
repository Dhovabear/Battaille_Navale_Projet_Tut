import java.awt.*;
import java.awt.event.*;

//Scène 12 - Credits

public class Credits extends Scene{

	private static ??? clicRetourCredits;

	public void update(){
		if(clicRetourCredits == ???){ //Bouton retour
			Game.swicthScene(1); //Retour au menu principal
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		clicRetourCredits = ???; //Défaut

		//[AFFICHER CREDITS]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){

	}
}