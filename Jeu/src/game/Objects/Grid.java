package game.Objects;

import game.engine.Game;
import game.scenes.JoueurVsOrdi;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;
import java.util.Scanner;

public class Grid{

	private static final int AMPLEUR_ILE = 5;
	private int m_xPos;
	private int m_yPos;
	private int m_cellSize;
	private int m_nbOfCell;

	private int[][] grille;
	private int[][] gizmoGrid;


	private BufferedImage baseTile;
	private BufferedImage ileTile;

	private int m_mousePosX;
	private int m_mousePosY;

	private int m_noIle;
	private boolean m_isVisu;

	private List<Bateau> bateaux = new ArrayList<Bateau>();
	private boolean m_mouseIn;

	private AnimatedSprite water;

	public Grid(int x , int y , int cSize , int nbOC,boolean isVisu){
		m_xPos = x;
		m_yPos = y;
		m_cellSize = cSize;
		m_nbOfCell = nbOC;
		m_isVisu = isVisu;
		grille = new int[m_nbOfCell][m_nbOfCell];
		gizmoGrid = new int[m_nbOfCell][m_nbOfCell];

		water = new AnimatedSprite(0,0,cSize,cSize,5,SpriteIndex.waterTiles);

		if(!isVisu){
			ileTile = SpriteIndex.normalIleTile;
		}else{
			baseTile = SpriteIndex.radarWaterTile;
			ileTile = SpriteIndex.radarIleTile;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grille[j][i]);
			}
			System.out.print("\n");
		}

	}

	public void draw(Graphics g, JPanel p){
		g.setColor(Color.BLACK);

		for (int i = 0; i < m_nbOfCell; i++){
			for (int j = 0; j < m_nbOfCell; j++){
				if(getCellInfo(j,i) == 3){
					g.drawImage(ileTile,m_xPos+(j*getCellSize()),m_yPos+(i*getCellSize()),getCellSize(),getCellSize(),p);
				}else{
					if(!m_isVisu){
						water.drawAt(m_xPos+(j*getCellSize()),m_yPos+(i*getCellSize()),getCellSize(),getCellSize(),g,p);
					}else{
						g.drawImage(baseTile,m_xPos+(j*getCellSize()),m_yPos+(i*getCellSize()),getCellSize(),getCellSize(),p);
					}


				}
				if(getCellInfo(j,i) == 1 && Game.debugModeEnabled){
					g.setColor(Color.RED);
					g.fillRect(m_xPos+(j*getCellSize()),m_yPos+(i*getCellSize()),getCellSize(),getCellSize());
				}
			}
		}

		for(int i = 0; i < m_nbOfCell+1 ; i++){
			for (int j = 0; j < m_nbOfCell+1 ; j++ ) {
				g.drawLine(m_xPos,j*m_cellSize + m_yPos,(m_cellSize*m_nbOfCell) + m_xPos,j*m_cellSize + m_yPos);
			}
			g.drawLine((i*m_cellSize) + m_xPos ,m_yPos,i*m_cellSize + m_xPos,(m_cellSize*m_nbOfCell) + m_yPos);

		}

		g.setFont(PoliceIndex.autoradio.deriveFont(new Float(m_cellSize/2)));
		String tbl[] = {"A","B","C","D","E","F","G","H","I","J"};
		for(int i = 0; i < 10 ; i++){
			g.drawString(tbl[i],m_xPos + ((i*m_cellSize)+(m_cellSize/2)),m_yPos-2);
		}


		int tmpX;

		if(!m_isVisu){
			tmpX = m_xPos - getCellSize()/2 - 5;
		}else{
			tmpX = m_xPos+ (m_nbOfCell*m_cellSize) + m_cellSize/3;
		}

		for(int i = 0; i <10;i++){
			g.drawString(Integer.toString(i+1),tmpX,(m_yPos+((i*m_cellSize))) + m_cellSize/2 + 5 );
		}


		for (Bateau b: bateaux) {
			b.draw(g, p);
		}
		water.updateTime();
	}

	public int getCellInfo(int x,int y){
		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return -1;
		//System.out.println(grille[x][y]);
		return grille[x][y];
	}

	public void setGizmo(int x, int y,int gizmo){
		gizmoGrid[x][y] = gizmo;
	}

	public boolean setCellInfo(int x, int y , int val){
		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return false;
		grille[x][y] = val;
		return true;
	}

	public boolean addBateau(int x , int y , int o , int type){

		int taille;

		if(type == 1 || type == 2){
			taille = 3;
		}else if(type == 0){
			taille = 2;
		}else{
			taille = type+1;
		}

		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return false;
		switch(o){
			case 0 :
				for (int i = 0 ; i < taille ; i++ ) {
					if((x > m_nbOfCell-1 || x < 0) || (y+i > m_nbOfCell-1 || y+i < 0)){return false;}
					if(grille[x][y+i] != 0){return false;}
				}
				break;
			case 1 :
				for (int i = 0 ; i < taille ; i++ ) {
					if((x-i > m_nbOfCell-1 || x-i < 0) || (y > m_nbOfCell-1 || y < 0)){return false;}
					if(grille[x-i][y] != 0){return false;}
				}
				break;
			case 2 :
				for (int i = 0 ; i < taille ; i++ ) {
					if((x > m_nbOfCell-1 || x < 0) || (y-i > m_nbOfCell-1 || y-i < 0)){return false;}
					if(grille[x][y-i] != 0){return false;}
				}
				break;
			case 3 :
				for (int i = 0 ; i < taille ; i++ ) {
					if((x+i > m_nbOfCell-1 || x+i < 0) || (y > m_nbOfCell-1 || y < 0)){return false;}
					if(grille[x+i][y] != 0){return false;}
				}
				break;
		}

		switch(o){
			case 0 :
				for (int i = 0 ; i < taille ; i++ ) {
					grille[x][y+i] = 1;
				}
				break;
			case 1 :
				for (int i = 0 ; i < taille ; i++ ) {
					grille[x-i][y] = 1;
				}
				break;
			case 2 :
				for (int i = 0 ; i < taille ; i++ ) {
					grille[x][y-i] = 1;
				}
				break;
			case 3 :
				for (int i = 0 ; i < taille ; i++ ) {
					grille[x+i][y] = 1;
				}
				break;
		}
		bateaux.add(new Bateau(x,y,o,type,this));
		return true;
	}

	public int getNbrOfBateau(){
		return bateaux.size();
	}

	public void checkMouse(MouseEvent e, String typeOfInput){
		if(e.getX() < m_xPos || e.getX() > m_xPos + (m_cellSize*m_nbOfCell) || e.getY() < m_yPos || e.getY() > m_yPos + (m_cellSize*m_nbOfCell) ){
			m_mouseIn = false;
			return;
		}
		m_mouseIn = true;
		m_mousePosX = e.getX();
		m_mousePosY = e.getY();
	}

	public void drawBateaux(Graphics g , JPanel p){
		Graphics2D g2 = (Graphics2D)g;
		for (Bateau b : bateaux ) {
			switch(b.orientation){
				case 0 :
					//g2.rotate(Math.toRadian())
					g.drawImage(b.img,(b.x*m_cellSize) + m_xPos,(b.y*m_cellSize) + m_yPos,m_cellSize , m_cellSize*(b.type+2),p);
					break;
				case 1 :
					g2.rotate(Math.toRadians(90),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					g.drawImage(b.img,(b.x*m_cellSize) + m_xPos,(b.y*m_cellSize) + m_yPos,m_cellSize , m_cellSize*(b.type+2),p);
					g2.rotate(Math.toRadians(-90),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					break;
				case 2 :
					g2.rotate(Math.toRadians(180),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					g.drawImage(b.img,(b.x*m_cellSize) + m_xPos,(b.y*m_cellSize) + m_yPos,m_cellSize , m_cellSize*(b.type+2),p);
					g2.rotate(Math.toRadians(-180),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					break;
				case 3:
					g2.rotate(Math.toRadians(-90),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					g.drawImage(b.img,(b.x*m_cellSize) + m_xPos,(b.y*m_cellSize) + m_yPos,m_cellSize , m_cellSize*(b.type+2),p);
					g2.rotate(Math.toRadians(90),((b.x*m_cellSize) + m_xPos) + m_cellSize/2,((b.y*m_cellSize) + m_yPos) + m_cellSize/2);
					break;
			}
		}
	}

	public void drawGizmos(Graphics g,JPanel p){
		for (int y = 0; y < m_nbOfCell ; y++ ){
			for (int x = 0; x < m_nbOfCell ; x++ ){
				switch(gizmoGrid[x][y]){
					case 2 :
						g.setColor(Color.RED);
						g.fillOval((x*m_cellSize)+m_xPos  , (y*m_cellSize)+m_yPos , m_cellSize , m_cellSize );
						g.setColor(Color.ORANGE);
						g.fillOval((x*m_cellSize)+m_xPos + 5, (y*m_cellSize)+m_yPos+5 , m_cellSize-10 , m_cellSize-10);
						g.setColor(Color.YELLOW);
						g.fillOval((x*m_cellSize)+m_xPos+10, (y*m_cellSize)+m_yPos+10, m_cellSize-20 , m_cellSize-20);
						break;
					case 3 :
						g.setColor(Color.GRAY);
						g.fillOval((x*m_cellSize)+m_xPos , (y*m_cellSize)+m_yPos , m_cellSize , m_cellSize );
						break;

					case 4:
						g.drawImage(SpriteIndex.croixImage,(x*m_cellSize)+m_xPos,(y*m_cellSize)+m_yPos,m_cellSize,m_cellSize,p);
						break;

					case 5:
						g.setColor(Color.WHITE);
						g.fillOval((x*m_cellSize)+m_xPos , (y*m_cellSize)+m_yPos , m_cellSize , m_cellSize );
						break;

					case 6:
						g.setColor(Color.RED);
						g.fillOval((x*m_cellSize)+m_xPos , (y*m_cellSize)+m_yPos , m_cellSize , m_cellSize );
						break;


				}	
			}
		}
	}

	public void setPosition(int x , int y){
		m_xPos = x;
		m_yPos = y;
	}

	public int getCellSize(){return m_cellSize;}

	public int getXPos(){return m_xPos;}

	public int getYPos(){return m_yPos;}

	public int getNbrOfCell(){return m_nbOfCell;}

	public Bateau getBateauWithType(int type){
		for (Bateau b: bateaux) {
			if(b.type == type){return b;}
		}
		return null;
	}

	public Bateau getBateau(int index){return bateaux.get(index);}

	public void remplissage(){
		String fichier = new String();
		int random = (int)(Math.round(Math.random()*AMPLEUR_ILE));
		//L'emplacement de fichiers sera sûrement changé au fil du temps, il faudra donc modifier les chemins (commentaire à effacer par la suite)
		//On choisit une carte dans un fichier aléatoire (Utile pour le mode île)
		setIsland(random);
	}

	public void setIsland(int noIle){
		m_noIle = noIle;
		try //On ouvre le fichier
		{
			//BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/donnes/iles/ile"+random)));
			Scanner scan = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/donnes/iles/ile"+noIle)));
			//On remplit les grilles avec le contenu du fichier selectionné au hasard (Mode île uniquement)
			if(JoueurVsOrdi.getSelectionMode() == 2){
				while(scan.hasNextInt()){
					for(int i=0;i<10;i++){
						System.out.println("");
						for(int j=0;j<10;j++){
							int st = scan.nextInt();
							setCellInfo(j,i,st);
							System.out.print(st);
						}
					}
				}
				System.out.println("\n");
			}
		}
		catch(Exception e) //Implémentée dû au refus de la compilation quand cette condition n'est pas présente, mais inutile dans les faits (ce qui est faux)
		{
			e.printStackTrace();
		}
	}

	public int getIleId(){
		return m_noIle;
	}

	public void setXPos(int x){
		m_xPos = x;
	}

	public void setYPos(int y){
		m_yPos = y;
	}

	public void setCellSize(int i) {
		m_cellSize = i;
	}

	public boolean isMouseIn() {
		return m_mouseIn;
	}

	public int getCursorXInGrid() {
		if(!m_mouseIn){
			return -1;
		}

		return (int)Math.floor((m_mousePosX-m_xPos)/m_cellSize);
	}

	public int getCursorYInGrid() {
		if(!m_mouseIn){
			return -1;
		}

		return (int)Math.floor((m_mousePosY-m_yPos)/m_cellSize);
	}

	public Bateau getBateauOfType(int type){
		for ( Bateau b: bateaux) {
			if(b.type == type){
				return b;
			}
		}
		return null;
	}

	public int getGizmoInfo(int x, int y) {
		return gizmoGrid[x][y];
	}

}

/*
-1: erreur negro
0: ya rien sur la case
1: ya un bout bateau sur la case
2: ya un bout de bateau toucher a cet endroit
3: iles
4: ya un bout de bateau couler sur la case

5: jeton blanc pour dire qun a tirer a cet endroit
6: jeton rouge pour dire qu'on a toucher


ORIENTATION DES BATEAUX: 
0:sud
1:ouest
2:nord
3:est
*/