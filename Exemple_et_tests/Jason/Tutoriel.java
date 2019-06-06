import java.awt.*;
import java.awt.event.*;

//Scène 11 - Tutoriel

public class Tutoriel extends Scene{

	private static ??? clicRetourTuto;

	public void update(){
		if(clicRetourTuto == ???){ //Bouton Retour
			Game.switchScene(1) //Retour au Menu Principal
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.clicRetourTuto = ???;

		//[AFFICHER TUTORIEL]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER TUTORIEL]
	}
}