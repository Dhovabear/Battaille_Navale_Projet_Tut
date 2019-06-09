package game.scenes;

import game.Objects.Coordonnees;
import game.Objects.Grid;
import game.engine.Game;
import game.engine.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*; //Note 1
import java.io.*; //Note 2

//Scène 5 - Jouer

/*
*Note 1 : La bibliothèque java.util.* est nécessaire pour Scanner et Math
*		  Scanner : lecture de fichier
*		  Math : générer l’aléatoire et arrondir le résultat
*/

/*
*Note 2 : La bibliothèque java.io.* est nécessaire pour File, utilisée dans cette scène pour la lecture de fichier
*/

/*
*	Légende pour les grilles :
*
*	0 = Eau
*	1 = Bateau (Grille personnelle (Grille de visu - radar : Mode bateaux tireurs uniquement))
*	2 = Bateau touché (Grille personnelle)
*	3 = Terre (Mode île uniquement)
*	4 = Torpille - touché (Grille de visu)
*	5 = Torpille - manqué (Grille de visu)
*/

public class Jouer extends Scene {

	//On recueille toutes les variables dont on a besoin
	private int mode = JoueurVsOrdi.getSelectionMode(); //Scène 2 - JoueurVsOrdi
	private int difficulte = DifficulteOrdi.getDifficulteOrdi(); //Scène 3 - DifficulteOrdi
	private BufferedImage drapeauJ = DrapeauNom.getDrapeauJoueur(); //Scène 4 - DrapeauNom
	private BufferedImage drapeauO = DrapeauNom.getDrapeauOrdi(); //Scène 4 - DrapeauNom
	private String nomJ = DrapeauNom.getNomJoueur(); //Scène 4 - DrapeauNom
	private String nomO = DrapeauNom.getNomOrdi(); //Scène 4 - DrapeauNom
	
	//Variables servant pour le système de base de la bataille navale
	private int x; //Axe des abscisses
	private int y; //Axe des ordonnées
	private Grid grilleJoueur = new Grid(0,0,30,10); //Grille personnelle du joueur
	private Grid grilleVisuJoueur = new Grid(0,0,30,10); //Servira au joueur pour voir la grille initiale de l'ordi
	private Grid grilleOrdi = new Grid(0,0,30,10); //Grille personnelle de l'ordi
	private Grid grilleVisuOrdi = new Grid(0,0,30,10); //Servira à l'ordi pour voir la grille initiale du joueur
	private boolean isJoueur; //Servira dans plusieurs fonctions pour savoir s'il s'agit du joueur ou de l'ordi
	private int vieJoueur; //Baissera au fur et à mesure que l'ordi touche les bateaux du joueur
	private int vieOrdi; //Baissera au fur et à mesure que le joueur touche les bateaux ordi

	//Variables servant pour le placement des bateaux, le touché-coulé et le Mode bateaux tireurs
	private int tailleBateau;
	private int sensBateau;
	/*
	*1 = Haut
	*2 = Gauche
	*3 = Bas
	*4 = Droite
	*/
	private int typeBateau; //Utilisée pour la classe Bateau (mais également pour tirer (Mode bateaux tireurs uniquement))
	/*
	*0 = Porte-avions
	*1 = Croiseur
	*2 = Contre-torpilleur
	*3 = Sous-marin
	*4 = Torpilleur
	*/

	//Variables servant au score
	private int precision; //Baissera au fur et à mesure si les tirs du joueur ne touchent pas de cible, utilisée pour établir le score
	private int degatsOrdi; //Augmentera au fur et à mesure que le joueur touche les bateaux ordi, utilisée pour établir le score
	private final int BONUSVICTOIRE = 178; //Utilisé pour atteindre un score maximal de 3000 points (((17+17)*83)+178) = 3000
	private int score; //Sera transmis à la Scène 6 - WinLoseScore

