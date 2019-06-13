package game.scenes;

import game.Objects.*;
import game.Objects.ia.IA;
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
	private int mode; //Scène 2 - JoueurVsOrdi
	private int difficulte; //Scène 3 - DifficulteOrdi
	private BufferedImage drapeauJ; //Scène 4 - DrapeauNom
	private BufferedImage drapeauO; //Scène 4 - DrapeauNom
	private String nomJ = DrapeauNom.getNomJoueur(); //Scène 4 - DrapeauNom
	private String nomO = DrapeauNom.getNomOrdi(); //Scène 4 - DrapeauNom
	
	//Variables servant pour le système de base de la bataille navale
	private int x; //Axe des abscisses
	private int y; //Axe des ordonnées
	private Grid grilleJoueur; //Grille personnelle du joueur
	private Grid grilleVisuJoueur; //Servira au joueur pour voir la grille initiale de l'ordi
	private Grid grilleOrdi; //Grille personnelle de l'ordi
	private Grid grilleVisuOrdi; //Servira à l'ordi pour voir la grille initiale du joueur
	private boolean isJoueur; //Servira dans plusieurs fonctions pour savoir s'il s'agit du joueur ou de l'ordi
	private int vieJoueur; //Baissera au fur et à mesure que l'ordi touche les bateaux du joueur
	private int vieOrdi; //Baissera au fur et à mesure que le joueur touche les bateaux ordi

	//Variables servant pour le placement des bateaux, le touché-coulé et le Mode bateaux tireurs
	private int tailleBateau;
	private int sensBateau;
	/*
	*1 = Haut  2
	*2 = Gauche 1
	*3 = Bas    0
	*4 = Droite 3
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

	//pour le drag and drop
	private boolean dragAndDropEnded = false;
	private boolean m_IntroDragFini = false;
	private int m_timeintroDrag = 0;
	private ArrayList<DGBateau> m_boatToPlace;

	private AnimatedSprite test;

	private Font bitcrusher;

	private boolean waitForTir;

	private IA ordi;

	public Jouer() throws IOException, FontFormatException {
		bitcrusher = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/polices/bitcrusher.ttf"));
		test = new AnimatedSprite(0,0,100,100,30,new String[]{"/images/moutons/mAlert_0.png", "/images/moutons/mAlert_1.png", "/images/moutons/mAlert_2.png"});
	}

	public void update() throws IOException, FontFormatException {
		//Si le joueur abandonne, il retourne au menu principal

		//Si le joueur ou l'Ordi a tous ses bateaux coulés, la partie se termine
		if(this.vieOrdi==0 || this.vieJoueur==0){ 
			if(this.vieOrdi==0){
				this.victoire = true;
			}
			else{
				this.victoire = false;
			}
			Game.switchScene(0);
		}


		if(waitForTir){
			return;
		}

		//On passe du tour du Joueur à celui de l'Ordi et vice versa

		//Le joueur ou l'ordi selectionne l'endroit où il veut tirer (ainsi que le bateau qui tire (Mode bateaux tireurs uniquement))
		//selectionTir();
	}

	public void setGrids(Grid gJ1 , Grid vJ1 , Grid gOrdi , Grid vOrdi){
		grilleJoueur = gJ1;
		grilleVisuJoueur = vJ1;
		grilleOrdi = gOrdi;
		grilleVisuOrdi = vOrdi;

	}

	public void finTour(){
		if(this.isJoueur==true){
			this.isJoueur = false;
			System.out.println("Tour Ordi !");
			Thread t = new Thread(){
				@Override
				public void run() {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ordi.jouerIA();
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finTour();
				}
			};
			t.start();
		}
		else{
			this.isJoueur = true;
			System.out.println("Tour Joueur !");
			waitForTir = true;
		}
	}

	public void draw(Graphics g , JPanel p){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,p.getWidth(),p.getHeight());

		grilleJoueur.draw(g, p);
		grilleJoueur.drawGizmos(g);
		grilleVisuJoueur.draw(g, p);
		grilleVisuJoueur.drawGizmos(g);

		g.drawImage(drapeauJ,60,450,150,75,p);
		g.drawImage(drapeauO, p.getWidth()-((grilleVisuOrdi.getCellSize()*10)+50),450,150,75,p);
		g.setFont(PoliceIndex.autoradio.deriveFont(20f));
		g.setColor(Color.BLACK);
		g.drawString(nomJ,220,500);
		g.drawString(nomO,p.getWidth()-((grilleVisuOrdi.getCellSize()*10)-120),500);

		//test.draw(g, p);
		if(waitForTir){
			if(grilleVisuJoueur.isMouseIn()){
				this.x = grilleVisuJoueur.getCursorXInGrid();
				this.y = grilleVisuJoueur.getCursorYInGrid();
				System.out.println("X: "+x+"  Y:"+y+" state: " + grilleVisuJoueur.getCellInfo(x,y));
				g.setColor(new Color(255,0,0, 118));
				if(Game.debugModeEnabled && autorisationTirJoueur()){
					g.fillRect(grilleVisuJoueur.getXPos() + (x*grilleVisuJoueur.getCellSize()),grilleJoueur.getYPos() + (y*grilleVisuJoueur.getCellSize()),grilleVisuJoueur.getCellSize(),grilleVisuJoueur.getCellSize());
				}

			}
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

		drapeauJ = DrapeauNom.getDrapeauJoueur();
		drapeauO = DrapeauNom.getDrapeauOrdi();

		nomJ = DrapeauNom.getNomJoueur();
		nomO = DrapeauNom.getNomOrdi();

		grilleVisuJoueur.setXPos(Game.fenetre.getWidth() - ((grilleVisuJoueur.getCellSize()*grilleVisuJoueur.getNbrOfCell()) + 60) );
		grilleVisuJoueur.setYPos(20);

		grilleJoueur.setCellSize(40);

		waitForTir = true;
		//On définit les différentes tailles de chaque bateau
		mode= JoueurVsOrdi.getSelectionMode();
		difficulte = DifficulteOrdi.getDifficulteOrdi();

		//[AFFICHER JOUER]
		//placerBateau();
		System.out.println(difficulte);
		System.out.println(mode);
		ordi = new IA(difficulte,mode,this);
		ordi.placement();

		//TESTTTTT
		/*grilleOrdi.addBateau(0,0,0,0);
		grilleOrdi.addBateau(1,0,0,1);
		grilleOrdi.addBateau(2,0,0,2);
		grilleOrdi.addBateau(3,0,0,3);
		grilleOrdi.addBateau(4,0,0,4);*/

	}

	public void input(KeyEvent e,String typeOfInput){

	}

	public void mouseInput(MouseEvent e,String typeOfInput){
		grilleVisuJoueur.checkMouse(e, typeOfInput);

		if(waitForTir && isJoueur){
			if(typeOfInput.equals("mP") && grilleVisuJoueur.isMouseIn()){
				tir();
				finTour();
			}
			return;
		}
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
				this.tailleBateau = 2;
				break;
			case 3:
				this.tailleBateau = 4;
				break;
			case 4:
				this.tailleBateau = 5;
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

	public boolean autorisationTirJoueur(){
		//Grâce à cette condition, le joueur ne peut pas tirer à un endroit où il a déjà tiré, ainsi que sur des îles (Mode île uniqueme;
		//Grâce à cette condition, les joueur ne peut pas tirer 2 fois de suite avec le même bateau, et il ne peut pas tirer si ce même bateau à été coulé
		if(this.mode == 3 && (this.grilleJoueur.getBateauWithType(typeBateau).getEnVie()==false || this.utilisationTirJoueur==this.typeBateau)){
			return false;
		}
		//System.out.println("pjopjiosfepji");
		return true;
	}

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

		Grid focusedGrid;
		Grid grilleVisu;

		if(this.isJoueur == true){
			focusedGrid = grilleOrdi;
			grilleVisu = grilleVisuJoueur;
		}else{
			focusedGrid = grilleJoueur;
			grilleVisu = grilleVisuOrdi;
		}


			//Si le tir ne sort pas de la grille (Vérification pour le Mode bateaux tireurs)
			if((this.x>-1 && this.x<10) && (this.y>-1 && this.y<10)){
				//Si le tir touche une case bateau adverse
				if(focusedGrid.getCellInfo(this.x,this.y)==1 && (focusedGrid.getGizmoInfo(this.x,this.y) != 6 || focusedGrid.getGizmoInfo(this.x,this.y) != 4 )){
					//On indique sur la grille de l'ordi qu'une de ses cases bateau à été touchée
					focusedGrid.setGizmo(this.x,this.y,2);
					//On indique sur la grille de visu du joueur que le tir à touché
					grilleVisu.setGizmo(this.x,this.y,6);
					//La vie de l'Ordi baisse
					if(isJoueur){
						this.vieOrdi--;
						this.degatsOrdi++;
					}

					//On vérifie quelle coordonnée de quel bateau à été touchée, on applique le processus en résultant (voir l'objet Bateau et l'objet Coordonnée)
					for(int i=0;i<focusedGrid.getNbrOfBateau();i++){
						if(focusedGrid.getBateau(i).touche(this.x,this.y)==true){
							break;
						}
					}

					//BOUCLE POUR METTRE LES PIONS SUR SI CA A COULER
				}
				else /*if(focusedGrid.getCellInfo(this.x,this.y) != 0)*/{
					grilleVisu.setGizmo(this.x,this.y,5);
					this.precision--;
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

	public Bateau getBateauxJoueur(int i) {
		return grilleJoueur.getBateau(i);
	}

	public Grid getGrilleOrdi(){
		return grilleOrdi;
	}

	public Grid getGrilleVisuordi(){
		return grilleVisuOrdi;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}