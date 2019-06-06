
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;

public class Credits extends Scene {

	private BufferedImage crImg ;
	private float ypos;
	private Clip gESound ;
	private Clip son2;
	private Son test;

	public Credits(){
		try{
			crImg = ImageIO.read(new File("kredilol.png"));
		}catch(Exception e){

		}
		test = new Son("sonDGE.wav",true);
		
	}

	public void startEvent(){
		ypos = 600f;
		test.start();
	}

	public void update(){

	}

	public void draw(Graphics g, JPanel p){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,p.getWidth(),p.getHeight());
		ypos -= 0.3;
		g.drawImage(crImg,400,(int)ypos,p);
	}

	public void input(KeyEvent e, String typeOfInput){
		if(typeOfInput == "kp" && e.getKeyChar() == 'q'){
			Game.quit();
		}
	}

	public void mouseInput(MouseEvent e, String typeOfInput){

	}

	public void mouseWheelInput(MouseWheelEvent e){

	}

	public void exitEvent(){
		gESound.stop();
		test.stop();
	}
}