	//Autres variables
	private boolean abandon; //Si le joueur abandonne, on retourne au menu principal directement
	private boolean victoire; //Sera transmis à la Scène 6 - WinLoseScore. Octroie un bonus de points si la valeur est à true
	private final int AMPLEUR_ILE = 5; //Utilisée pour le Mode île uniquement
	private final int AMPLEUR_TIR = 3; //Utilisée pour le Mode bateaux tireurs uniquement
	private boolean sonar; //Utilisé par le sous-marin dans le Mode bateaux tireurs uniquement
	private int utilisationTirJoueur; //Utilisée pour le Mode bateaux tireurs uniquement (Un même bateau ne peux pas tirer 2 tours de suite)
	private int utilisationTirOrdi; //Utilisée pour le Mode bateaux tireurs uniquement (Un même bateau ne peux pas tirer 2 tours de suite)
	private int randomTirTorpilleur; //Utilisé pour le tir aléatoire du torpilleur (Mode bateaux tireurs uniquement)
	private boolean dragAndDropEnded = false;

	public void update() throws IOException, FontFormatException {
		//Si le joueur abandonne, il retourne au menu principal
		if(this.abandon==true){
			Game.switchScene(1);
		}

		if(!dragAndDropEnded){
			return;
		}

		//Si le joueur ou l'Ordi a tous ses bateaux coulés, la partie se termine
		if(this.vieOrdi==0 || this.vieJoueur==0){ 
			if(this.vieOrdi==0){
				this.victoire = true;
			}
			else{
				this.victoire = false;
			}
			Game.switchScene(6);
		}
		//On passe du tour du Joueur à celui de l'Ordi et vice versa
		if(this.isJoueur==true){
			this.isJoueur = false;
		}
		else{
			this.isJoueur = true;
		}
		//Le joueur ou l'ordi selectionne l'endroit où il veut tirer (ainsi que le bateau qui tire (Mode bateaux tireurs uniquement))
		//selectionTir();
	}
								
	public void draw(Graphics g , JPanel p){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,p.getWidth(),p.getHeight());

