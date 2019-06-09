package game.engine.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TextField {
    private int m_posx;
    private int m_posy;
    private int m_taille;
    private int m_width;
    private String m_contenu;
    private String m_placeHolder;
    private int m_cursorPlace;
    private int m_maxChar;
    private Font m_police;
    private boolean m_hadFocus;

    private int m_blinkCompt;
    private boolean m_isCursorVisible;

    private int[] m_excludedKeys = {8,16,37,38,39,40,10,17,18,19,20,112,113,114,115,116,117,118,119,120,121,122,123,27,222};

    public TextField(int x , int y, int taille , String placeHolder, int maxChar) throws IOException, FontFormatException {
        this.m_posx = x;
        this.m_posy = y;
        this.m_taille = taille;
        this.m_placeHolder = placeHolder;
        this.m_maxChar = maxChar;
        this.m_cursorPlace = 0;
        this.m_contenu = "";
        this.m_police = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/polices/autoradio.ttf"));
        this.m_police = m_police.deriveFont((float)m_taille);
        this.m_hadFocus = false;
        this.m_width = (m_taille*m_maxChar)-(5*m_taille);
        this.m_isCursorVisible = true;
    }

    public void draw(Graphics g, JPanel p){
        g.setColor(Color.BLACK);
        g.fillRect(m_posx,m_posy,m_width+6,m_taille+6);
        g.setColor(Color.WHITE);
        g.fillRect(m_posx+3,m_posy+3,m_width,m_taille);
        g.setFont(m_police);

        System.out.println(m_cursorPlace);
        if(m_hadFocus){
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics(m_police);
            int larg = 0;
            if(m_cursorPlace -1 >= 0 ){
                larg =  fm.stringWidth(m_contenu.substring(0,m_cursorPlace));
            }

            m_blinkCompt ++;
            if(m_blinkCompt >= 30){
                m_blinkCompt = 0;
                m_isCursorVisible = !m_isCursorVisible;
            }
            if(m_isCursorVisible)
            {
                g.fillRect(m_posx+larg+5,m_posy+5,1,m_taille-5);
            }
        }else{
            if(m_contenu.isEmpty()){
                g.setColor(Color.GRAY);
                g.drawString(m_placeHolder,m_posx+4,m_posy+m_taille);
            }
        }

        g.setColor(Color.BLACK);
        g.drawString(m_contenu,m_posx+4,m_posy+m_taille);
    }

    public void checkMouse(MouseEvent e, String inputType){
        if(!inputType.equals("mP")){
            return;
        }
        if(e.getX() < m_posx || e.getX() > m_posx+m_width || e.getY() < m_posy || e.getY() > m_posy+m_taille){
            m_hadFocus = false;
        }else{
            m_hadFocus = true;
        }
    }

    public void checkKeys(KeyEvent e , String inputType){

        if(!m_hadFocus){
            return;
        }

        if(!inputType.equals("kp")){
            return;
        }



        if(e.getKeyCode() == 8 && m_contenu.length() > 0 && m_cursorPlace > 0){
            m_contenu = charRemoveAt(m_contenu,m_cursorPlace-1);
            m_cursorPlace--;
            return;
        }

        if(e.getKeyCode() == 37 && m_cursorPlace > 0){
            m_cursorPlace--;
        }

        if(e.getKeyCode() == 39 && m_cursorPlace < m_contenu.length()){
            m_cursorPlace++;
        }


        if(e.getKeyCode() == 10){
            m_hadFocus = false;
            return;
        }

        //Touches exclues
        for (int k: m_excludedKeys) {
            if(e.getKeyCode() == k){
                return;
            }
        }

        if(m_contenu.length()<m_maxChar){
            m_contenu = charAddAt(m_contenu ,m_cursorPlace, e.getKeyChar());
            m_cursorPlace ++;
        }


    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }

    public static String charAddAt(String str, int p,char c) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(p,c);
        return sb.toString();
    }

    public String getText() {
        return m_contenu;
    }
}
