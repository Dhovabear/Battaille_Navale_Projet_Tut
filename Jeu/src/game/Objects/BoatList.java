package game.Objects;

import javax.swing.*;
import java.awt.*;

public class BoatList {
    private Grid grille;

    public BoatList(Grid grille){
        this.grille = grille;
    }

    public void drawAt(int x , int y, Graphics g , JPanel p){
        g.setColor(Color.BLACK);
        g.fillRect(x,y,60,300);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x+4,y+4,60-8,300-8);
        
        for (int i = 0; i < grille.getBateaux().size(); i++) {
            g.drawImage(SpriteIndex.imagesBateaux[i],x+5,y+(10*i),10*i,10,p);
        }
    }
}