		if(!dragAndDropEnded){

			return;
		}

	}
	
	public void startEvent(){
		this.vieJoueur = 17; //Nombre de "cases bateaux"
		this.vieOrdi = 17; //Nombre de "cases bateaux"
		this.degatsOrdi = 0;
		this.precision = 83; //Nombre de cases (100) - nombre de "cases bateaux" (17)
		this.sonar = false;
		this.utilisationTirJoueur = -1; //Chaque tir est disponible au 1er tour
		this.utilisationTirOrdi = -1; //Chaque tir est disponible au 1er tour
		this.abandon = false;

		this.isJoueur = true;
		remplissage();
		//Dans le Mode île, les grilles du Joueur et de l'ordi peuvent être différentes 
		if(this.mode == 2){ 
			this.isJoueur = false;
			remplissage();
		}

		//On définit les différentes tailles de chaque bateau

		//[AFFICHER JOUER]
		//placerBateau();

	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		//[ENLEVER JOUER]

		//Le score n'est sauvegardé que pour le mode classique
		if(this.mode == 0 && this.abandon == false){
			//Si le joueur a gagné
			if(this.victoire == true){
				this.score = ((this.vieJoueur+this.degatsOrdi)*this.precision)+BONUSVICTOIRE;
			}
			else{
				this.score = (this.vieJoueur+this.degatsOrdi)*this.precision;
			}
		}
	}

	public void remplissage(){
		String fichier = new String();
		int random = (int)(Math.round(Math.random()*AMPLEUR_ILE));
		//L'emplacement de fichiers sera sûrement changé au fil du temps, il faudra donc modifier les chemins (commentaire à effacer par la suite)
		//On choisit une carte dans un fichier aléatoire (Utile pour le mode île)
		switch(random){
			case 0:
				fichier = "iles/ile0";
				break;
			case 1:
				fichier = "iles/ile1";
				break;
			case 2:
				fichier = "iles/ile2";
				break;
			case 3:
				fichier = "iles/ile3";
				break;
			case 4:
				fichier = "iles/ile4";
				break;
			case 5:
				fichier = "iles/ile5";
				break;
		}
		try //On ouvre le fichier
		{
			File file = new File(fichier);
			Scanner scan = new Scanner(file);
			//On remplit les grilles avec le contenu du fichier selectionné au hasard (Mode île uniquement)
			if(this.mode == 2){ 
				while(scan.hasNextInt()){
					for(int i=0;i<10;i++){
						for(int j=0;j<10;j++){
							if(this.isJoueur == true){
								this.grilleJoueur.setCellInfo(i,j,scan.nextInt());
								this.grilleVisuOrdi.setCellInfo(i,j,grilleJoueur.getCellInfo(i,j));
							}
							else{
								this.grilleOrdi.setCellInfo(i,j,scan.nextInt());
								this.grilleVisuJoueur.setCellInfo(i,j,this.grilleOrdi.getCellInfo(i,j));
							}
						}
					}
				}
			}
			//On remplit entièrement toutes les grilles avec de l'eau (valeur 0)
			else{ 
				for(int i=0;i<10;i++){
					for(int j=0;j<10;j++){
						this.grilleJoueur.setCellInfo(i,j,0);
						this.grilleVisuJoueur.setCellInfo(i,j,0);
						this.grilleOrdi.setCellInfo(i,j,0);
						this.grilleVisuOrdi.setCellInfo(i,j,0);
					}
				}
			}
		}
		catch(Exception e) //Implémentée dû au refus de la compilation quand cette condition n'est pas présente, mais inutile dans les faits (ce qui est faux)
		{
			System.out.println("Une erreur s'est produite.");
		}
	}

	/*public void selectionPlacerBateau(){
		int compteur = 0;
		//Le joueur place ses bateaux
		if(this.isJoueur == true){
			while(compteur<5){ //5 car il y a 5 bateaux à placer
				//On lit dans cette boucle , les variables x, y, tailleBateau, sensBateau et typeBateau (se référer aux légendes de sensBateau et typeBateau)
				do{ 
					//[LIRE X,Y,TAILLEBATEAU,SENSBATEAU ET TYPEBATEAU [GRILLEJOUEUR/GRILLEORDI]]
					//Si le joueur place son bateau à un endroit interdit, on lui affiche un message d'erreur
				//On place le bateau selectionné
				placerBateau(); 
				compteur++;
			}
		}
		//L'ordi place ses bateaux
		else{
			while(compteur<5){
				
				//[Appel d'IA à implémenter içi]

				placerBateau();
				compteur++;
			}
		}
	}*/

	/*public boolean autorisationPlacementJoueur(){
		int taille = 1;
		//Si l'endroit selectionné est invalide, on revoit false, il est inutile de vérifier le reste
		if(this.grilleJoueur.getCellInfo(this.x,this.y) == 0){
			switch(this.sensBateau){
				case 1: //Sens - Haut
					//On vérifie si le bateau ne dépasse pas de la grille
					if((this.y-this.tailleBateau)>-1){
						//On vérifie que toutes les cases du bateau sont dans de l'eau
						while(taille<this.tailleBateau){
							if((this.grilleJoueur[this.x][this.y-taille])!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 2: //Sens - Gauche
					//On vérifie si le bateau ne dépasse pas de la grille
					if((this.x-this.tailleBateau)>-1){
						//On vérifie que toutes les cases du bateau sont dans de l'eau
						while(taille<this.tailleBateau){
							if((this.grilleJoueur[this.x-taille][this.y])!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 3: //Sens - Bas
					//On vérifie si le bateau ne dépasse pas de la grille
					if((this.y+this.tailleBateau)>9){
						//On vérifie que toutes les cases du bateau sont dans de l'eau
						while(taille<this.tailleBateau){
							if((this.grilleJoueur[this.x][this.y+taille])!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 4: //Sens - Droite
					//On vérifie si le bateau ne dépasse pas de la grille
					if((this.x+this.tailleBateau)>9){
						//On vérifie que toutes les cases du bateau sont dans de l'eau
						while(taille<this.tailleBateau){
							if((this.grilleJoueur[this.x+taille][this.y])!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				}
			}
		else{
			return false;
		}
		return true; //Si toutes les vérifications ont été validées, le placement est accepté
	}*/

	public boolean autorisationPlacementOrdi(Coordonnees c, int t, int s){
		int taille = 1;
		//Les variables passées par l'IA sont définies içi
		this.x = c.getX();
		this.y = c.getY();
		this.typeBateau = t;
		this.sensBateau = s;
		//En fonction du type du bateau, on definit sa taille
		switch(this.typeBateau){
			case 0:
				this.tailleBateau = 5;
				break;
			case 1:
				this.tailleBateau = 4;
				break;
			case 4:
				this.tailleBateau = 2;
				break;
			default:
				this.tailleBateau = 3;
				break;
		}
		//Ces vérifications sont identiques à celles du joueur, les grilles ont juste été inversées pour l'Ordi
		if(this.grilleOrdi.getCellInfo(this.x , this.y) == 0){
			switch(this.sensBateau){
				case 1: //Sens - Haut
					if((this.y-this.tailleBateau)>-1){
						while(taille<this.tailleBateau){
							if(this.grilleOrdi.getCellInfo(this.x , this.y)!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 2: //Sens - Gauche
					if((this.x-this.tailleBateau)>-1){
						while(taille<this.tailleBateau){
							if(this.grilleOrdi.getCellInfo(this.x,this.y)!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 3: //Sens - Bas
					if((this.y+this.tailleBateau)>9){
						while(taille<this.tailleBateau){
							if(this.grilleOrdi.getCellInfo(this.x,this.y+taille)!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
				case 4: //Sens - Droite
					if((this.x+this.tailleBateau)>9){
						while(taille<this.tailleBateau){
							if(this.grilleOrdi.getCellInfo(this.x+taille,this.y)!=0){
								return false;
							}
							taille++;
						}
					}
					else{
						return false;
					}
					break;
			}
		}
		else{
			return false;
		}
		return true; //Utilisé par l'IA
	}

	//L'objet Bateau est utilisé içi
	/*public void placerBateau(){
		int taille = 0;
		//Le système place le bateau du Joueur ou de l'Ordi sur les grilles respectives
		if(this.isJoueur == true)
		{
			switch(this.sensBateau){
				case 1: //Sens - Haut
					while(taille<this.tailleBateau){
						this.grilleJoueur.setCellInfo(this.x , this.y+taille,1);
						this.bateauxJoueur[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 2: //Sens - Gauche
					while(taille<this.tailleBateau){
						this.grilleJoueur.setCellInfo(this.x-taille,this.y,1);
						this.bateauxJoueur[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 3: //Sens - Bas
					while(taille<this.tailleBateau){
						this.grilleJoueur.setCellInfo(this.x,this.y+taille,1);
						this.bateauxJoueur[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 4: //Sens - Droite
					while(taille<this.tailleBateau){
						this.grilleJoueur.setCellInfo(this.x+taille,this.y,1);
						this.bateauxJoueur[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
			}
		}
		else
		{
			switch(this.sensBateau){
				case 1: //Sens - Haut
					while(taille<this.tailleBateau){
						this.grilleOrdi.setCellInfo(this.x,this.y-taille,1);
						this.bateauxOrdi[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 2: //Sens - Gauche
					while(taille<this.tailleBateau){
						this.grilleOrdi.setCellInfo(this.x-taille,this.y,1);
						this.bateauxOrdi[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 3: //Sens - Bas
					while(taille<this.tailleBateau){
						this.grilleOrdi.setCellInfo(this.x,this.y+taille,1);
						this.bateauxOrdi[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
				case 4: //Sens - Droite
					while(taille<this.tailleBateau){
						this.grilleOrdi.setCellInfo(this.x+taille,this.y,1);
						this.bateauxOrdi[typeBateau].setCoordonnees(taille,x,y);
						taille++;
					}
					break;
			}
		}
	}*/

	/*public void selectionTir(){
		//Le joueur selectionne la case de sa grille de visu 
		if(this.isJoueur==true){
			//Le joueur lit les variable x et y pour la case selectionnée, on lit aussi typeBateau pour le tir spécial voulu (Mode bateaux tireurs uniquement)
			do{ 
				//[LIRE X,Y (ET TYPEBATEAU(Mode bateau tireurs uniquement)) GRILLEVISUORDI/GRILLEVISUJOUEUR]
				//Si le joueur veut tirer à un endroit interdi
				if(autorisationTirJoueur()==false){
					//[AFFICHER ERREUR TIR]
				}
			}while(autorisationTirJoueur()==false);
			if(this.mode==3){
				typeTir();
				if(this.typeBateau==2){
					if(this.sonar==true){
						//[AFFICHER "Il y a un ou plusieurs bateaux dans cette zone"]
					}
					else{
						//[AFFICHER "Le radar n'a rien trouvé dans cette zone"]
					}
				}
			}
			else{
				tir();
			}
		}
		else{
			
			//[Appel d'IA à implémenter içi]
			
			if(this.mode==3){
				typeTir();
			}
			else{
				tir();
			}
		}
	}*/

	

	public boolean autorisationTirOrdi(Coordonnees c){
		//Les variables passées par l'IA sont définies içi
		this.x = c.getX();
		this.y = c.getY();
		//Ces vérifications sont identiques à celles du joueur, les grilles ont juste été inversées pour l'Ordi
		if(this.grilleVisuOrdi.getCellInfo(this.x,this.y)>2){
			return false;
		}
		if(this.mode == 3 && (this.grilleOrdi.getBateauWithType(typeBateau).getEnVie()==false || this.utilisationTirOrdi==this.typeBateau)){
			return false;
		}
		return true;
	}

	//Cette fonction est utilisée dans le Mode bateaux tireurs uniquement
	public void typeTir(){
		this.sonar = false;
		switch(this.typeBateau){
			case 0:  //Tir du porte-avions, un bloc de 3x3 cases avec la case selectionnée en son centre
				tir();
				this.x--;
				tir();
				this.x=this.x+2;
				tir();
				this.y--;
				tir();
				this.x--;
				tir();
				this.x--;
				tir();
				this.y=this.y+2;
				tir();
				this.x++;
				tir();
				this.x++;
				tir();
				break;

			case 1: //Tir du croiseur, un + de 3cases en longueur et largeur avec la case selectionnée en son centre
				tir();
				this.x--;
				tir();
				this.x=this.x+2;
				tir();
				this.x--;
				this.y--;
				tir();
				this.y=this.y+2;
				tir();
				break;

			case 2: //Tir du contre-torpilleur, une largeur de 3 cases avec la case selectionnée en son centre
				tir();
				this.x--;
				tir();
				this.x=this.x+2;
				tir();
				break;

			case 3: //Tir du sous-marin, un sonar de 3x3 cases avec la case selectionnée en son centre
				sonar();
				this.x--;
				sonar();
				this.x=this.x+2;
				sonar();
				this.y--;
				sonar();
				this.x--;
				sonar();
				this.x--;
				sonar();
				this.y=this.y+2;
				sonar();
				this.x++;
				sonar();
				this.x++;
				sonar();
				break;

			case 4: //Tir du torpilleur, tir sur la case selectionnée plus 1 tir aléatoire en/à haut/bas/gauche/droite de celle-ci
				tir();
				this.randomTirTorpilleur = (int)(Math.round(Math.random()*AMPLEUR_TIR));
				switch(randomTirTorpilleur){
					case 0: //Haut
						this.y--;
						tir();
						break;
					case 1: //Droite
						this.x++;
						tir();
						break;
					case 2: //Bas
						this.y++;
						tir();
						break;
					case 3: //Gauche
						this.x--;
						tir();
						break;
				}
				break;

			default: //Tir de base
				tir();
				break;
		}
	}

	//L'objet Bateau est utilisé içi
	public void tir(){
		//Si le joueur tire
		if(this.isJoueur==true){
			//Si le tir ne sort pas de la grille (Vérification pour le Mode bateaux tireurs)
			if((this.x>-1 && this.x<10) && (this.y>-1 && this.y<10)){
				//Si le tir touche une case bateau adverse
				if(this.grilleOrdi.getCellInfo(this.x,this.y)==1){
					//On indique sur la grille de l'ordi qu'une de ses cases bateau à été touchée
					this.grilleOrdi.setCellInfo(this.x,this.y,2);
					//On indique sur la grille de visu du joueur que le tir à touché
					this.grilleVisuJoueur.setCellInfo(this.x,this.y,4);
					//La vie de l'Ordi baisse
					this.vieOrdi--;
					this.degatsOrdi++;
					//On vérifie quelle coordonnée de quel bateau à été touchée, on applique le processus en résultant (voir l'objet Bateau et l'objet Coordonnée)
					for(int i=0;i<this.grilleOrdi.getNbrOfBateau();i++){
						if(this.grilleOrdi.getBateau(i).touche(this.x,this.y)==true){
							break;
						}
					}
				}
				else if(this.grilleOrdi.getCellInfo(this.x,this.y) != 0){
					this.grilleVisuJoueur.setCellInfo(this.x,this.y,5);
					this.precision--;
				}
			}
		}
		//Identique au tir du joueur, mais içi les grilles sont inversées pour l'Ordi
		else{
			if((this.x>-1 && this.x<10) && (this.y>-1 && this.y<10)){
				if(this.grilleJoueur.getCellInfo(this.x,this.y)==1){
					this.grilleJoueur.setCellInfo(this.x,this.y,2);
					this.grilleVisuOrdi.setCellInfo(this.x,this.y,4);
					this.vieJoueur--;
					for(int i=0;i<this.grilleJoueur.getNbrOfBateau();i++){
						if(this.grilleJoueur.getBateau(i).touche(this.x,this.y)==true){
							break;
						}
					}
				}
				else if(this.grilleJoueur.getCellInfo(this.x,this.y) != 0){
					this.grilleVisuOrdi.setCellInfo(this.x,this.y,5);
				}
			}
		}
	}

	//Cette fonction est utilisée dans le Mode bateaux tireurs uniquement
	public void sonar(){
		//Le sonar analyse si il y a un bateau dans la grille adverse
		if(this.isJoueur==true){
			if(this.grilleOrdi.getCellInfo(this.x,this.y)==1){
				this.sonar = true;
			}
		}
		else{
			if(this.grilleJoueur.getCellInfo(this.x,this.y)==1){
				this.sonar = true;
			}
		}
	}

	//Utilisé par l'IA
	public int getEtatCase(Coordonnees c){
		//On revoie l'état de la case selectionnée
		this.x = c.getX();
		this.y = c.getY();
		return grilleVisuOrdi.getCellInfo(this.x,this.y);
	}

	//Utilisé par l'IA (Mode bateaux tireurs uniquement)
	public boolean getSonar(){
		//On renvoie le résultat du sonar, c'est à dire si il a trouvé un/plusieurs bateau(x) dans la zone
		return this.sonar;
	}

	//Utilisé par l'IA (Mode bateaux tireurs uniquement)
	public int getSensTirTorpilleur(){
		//Le tir du torpilleur étant aléatoire, l'Ordi a besoin de savoir dans quel sens le tir à été effectué
		return this.randomTirTorpilleur;
	}

	//Utilisé dans la scène WinLoseScore
	public boolean getVictoire(){
		return this.victoire;
	}

	//Utilisé dans la scène WinLoseScore
	public int getScore(){
		return this.score;
	}
}