package game.scenes;

import game.engine.Scene;
import game.engine.ui.FlagSelector;
import game.engine.ui.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math; //Note 2

//Scène 4 - DrapeauNom

/*
*Note : Il faudra faire en sorte que le bouton Confirmer soit «grisé» si 
*		 l’on a pas sélectionné le drapeau et le nom.
*/

/*
*Note 2 : La bibliothèque mathématique est nécessaire pour générer l’aléatoire
*		   ainsi que pour arrondir le résultat.
*/

public class DrapeauNom extends Scene {

	private static BufferedImage choixDrapeauJoueur;
	private static String choixNomJoueur;
	private static BufferedImage choixDrapeauOrdi;
	private static String choixNomOrdi;

	private static boolean confirmer;
	private	static final int AMPLEUR = 12;

	private FlagSelector m_flagSelector;
	private Label m_titreMenu;

	public DrapeauNom() throws IOException {
		m_flagSelector = new FlagSelector(30,100,100);
		m_titreMenu = new Label(300,50,50f,"bitcrusher.ttf","Choisissez votre nom et pays");
	}

	public void update(){
		
	}
								
	public void draw(Graphics g , JPanel p){
		g.setColor(new Color(188, 209, 255));
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		Menu.fond.draw(g,p);
		m_titreMenu.draw(g, p);
		m_flagSelector.draw(g, p);

		if(choixDrapeauJoueur != null){
			g.drawImage(choixDrapeauJoueur,300,350,200,100,p);
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(300,350,200,100);
		}
	}
	
	public void startEvent(){
		this.confirmer = false;
		//[AFFICHER MENU NOM ET DRAPEAU]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){
		m_flagSelector.checkMouse(e,typeOfInput);

		if(typeOfInput.equals("mP")){
			BufferedImage tmp = m_flagSelector.getSelectedFlag();
			if(tmp != null){
				choixDrapeauJoueur = tmp;
			}
		}
	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER MENU DIFFICULTE ORDI]

		//Choix aléatoire du drapeau et du nom pour l'ORDI
		int random = (int)(Math.round(Math.random()*AMPLEUR));
		if(random == 0){
			random++;
		}
		//getTexteDifficulte() ----> Scène 3 - DifficulteOrdi
		switch(random){
		case 1:
			choixNomOrdi = "France"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 2:
			choixNomOrdi = "Allemagne"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 3:
			choixNomOrdi = "Espagne"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 4:
			choixNomOrdi = "Portugal"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 5:
			choixNomOrdi = "Italie"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 6:
			choixNomOrdi = "Royaume-Uni"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 7:
			choixNomOrdi = "Etats-Unis"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 8:
			choixNomOrdi = "Mexique"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 9:
			choixNomOrdi = "Canada"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 10:
			choixNomOrdi = "Brésil"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 11:
			choixNomOrdi = "Russie"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 12:
			choixNomOrdi = "Chine"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 13:
			choixNomOrdi = "Inde"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 14:
			choixNomOrdi = "Australie"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 15:
			choixNomOrdi = "IUT Info"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 16:
			choixNomOrdi = "RaptorRouge"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 17:
			choixNomOrdi = "Xanix"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 18:
			choixNomOrdi = "Dovabear"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 19:
			choixNomOrdi = "r2r0"+DifficulteOrdi.getTexteDifficulte();
			break;

		case 20:
			choixNomOrdi = "Nijtus"+DifficulteOrdi.getTexteDifficulte();
			break;
		}

		choixDrapeauOrdi = m_flagSelector.getFlag(random);
	}


	public static BufferedImage getDrapeauJoueur(){ //Utilisée dans la classe Jouer
		return choixDrapeauJoueur;
	}

	public static BufferedImage getDrapeauOrdi(){ //Utilisée dans la classe Jouer
		return choixDrapeauOrdi;
	}

	public static String getNomJoueur(){ //Utilisée dans la classe Jouer et WinLoseScore
		return choixNomJoueur;
	}

	public static String getNomOrdi(){ //Utilisée dans la classe Jouer
		return choixNomOrdi;
	}
}