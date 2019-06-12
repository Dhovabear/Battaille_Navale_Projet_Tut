package game.engine.ui;

import game.Objects.SoundLibrary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FlagSelector {

    private ArrayList<BufferedImage> m_flags;
    private int m_nbrOfFlags = 20;

    private int m_posx;
    private int m_posy;

    private int m_flagSize;
    private int m_width;
    private int m_height;

    private int m_selectedFlag;

    private String[] m_flagsName = {"Inde","Russie","Allemagne","Italie","Belgique","France","Angleterre","Etats-Unis","Canada","Chine","IUT-Info","Dovabear","Japon","Brésil","Portugal","Espagne","Xanix","r2r0","RaptorRouge","Nijtus"};

    public FlagSelector(int x , int y , int flagSize) throws IOException {
        m_flags = new ArrayList<BufferedImage>();
        for(int i = 0; i < m_nbrOfFlags ; i++){
            m_flags.add(ImageIO.read(getClass().getResourceAsStream("/images/drapeaux/drapeau_"+i+".png")));
        }

        this.m_posx = x;
        this.m_posy = y;
        this.m_flagSize = flagSize;

        this.m_width = ((flagSize+5)*10)+20;
        this.m_height = (((flagSize/2)+5)*2)+20;

        m_selectedFlag = -1;
    }

    public void draw(Graphics g, JPanel p){

        g.setColor(Color.BLACK);
        g.fillRect(m_posx,m_posy,m_width,m_height);
        g.setColor(new Color(184, 197, 192));
        g.fillRect(3+m_posx,3+m_posy,m_width-6,m_height-6);
        for (int i = 0 ; i < 2;i++){
            for (int j = 0; j < 10;j++){
                if((i*10)+j >= m_nbrOfFlags){
                    break;
                }
                g.drawImage(m_flags.get((i*10)+j),10+m_posx+(j*(m_flagSize + 5)),10+m_posy+(i*((m_flagSize/2)+5)),m_flagSize,m_flagSize/2,p);
            }
        }
    }


    public void checkMouse(MouseEvent e , String inputType){

        if(!inputType.equals("mP")){
            return;
        }
        if(e.getX() < m_posx || e.getX() > m_posx + m_width || e.getY() < m_posy || e.getY() > m_posy+m_height){
            return;
        }
        int flag = (int) ((Math.floor((e.getX()-m_posx)/105)) + (Math.floor((e.getY()-m_posy)/55)*10));
        if(flag >= m_flags.size()){
            return;
        }
        SoundLibrary.playClickSound();
        System.out.println(flag);
        m_selectedFlag = flag;
    }

    public BufferedImage getFlag(int i) {
        return m_flags.get(i);
    }

    public String getNameOfFlag(int i){
        return m_flagsName[i];
    }

    public BufferedImage getSelectedFlag(){
        if(m_selectedFlag == -1){
            return null;
        }
        return m_flags.get(m_selectedFlag);
    }

    public String getNameOfSelectedFlag(){
        return m_flagsName[m_selectedFlag];
    }

    public void deselect() {
        m_selectedFlag = -1;
    }

    public void chooseRandomFlag(){
        Random rng = new Random(System.currentTimeMillis());
        m_selectedFlag = rng.nextInt(20);
    }

}
