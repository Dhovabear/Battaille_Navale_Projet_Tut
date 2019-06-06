
import java.io.*;
import javax.sound.sampled.*;

public class Son{

	private Clip c;
	private long tmcd;
	private boolean isPaused;
	private String path;
	private AudioInputStream snd;
	private boolean isInLoop;

	public Son(String tpath , boolean loop){
		try{
			snd = AudioSystem.getAudioInputStream(new File(tpath).getAbsoluteFile());
			c = AudioSystem.getClip();
			
			if(loop){
				c.loop(Clip.LOOP_CONTINUOUSLY);
			}
			path = tpath;
			isInLoop = loop;
		}catch(Exception e){
			System.out.println("Erreur lors du chargement de " + path + "verifier l'extension du fichier ou l'existence de ce dernier");
		}
	}

	public void start(){
		try{
			c.open(snd);
			c.start();
		}catch(Exception e){
			
		}
		
	}

	public void resume(){
		c.start();
		if(isInLoop){
        	c.loop(Clip.LOOP_CONTINUOUSLY);
        }
	}

	public void pause(){
		c.stop();
	}

	public void stop(){
		tmcd = 0;
		c.stop();
	}

	void reset() throws Exception{
		snd = AudioSystem.getAudioInputStream( 
        new File(path).getAbsoluteFile()); 
        c.open(snd);
        if(isInLoop){
        	c.loop(Clip.LOOP_CONTINUOUSLY);
        }
	}

}