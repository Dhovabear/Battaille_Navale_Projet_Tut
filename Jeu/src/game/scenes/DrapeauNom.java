package game.scenes;

import game.engine.Game;
import game.engine.Scene;
import game.engine.ui.BouttonSansFond;
import game.engine.ui.FlagSelector;
import game.engine.ui.Label;
import game.engine.ui.TextField;

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
	private TextField m_champNom;

	private BouttonSansFond m_bouttonRetour;
	private BouttonSansFond m_bouttonSuivant;

	public DrapeauNom() throws IOException, FontFormatException {
		m_flagSelector = new FlagSelector(30,100,100);
		m_titreMenu = new Label(300,50,50f,"bitcrusher.ttf","Choisissez votre nom et pays");
		m_champNom = new TextField(520,400,20,"Enter name here",20);
		m_bouttonRetour = new BouttonSansFond(5,510,50,"Retour"){
			@Override
			public void action() throws IOException, FontFormatException {
				Game.switchScene(3);
			}
		};
		m_bouttonSuivant = new BouttonSansFond(800,510,50,"Suivant"){
			@Override
			public void action() throws IOException, FontFormatException {
				int random = (int)(Math.round(Math.random()*AMPLEUR));
				choixDrapeauOrdi = m_flagSelector.getFlag(random);
				choixNomOrdi = m_flagSelector.getNameOfSelectedFlag();
				Game.switchScene(5);
			}
		};
	}

	public void update(){
		if(!m_champNom.getText().equals("") && choixDrapeauJoueur != null){
			if(!m_bouttonSuivant.isEnabled()){
				m_bouttonSuivant.setEnabled(true);
			}
		}else{
			m_bouttonSuivant.setEnabled(false);
		}
	}
								
	public void draw(Graphics g , JPanel p){
		g.setColor(new Color(188, 209, 255));
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		Menu.fond.draw(g,p);
		m_titreMenu.draw(g, p);
		m_flagSelector.draw(g, p);
		m_bouttonRetour.draw(g, p);
		m_bouttonSuivant.draw(g, p);
		if(choixDrapeauJoueur != null){
			g.drawImage(choixDrapeauJoueur,300,350,200,100,p);
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(300,350,200,100);
		}

		m_champNom.draw(g, p);
	}
	
	public void startEvent(){
		this.confirmer = false;
		m_bouttonSuivant.setEnabled(false);
		m_champNom.clearText();
		m_flagSelector.deselect();
		choixDrapeauJoueur = null;
		//[AFFICHER MENU NOM ET DRAPEAU]
	}

	public void input(KeyEvent e,String typeOfInput){
		m_champNom.checkKeys(e,typeOfInput);
	}

	public void mouseInput(MouseEvent e,String typeOfInput){
		m_flagSelector.checkMouse(e,typeOfInput);
		m_champNom.checkMouse(e, typeOfInput);
		m_bouttonRetour.checkMouse(e, typeOfInput);
		m_bouttonSuivant.checkMouse(e, typeOfInput);

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

		m_bouttonRetour.setEnabled(true);
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