package game.Objects;

import game.engine.Game;
import game.engine.ui.BouttonImage;
import game.engine.ui.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PauseMenu {

    private boolean isActive;
    private Label titre;
    private BouttonImage abandonner;
    private BouttonImage mute;

    private int xpos;
    private int ypos;
    private int width;
    private int height;

    public PauseMenu(){
        isActive = false;

        xpos = 500;
        ypos = 100;

        width = 220;
        height = 220;

        titre = new Label(xpos+80,ypos+46,40f,PoliceIndex.bitcrusher,"Pause");
        abandonner = new BouttonImage(xpos+50,ypos+26+54,120,54,SpriteIndex.giveUp,SpriteIndex.giveUp){
            @Override
            public void action() throws IOException, FontFormatException {
                //System.out.println("qdqdzqdzqdqdqzddqdqzddqdzqdzqd");
                SoundLibrary.stopAll();
                isActive = false;
                Game.switchScene(0);
            }
        };
        abandonner.setEnabled(true);
        mute = new BouttonImage(xpos+80,ypos+150,50,50,SpriteIndex.bouttonMuteEnabled,SpriteIndex.bouttonMuteEnabled){
            @Override
            public void action() throws IOException, FontFormatException {
                SoundLibrary.setMute(!SoundLibrary.isMute());

                if(SoundLibrary.isMute()){
                    this.setEnabledImage(SpriteIndex.bouttonMuteDisabled);
                }else{
                    this.setEnabledImage(SpriteIndex.bouttonMuteEnabled);
                }
            }
        };
        mute.setEnabled(true);

    }

    public void checkMouse(MouseEvent e, String typeOfInput){
        if(!isActive){return;}
        System.out.println("ddd");
        abandonner.checkMouse(e, typeOfInput);
        mute.checkMouse(e, typeOfInput);
    }

    public void checkKeyboard(KeyEvent e,String typeOfInput){
        if(typeOfInput == "kr" && e.getKeyCode() == 27){
            isActive = !isActive;
            if(SoundLibrary.isMute()){
                mute.setEnabledImage(SpriteIndex.bouttonMuteDisabled);
            }else{
                mute.setEnabledImage(SpriteIndex.bouttonMuteEnabled);
            }
        }
    }

    public void draw(Graphics g , JPanel p){
        if(!isActive){return;}
        g.setColor(Color.BLACK);
        g.fillRect(xpos,ypos,width,height);
        g.setColor(new Color(184, 197, 192));
        g.fillRect(xpos+4,ypos+4,width-8,height-8);
        titre.draw(g, p);
        abandonner.draw(g, p);
        mute.draw(g, p);
    }

    public boolean isActive(){
        return isActive;
    }

}
