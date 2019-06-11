package game.engine.ui;

import game.engine.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Label {
     private int m_posx;
     private int m_posy;
     private String m_text;
     private Font m_police;

     public Label(int x,int y,float taille,String policeName,String text){
         this.m_posx = x;
         this.m_posy = y;
         this.m_text = text;

         try {
             this.m_police = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/polices/"+policeName));
             this.m_police = this.m_police.deriveFont(taille);
         } catch (FontFormatException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }

     public void draw(Graphics g, JPanel p){
         g.setColor(Color.black);
         g.setFont(m_police);
         g.drawString(m_text,m_posx,m_posy);
         if(Game.debugModeEnabled){
             g.setColor(Color.RED);
             g.fillOval(m_posx-1,m_posy-1,3,3);
         }
     }
}
