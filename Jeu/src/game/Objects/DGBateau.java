package game.Objects;

import game.engine.Game;

import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.Math;

public class DGBateau {

	private static boolean somethingIsDragged = false;
	public static ArrayList<DGBateau> occurences = new ArrayList<DGBateau>();
	private boolean isDragged;
	private BufferedImage img;
	private int rotation = 0;
	private Grid parent;

	private int x;
	private int y;
	private int w;
	private int h;

	private int wc;
	private int hc;
	private int xc;
	private int yc;

	private int xo;
	private int yo;
	private int oo;

	private int taille;

	private boolean  m_estBienPlacer;


	public DGBateau(int x , int y , int bateauToSpawn , Grid parent){
		this.x = x;
		this.y = y;
		img = Bateau.imgBateaux[bateauToSpawn];
		this.parent = parent;

		if(bateauToSpawn == 1 || bateauToSpawn == 2){
			taille = 3;
		}else if(bateauToSpawn == 0){
			taille = 2;
		}else if(bateauToSpawn > 2){
			taille = bateauToSpawn +1;
		}else{
			taille = 10;
		}
		w = parent.getCellSize();
		h = parent.getCellSize()*(taille);
		occurences.add(this);
		m_estBienPlacer = false;
		//setCollider();
	}

	 
	public void drag(MouseEvent e){
		if(!isDragged){return;}
		this.x = e.getX()- (w/2);
		this.y = e.getY() - (w/2);
		setCollider();
	}

	public void stopDrag(){
		if(isDragged){
			m_estBienPlacer = true;
			magnet();
			setCollider();
			anticollide();
		}
		somethingIsDragged = false;
		isDragged = false;

	}

