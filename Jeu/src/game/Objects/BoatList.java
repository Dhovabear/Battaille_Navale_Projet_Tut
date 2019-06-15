package game.Objects;

import javax.swing.*;
import java.awt.*;

public class BoatList {

    private Grid grille;
    private boolean isFlippedRight;

    public BoatList(Grid grille,boolean flipedRight){
        this.grille = grille;
        this.isFlippedRight = flipedRight;
    }

    public void drawAt(int x , int y, Graphics g , JPanel p){
        g.setColor(Color.BLACK);
        g.fillRect(x,y,100,300);
        g.setColor(new Color(38, 150, 10));
        g.fillRect(x+4,y+4,100-8,300-8);

        ((Graphics2D)g).rotate(Math.toRadians(-90),x,y);
        int taille;
        int poscroix;
            for (int i = 0; i < grille.getBateaux().size(); i++) {
                if(i == 0){
                    taille = 2;
                }else if( i == 1 || i ==2){
                    taille = 3;
                }else{
                    taille = i +1;
                }

                if(isFlippedRight){
                    g.drawImage(SpriteIndex.imagesBateaux[i],x - (50*(i+1)),y + 95-(18*taille),18,18*taille,p);
                    poscroix = y +  95 - ((18*taille)/2);
                }else{
                    g.drawImage(SpriteIndex.imagesBateaux[i],x - (50*(i+1)),y+5,18,18*taille,p);
                    poscroix = y+((18*taille)/2);
                }
                if(!grille.getBateau(i).getEnVie()){
                    g.drawImage(SpriteIndex.croixImage,x - (50*(i+1)),poscroix-15,30,30,p);
                }
            }

        ((Graphics2D)g).rotate(Math.toRadians(90),x,y);


    }
}
