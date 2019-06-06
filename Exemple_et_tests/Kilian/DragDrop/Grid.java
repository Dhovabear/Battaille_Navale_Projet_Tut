
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;
import java.lang.Math;

public class Grid{

	private int m_xPos;
	private int m_yPos;
	private int m_cellSize;
	private int m_nbOfCell;

	private int[][] grille;

	private int m_nbrBateau;
	private List<Bateau> bateaux = new ArrayList<Bateau>();

	public Grid(int x , int y , int cSize , int nbOC){
		m_xPos = x;
		m_yPos = y;
		m_cellSize = cSize;
		m_nbOfCell = nbOC;
		grille = new int[m_nbOfCell][m_nbOfCell];
	}

	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		for(int i = 0; i < m_nbOfCell+1 ; i++){
			for (int j = 0; j < m_nbOfCell+1 ; j++ ) {
				g.drawLine(m_xPos,j*m_cellSize + m_yPos,(m_cellSize*m_nbOfCell) + m_xPos,j*m_cellSize + m_yPos);
			}
			g.drawLine((i*m_cellSize) + m_xPos ,m_yPos,i*m_cellSize + m_xPos,(m_cellSize*m_nbOfCell) + m_yPos);

		}
	}

	public int getCellInfo(int x,int y){
		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return -1;
		return grille[x][y];
	}

	public boolean setCellInfo(int x, int y , int val){
		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return false;
		grille[x][y] = val;
		return true;
	}

	public boolean addBateau(int x , int y , int o , int type){
		if((x > m_nbOfCell-1 || x < 0) || (y > m_nbOfCell-1 || y < 0))
			return false;
		switch(o){
			case 0 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					if((x > m_nbOfCell-1 || x < 0) || (y+i > m_nbOfCell-1 || y+i < 0)){return false;}
					if(grille[x][y+i] == 1){return false;}
				}
				break;
			case 1 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					if((x-i > m_nbOfCell-1 || x-i < 0) || (y > m_nbOfCell-1 || y < 0)){return false;}
					if(grille[x-i][y] == 1){return false;}
				}
				break;
			case 2 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					if((x > m_nbOfCell-1 || x < 0) || (y-i > m_nbOfCell-1 || y-i < 0)){return false;}
					if(grille[x][y-i] == 1){return false;}
				}
				break;
			case 3 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					if((x+i > m_nbOfCell-1 || x+i < 0) || (y > m_nbOfCell-1 || y < 0)){return false;}
					if(grille[x+i][y] == 1){return false;}
				}
				break;
		}

		switch(o){
			case 0 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					grille[x][y+i] = 1;
				}
				break;
			case 1 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					grille[x-i][y] = 1;
				}
				break;
			case 2 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					grille[x][y-i] = 1;
				}
				break;
			case 3 :
				for (int i = 0 ; i < type+2 ; i++ ) {
					grille[x+i][y] = 1;
				}
				break;
		}
		bateaux.add(new Bateau(x,y,o,type));
		return true;
	}

	public int getNbrOfBateau(){
		return m_nbrBateau;
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

	public void drawGizmos(Graphics g){
		for (int y = 0; y < m_nbOfCell ; y++ ){
			for (int x = 0; x < m_nbOfCell ; x++ ){
				switch(grille[x][y]){
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
						g.setColor(Color.BLACK);
						g.fillOval((x*m_cellSize)+m_xPos , (y*m_cellSize)+m_yPos , m_cellSize , m_cellSize );
						g.setColor(Color.WHITE);
						g.fillOval((x*m_cellSize)+m_xPos , (y*m_cellSize)+m_yPos , m_cellSize-1, m_cellSize-1);
						break;

					case 5:
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
}

/*
-1: erreur negro
0: ya rien sur la case
1: ya un bout bateau sur la case
2: ya un bout de bateau toucher a cet endroit
3: ya un bout de bateau couler sur la case

4: jeton blanc pour dire qun a tirer a cet endroit
5: jeton rouge pour dire qu'on a toucher


ORIENTATION DES BATEAUX: 
0:sud
1:ouest
2:nord
3:est
*/