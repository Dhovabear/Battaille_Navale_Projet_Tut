package game.Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IdentityCard {
    private BufferedImage m_drapeau;
    private String m_nom;

    private int m_x;
    private int m_y;

    public IdentityCard(int x, int y, BufferedImage drapeau , String nom){
        this.m_drapeau = drapeau;
        this.m_nom = nom;
        this.m_x = x;
        this.m_y = y;
    }

    public void draw(Graphics g , JPanel p){
        g.drawImage(SpriteIndex.woodPancart,m_x,m_y,412,195,p);
        g.drawImage(m_drapeau,(m_x + 206) - (180/2),m_y+10,180,120,p);
        g.setFont(PoliceIndex.goodBrush.deriveFont(25f));
        g.setColor(new Color(153, 12, 32));
        int posX = g.getFontMetrics(g.getFont()).stringWidth(m_nom);
        g.drawString(m_nom,(m_x + 206) - (posX/2),m_y+170);
    }

    public int getX() {
        return m_x;
    }

    public int getY(){
        return m_y;
    }

    public void setX(int x) {
        m_x = x;
    }

    public void setY(int y){
        m_y = y;
    }
}
