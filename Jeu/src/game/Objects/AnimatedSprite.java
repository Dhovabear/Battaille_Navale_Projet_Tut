package game.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AnimatedSprite {
    private BufferedImage[] m_sprites;
    private int m_frameToWait;
    private int m_xPos;
    private int m_yPos;
    private int m_width;
    private int m_heigth;

    private int m_currentFrame;
    private int m_counter;

    public AnimatedSprite(int x,int y,int w , int h, int ftw, String[] paths) throws IOException {
        this.m_xPos = x;
        this.m_yPos = y;
        this.m_width = w;
        this.m_heigth = h;
        this.m_frameToWait = ftw;
        this.m_currentFrame = 0;
        this.m_counter = 0;

        m_sprites = new BufferedImage[paths.length];

        for (int i = 0; i < paths.length ; i++){
            m_sprites[i] = ImageIO.read(getClass().getResourceAsStream(paths[i]));
        }
    }

    public AnimatedSprite(int x,int y,int w, int h, int ftw , BufferedImage[] imgs){
        this.m_xPos = x;
        this.m_yPos = y;
        this.m_width = w;
        this.m_heigth = h;
        this.m_frameToWait = ftw;
        this.m_currentFrame = 0;
        this.m_counter = 0;

        m_sprites = imgs;
    }

    public void draw(Graphics g, JPanel p){
        updateTime();
        drawAt(m_xPos,m_yPos,m_width,m_heigth,g,p);
    }

    public void setX(int x){
        this.m_xPos = x;
    }

    public void drawAt(int x,int y,int w,int h, Graphics g, JPanel p){
        g.drawImage(m_sprites[m_currentFrame],x,y,w,h,p);
    }

    public void updateTime(){
        m_counter++;
        if(m_counter >= m_frameToWait){
            //System.out.println("zbra");
            m_currentFrame++;
            if(m_currentFrame >= m_sprites.length){
                m_currentFrame = 0;
            }
            m_counter = 0;
        }
    }

    public void setY(int y){
        this.m_xPos = y;
    }

}
