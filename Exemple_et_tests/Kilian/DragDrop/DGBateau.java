
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
	private int bateauToSpawn;
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

	private int gridX = -1;
	private int gridY = -1;

	public DGBateau(int x , int y , int bateauToSpawn , Grid parent){
		this.x = x;
		this.y = y;
		img = Bateau.imgBateaux[bateauToSpawn];
		this.parent = parent;

		int taille;
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
	}

	 
	public void drag(MouseEvent e){
		if(!isDragged){return;}
		this.x = e.getX()- (w/2);
		this.y = e.getY() - (w/2);
		switch(rotation){
			case 0:
				xc = x;
				yc = y;
				break;
			case 1:
				xc = x + h;
				yc = y + w;
				break;

		}
	}

	public void stopDrag(){
		if(isDragged){
			magnet();
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
		g.setColor(Color.RED);
		g.fillOval(x,y,3,3);
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
	}

	public void rotate(){
		if(!isDragged){return;}
		rotation++;
		if(rotation > 3){
			rotation = 0;
		}
	}

	public void magnet(){
		if(x+(w/2) < parent.getXPos() || x+(w/2) > parent.getXPos() + (parent.getCellSize()*parent.getNbrOfCell())
			|| y+(w/2) < parent.getYPos() || y+(w/2) > parent.getYPos() + (parent.getCellSize() * parent.getNbrOfCell())){
			return;
		}
		x = parent.getXPos() + (((x+(w/2))/parent.getCellSize())*parent.getCellSize())-parent.getCellSize();
		y = parent.getYPos() + (((y+(w/2))/parent.getCellSize())*parent.getCellSize());
		anticollide();
	}

	void anticollide(){
		for (DGBateau b : occurences ) {
			if(this.equals(b)){continue;}
			if(this.collide(b)){
				this.x = xo;
				this.y = yo;
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


}