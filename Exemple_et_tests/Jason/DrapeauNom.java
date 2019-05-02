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

public class DrapeauNom extends Scene{

	private int choixDrapeauJoueur;
	private String choixNomJoueur;
	private int choixDrapeauOrdi;
	private String choixNomOrdi;
	private static ??? clicChoixDrapeau;
	private static ??? clicChoixNom;
	private static ??? clicConfirmer;
	private static boolean confirmer;
	private	static final int AMPLEUR = 20;

	public void update(){
		if(clicChoixDrapeau == ???){ //Bouton Drapeau France
			choixDrapeauJoueur = 1;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Allemagne
			choixDrapeauJoueur = 2;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Espagne
			choixDrapeauJoueur = 3;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Portugal
			choixDrapeauJoueur = 4;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Italie
			choixDrapeauJoueur = 5;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Royaume-Uni
			choixDrapeauJoueur = 6;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Etats-Unis
			choixDrapeauJoueur = 7;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Mexique
			choixDrapeauJoueur = 8;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Canada
			choixDrapeauJoueur = 9;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Brésil
			choixDrapeauJoueur = 10;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Russie
			choixDrapeauJoueur = 11;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Chine
			choixDrapeauJoueur = 12;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Inde
			choixDrapeauJoueur = 13;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Australie
			choixDrapeauJoueur = 14;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau IUT Info
			choixDrapeauJoueur = 15;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau RaptorRouge
			choixDrapeauJoueur = 16;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Xanix
			choixDrapeauJoueur = 17;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Dovabear
			choixDrapeauJoueur = 18;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau r2r0
			choixDrapeauJoueur = 19;
		}
		else if(clicChoixDrapeau == ???){ //Bouton Drapeau Nijtus
			choixDrapeauJoueur = 20;
		}
		else if(clicChoixNom == ???){ //Bouton SaisieNom
			//choixNom = [LIRE ENTREE DE CLAVIER];
		}
		else if(clicChoixDrapeau == ???){ //Bouton Retour
			Game.switchScene(3) //Retour vers la scène DifficulteOrdi
		}

		if((choixDrapeau != 0)&&(choixNom != null)){
			if(clicConfirmer == ???){ //Bouton Confirmer
				confirmer = true;
			}
		}

		if((choixDrapeau != 0)&&(choixNom != null)&&(confirmer == true){
			Game.switchScene(5); //Switch vers la scene Jouer
		}
	}
								
	public void draw(Graphics g , JPanel p){

	}
	
	public void startEvent(){
		this.confirmer = false;
		this.clicChoixDrapeau = ???; //Défaut
		this.clicChoixNom = ???; //Défaut
		this.clicConfirmer = ???; //Défaut

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

	public int getDrapeauJoueur(){ //Utilisée dans la classe Jouer
		return this.choixDrapeauJoueur;
	}

	public int getDrapeauOrdi(){ //Utilisée dans la classe Jouer
		return this.choixDrapeauOrdi;
	}

	public String getNomJoueur(){ //Utilisée dans la classe Jouer et WinLoseScore
		return this.choixNomJoueur;
	}

	public String getNomOrdi(){ //Utilisée dans la classe Jouer
		return this.choixNomOrdi;
	}
}