package game.Objects;

import game.engine.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bateau{

	public int orientation;
	private Coordonnees[] cases;
	public int x;
	public int y;
	public BufferedImage img;
	public int type;
	public int taille;
	private Grid m_parent;

	public Bateau(int x, int y, int o, int type, Grid parent){
		this.x = x;
		this.y = y;
		this.orientation = o;
		this.type = type;
		this.m_parent = parent;

		switch (type){
			case 0: this.taille = 2;break;
			case 1: this.taille = 3;break;
			case 2: this.taille = 3;break;
			case 3: this.taille = 4;break;
			case 4: this.taille = 5;break;
		}
		System.out.println("Taille: "+taille + "  Type: " + type);
		img = SpriteIndex.imagesBateaux[type];
		cases = new Coordonnees[taille];
		placerBateau();
	}

	public void placerBateau(){
		int taille = 0;
		//Le système place le bateau du Joueur ou de l'Ordi sur les grilles respectives
		switch(this.orientation){
			case 2: //Sens - Haut
				while(taille<this.taille){
					this.setCoordonnees(taille,x,y-taille);
					System.out.println("Cases X: " + x + " Y: " + (y-taille));
					taille++;
				}
				break;
			case 1: //Sens - Gauche
				while(taille<this.taille){
					this.setCoordonnees(taille,x-taille,y);
					System.out.println("Cases X: " + (x-taille) + " Y: " + (y));
					taille++;
				}
				break;
			case 0: //Sens - Bas
				while(taille<this.taille){
					this.setCoordonnees(taille,x,y+taille);
					System.out.println("Cases X: " + x + " Y: " + (y+taille));
					taille++;
				}
				break;
			case 3: //Sens - Droite
				while(taille<this.taille){
					this.setCoordonnees(taille,x+taille,y);
					System.out.println("Cases X: " + (x+taille) + " Y: " + (y));
					taille++;
				}
				break;
		}
	}

	public void setTaille(int taille){
		//On définit la taille du tableau de coordonnées dont on aura besoin
		cases = new Coordonnees[taille];
	}

	public boolean touche(int x, int y){
		//On vérifie si l'une des Coordonnée du Bateau est touchée 
		for(int i=0;i<this.cases.length;i++){
			if(this.cases[i].getX()==x && this.cases[i].getY()==y){
				this.cases[i].setTouche();
				
				return true;
			}
		}
		return false;
	}

	public void setCoordonnees(int c,int x, int y){
		//On définit les différentes coordonnés du bateau
		this.cases[c] = new Coordonnees();
		this.cases[c].setXY(x,y);
	}

	//Retourne l'état du bateau, true = "en vie" et false = coulé
	public boolean getEnVie(){
		int verif = 0;
		//On regarde toutes les cases du bateau
		for(int i=0;i<this.cases.length;i++){
			//Si une case du bateau est touchée
			if(this.cases[i].getTouche() == true){
				verif++;
			}
		}
		//Si toutes les cases sont touchées, le bateau est coulé
		if(verif == this.cases.length){
			return false;
		}
		return true; //Le bateau est encore "en vie"
	}

	public void draw(Graphics g, JPanel p){
		Graphics2D g2 = (Graphics2D)g;
		int rt = 0;
		switch(orientation){
			case 0 :
				rt = 0;
				break;
			case 1:
				rt = 90;
				break;
			case 2:
				rt = 180;
				break;
			case 3:
				rt = -90;
				break;
		}
		g2.rotate(Math.toRadians(rt),((x*m_parent.getCellSize())+m_parent.getXPos())+(m_parent.getCellSize()/2),((y*m_parent.getCellSize())+m_parent.getYPos())+(m_parent.getCellSize()/2));
		g.drawImage(SpriteIndex.imagesBateaux[type],m_parent.getXPos()+(x*m_parent.getCellSize()),m_parent.getYPos()+(y*m_parent.getCellSize()),m_parent.getCellSize(),m_parent.getCellSize()*taille,p);
		g2.rotate(Math.toRadians(-rt),((x*m_parent.getCellSize())+m_parent.getXPos())+(m_parent.getCellSize()/2),((y*m_parent.getCellSize())+m_parent.getYPos())+(m_parent.getCellSize()/2));

	}

	public Coordonnees getCases(int i) {
		return cases[i];
	}
}