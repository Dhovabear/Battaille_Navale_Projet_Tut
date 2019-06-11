import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;

public class TestG extends Scene{

	private Grid grille;
	private Grid grilleRadar;
	private ArrayList<DGBateau> bts = new ArrayList<DGBateau>();
	private DGBateau b;

	public TestG(){
		grille = new Grid(50,20,50,10);
		bts.add(new DGBateau(150,100,0,grille));
		bts.add(new DGBateau(100,100,1,grille));
		bts.add(new DGBateau(200,100,3,grille));
		bts.add(new DGBateau(250,100,4,grille));

	}

	public void startEvent(){
	}

	public void update(){

	}

	public void draw(Graphics g, JPanel p){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		grille.draw(g);
		grille.drawBateaux(g,p);
		grille.drawGizmos(g);
		for (DGBateau b : bts ) {
			b.draw(g,p);
		}

	}

	public void input(KeyEvent e, String typeOfInput){
		if(typeOfInput == "kr"){
			for (DGBateau b : bts ) {
				b.rotate();
			}
		}
	}

	public void mouseInput(MouseEvent e , String typeOfInput){
		
		if(typeOfInput == "mP"){
			for (DGBateau b : bts ) {
				b.isIn(e);
			}
		}else if(typeOfInput == "mD"){
			for (DGBateau b : bts ) {
				b.drag(e);
			}
		}else if(typeOfInput == "mR"){
			for (DGBateau b : bts ) {
				b.stopDrag();
			}
		}
	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){

	}

}