	public void draw(Graphics g, JPanel p){
		Graphics2D g2 = (Graphics2D)g;
		int rt = 0;
		switch(rotation){
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
		g2.rotate(Math.toRadians(rt),x+(w/2),y+(w/2));
		g.drawImage(img,x,y,w,h,p);
		g2.rotate(Math.toRadians(-rt),x+(w/2),y+(w/2));

		if(Game.debugModeEnabled){
			g.setColor(Color.RED);
			//g.fillOval(x+(w/2),y+(w/2),3,3);
			g.drawRect(xc,yc,wc, hc);

			if(m_estBienPlacer){
				g.setColor(new Color(140, 221, 92, 152));
				g.fillOval(x,y,w,h);
			}
		}
	}

	public void isIn(MouseEvent e){
		if(somethingIsDragged){return;}
		switch(rotation){
			case 0 :
				if(e.getX() >= x && e.getX() < x+w && e.getY() >= y && e.getY() < y+h){
					isDragged = true;
					somethingIsDragged = true;
				}
				break;
			case 1 : 
				if(e.getX() >= x-h+w && e.getX() < x+w && e.getY() >= y && e.getY() < y+w){
					isDragged = true;
					somethingIsDragged = true;
				}
				break;
			case 2 : 
				if(e.getX() >= x && e.getX() < x+w && e.getY() >= y-h+w && e.getY() < y+w){
					isDragged = true;
					somethingIsDragged = true;
				}
				break;
			case 3 : 
				if(e.getX() >= x && e.getX() < x+h && e.getY() >= y && e.getY() < y+w){
					isDragged = true;
					somethingIsDragged = true;
				}
				break;
		}
		xo = this.x;
		yo = this.y;
		oo = this.rotation;
	}

	public void rotate(){
		if(!isDragged){return;}
		rotation++;
		if(rotation > 3){
			rotation = 0;
		}
		setCollider();

	}

	public void rotateBack(){
		if(!isDragged){return;}
		rotation--;
		if(rotation < 0){
			rotation = 3;
		}
		setCollider();
	}

	public void setCollider(){
		switch(rotation){
			case 0:
				xc = x;
				yc = y;
				wc = w;
				hc = h;
				break;
			case 1:
				xc = (x-h) + parent.getCellSize();
				yc = y;
				wc = h;
				hc = w;
				break;
			case 2:
				xc = x;
				yc = (y - h) + parent.getCellSize();
				wc = w;
				hc = h;
				break;
			case 3:
				xc = x;
				yc = y;
				hc = w;
				wc = h;
				break;

		}
	}

	public void magnet(){
		if(x+(w/2) < parent.getXPos() || x+(w/2) > parent.getXPos() + (parent.getCellSize()*parent.getNbrOfCell())
			|| y+(w/2) < parent.getYPos() || y+(w/2) > parent.getYPos() + (parent.getCellSize() * parent.getNbrOfCell())){
			m_estBienPlacer = false;
			return;
		}
		//x = (parent.getXPos() + Math.floor(((x+(w/2))/parent.getCellSize())*parent.getCellSize()));

		int tmpx = (int)Math.floor(((x+(w/2))-parent.getXPos())/parent.getCellSize());
		int tmpy = (int)Math.floor(((y+(w/2))-parent.getYPos())/parent.getCellSize());

		System.out.println("x: " + tmpx + "  y: "+tmpy);

		x = parent.getXPos() + (tmpx*parent.getCellSize());
		//y = (parent.getYPos() + (((float)(y+(w/2))/parent.getCellSize())*parent.getCellSize()));
		y = parent.getYPos() + (tmpy*parent.getCellSize());
		if(!isInGrid()){
			x = xo;
			y = yo;
			rotation = oo;
			if(!isInGrid()){
				m_estBienPlacer = false;
			}else{
				m_estBienPlacer = true;
			}

		}
	}

	public boolean isInGrid(){
		if(x+(w/2) < parent.getXPos() || x+(w/2) > parent.getXPos() + (parent.getCellSize()*parent.getNbrOfCell())
				|| y+(w/2) < parent.getYPos() || y+(w/2) > parent.getYPos() + (parent.getCellSize() * parent.getNbrOfCell())){
			m_estBienPlacer = false;
			return false;
		}
		int tmpx = (int)Math.floor(((x+(w/2))-parent.getXPos())/parent.getCellSize());
		int tmpy = (int)Math.floor(((y+(w/2))-parent.getYPos())/parent.getCellSize());
		switch (rotation){
			case 0:
				for (int i = 0 ; i < taille; i++ ){
					System.out.println("y="+ (tmpy + i));
					if(tmpy + i >= parent.getNbrOfCell()){
						return false;
					}
					if(parent.getCellInfo(tmpx,tmpy+i) == 3){
						return false;
					}
				}
				return true;
			case 1:
				for (int i = 0 ; i < taille; i++ ){
					System.out.println("x="+ (tmpx + i));
					if(tmpx - i < 0){
						return false;
					}
					if(parent.getCellInfo(tmpx-i,tmpy) == 3){
						return false;
					}
				}
				return true;
			case 2:
				for (int i = 0 ; i < taille; i++ ){
					System.out.println("y="+ (tmpy - i));
					if(tmpy - i < 0){
						return false;
					}
					if(parent.getCellInfo(tmpx,tmpy-i) == 3){
						return false;
					}
				}

				return true;
			case 3:
				for (int i = 0 ; i < taille; i++ ){
					System.out.println("x="+ (tmpx - i));
					if(tmpx + i >= parent.getNbrOfCell()){
						return false;
					}
					if(parent.getCellInfo(tmpx+i,tmpy) == 3){
						return false;
					}
				}
				return true;
		}
		return true;
	}


	void anticollide(){
		for (DGBateau b : occurences ) {
			if(this.equals(b)){continue;}
			if(this.collide(b)){
				this.x = xo;
				this.y = yo;
				this.rotation = oo;
				if(isInGrid()){
					m_estBienPlacer = true;
				}
				setCollider();
			}
		}
	}

	boolean collide(DGBateau b){
		if((b.xc >= this.xc + this.wc)
			|| (b.xc + b.wc <= this.xc)
			|| (b.yc >= this.yc + this.hc)
			|| (b.yc + b.hc <= this.yc)){
			return false;
		}else{
			return true;
		}
	}


	public boolean estBienPlacer() {
		return m_estBienPlacer;
	}
}