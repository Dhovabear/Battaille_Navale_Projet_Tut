package game.scenes;

import game.engine.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

	private static int choixDrapeauJoueur;
	private static String choixNomJoueur;
	private static int choixDrapeauOrdi;
	private static String choixNomOrdi;

	private static boolean confirmer;
	private	static final int AMPLEUR = 20;

	public void update(){
		
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.confirmer = false;
		//[AFFICHER MENU NOM ET DRAPEAU]
	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

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
			choixDrapeauOrdi = 1;
			choixNomOrdi = "France"+getTexteDifficulte();
			break;

		case 2:
			choixDrapeauOrdi = 2;
			choixNomOrdi = "Allemagne"+getTexteDifficulte();
			break;

		case 3:
			choixDrapeauOrdi = 3;
			choixNomOrdi = "Espagne"+getTexteDifficulte();
			break;

		case 4:
			choixDrapeauOrdi = 4;
			choixNomOrdi = "Portugal"+getTexteDifficulte();
			break;

		case 5:
			choixDrapeauOrdi = 5;
			choixNomOrdi = "Italie"+getTexteDifficulte();
			break;

		case 6:
			choixDrapeauOrdi = 6;
			choixNomOrdi = "Royaume-Uni"+getTexteDifficulte();
			break;

		case 7:
			choixDrapeauOrdi = 7;
			choixNomOrdi = "Etats-Unis"+getTexteDifficulte();
			break;

		case 8:
			choixDrapeauOrdi = 8;
			choixNomOrdi = "Mexique"+getTexteDifficulte();
			break;

		case 9:
			choixDrapeauOrdi = 9;
			choixNomOrdi = "Canada"+getTexteDifficulte();
			break;

		case 10:
			choixDrapeauOrdi = 10;
			choixNomOrdi = "Brésil"+getTexteDifficulte();
			break;

		case 11:
			choixDrapeauOrdi = 11;
			choixNomOrdi = "Russie"+getTexteDifficulte();
			break;

		case 12:
			choixDrapeauOrdi = 12;
			choixNomOrdi = "Chine"+getTexteDifficulte();
			break;

		case 13:
			choixDrapeauOrdi = 13;
			choixNomOrdi = "Inde"+getTexteDifficulte();
			break;

		case 14:
			choixDrapeauOrdi = 14;
			choixNomOrdi = "Australie"+getTexteDifficulte();
			break;

		case 15:
			choixDrapeauOrdi = 15;
			choixNomOrdi = "IUT Info"+getTexteDifficulte();
			break;

		case 16:
			choixDrapeauOrdi = 16;
			choixNomOrdi = "RaptorRouge"+getTexteDifficulte();
			break;

		case 17:
			choixDrapeauOrdi = 17;
			choixNomOrdi = "Xanix"+getTexteDifficulte();
			break;

		case 18:
			choixDrapeauOrdi = 18;
			choixNomOrdi = "Dovabear"+getTexteDifficulte();
			break;

		case 19:
			choixDrapeauOrdi = 19;
			choixNomOrdi = "r2r0"+getTexteDifficulte();
			break;

		case 20:
			choixDrapeauOrdi = 20;
			choixNomOrdi = "Nijtus"+getTexteDifficulte();
			break;
		}
	}

	private String getTexteDifficulte() {
		return "boiiiiiii";
	}

	public static int getDrapeauJoueur(){ //Utilisée dans la classe Jouer
		return choixDrapeauJoueur;
	}

	public static int getDrapeauOrdi(){ //Utilisée dans la classe Jouer
		return choixDrapeauOrdi;
	}

	public static String getNomJoueur(){ //Utilisée dans la classe Jouer et WinLoseScore
		return choixNomJoueur;
	}

	public static String getNomOrdi(){ //Utilisée dans la classe Jouer
		return choixNomOrdi;
	}
}