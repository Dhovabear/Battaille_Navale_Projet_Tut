package game.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SheepFace {

    private AnimatedSprite lowLifeFace;
    private boolean isLowLife;
    private BufferedImage currentFace;

    public SheepFace(){
        lowLifeFace = new AnimatedSprite(0,0,100,100,30,SpriteIndex.lowLifeSheep);
        setNeutral();
    }

    public void drawAt(int x,int y,Graphics g, JPanel p){
        if(isLowLife && currentFace.equals(SpriteIndex.neutralSheep)){
            lowLifeFace.drawAt(x,y,100,100,g,p);
            lowLifeFace.updateTime();
        }else{
            g.drawImage(currentFace,x,y,100,100,p);
        }
    }

    public void setHurt(){
        currentFace = SpriteIndex.hurtSheep;
    }

    public void setHappy(){
        currentFace = SpriteIndex.happySheep;
    }

    public void setNeutral(){
        currentFace = SpriteIndex.neutralSheep;
    }

    public void setLowLife(boolean state){
        isLowLife = state;
    }

}
