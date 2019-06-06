
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;

public class Bateau {

	public int x;
	public int y;
	public int orientation;
	public int type;
	public BufferedImage img;

	public static BufferedImage[] imgBateaux;

	public Bateau(int x , int y , int o , int ttype){
		this.x = x;
		this.y = y;
		orientation = o;
		type = ttype;
		img = imgBateaux[ttype];
	}

	public static BufferedImage getBateauImage(int v){
		return imgBateaux[v];
	}

	public static void loadBoatImages(){
		imgBateaux = new BufferedImage[5];
		try{
			imgBateaux[0] = ImageIO.read(new File("batDeuxCase.png"));
			imgBateaux[1] = ImageIO.read(new File("batTroisCase.png"));
			imgBateaux[3] = ImageIO.read(new File("batQuatreCase.png"));
			imgBateaux[4] = ImageIO.read(new File("batCinqCase.png"));
		}catch(Exception e){

		}
		
		//ON LOADERA LES IMAGES LOL
	}


}