package game.engine.ui;

import game.engine.Game;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplashText {
    private static List<String> m_splashTexts = new ArrayList<String>();
    private static Random rng = new Random();
    private String m_currentText;
    private float m_zoomLevel;
    private int m_posX;
    private int m_posY;
    private int m_rotation;
    private Font m_fnt;
    private final int size = 18;


    public static void LoadSplash() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(Game.class.getResourceAsStream("/donnes/splash.txt")));
        String tmp;
        while ((tmp = rd.readLine()) !=null){
            m_splashTexts.add(tmp);
        }
    }

    public static String getRandomSplash(){
        return m_splashTexts.get(rng.nextInt(m_splashTexts.size()));
    }

    public SplashText(int posX, int posY) throws IOException, FontFormatException {
        this.m_posX = posX;
        this.m_posY = posY;
        this.m_currentText = getRandomSplash();
        System.out.println(m_currentText);
        System.out.println(m_posX + " " + m_posY);
        this.m_fnt = Font.createFont(Font.TRUETYPE_FONT,Game.class.getResourceAsStream("/polices/autoradio.ttf"));
        this.m_fnt = m_fnt.deriveFont((float) size);

    }

    public void draw(Graphics g){
        g.setColor(new Color(0, 0, 0));
        g.fillRect(m_posX,m_posY,354,64);
        g.setColor(new Color(184, 197, 192));
        g.fillRect(m_posX+2,m_posY+2,350,60);
        g.setColor(new Color(0, 0, 0));
        g.setFont(m_fnt.deriveFont(Font.BOLD).deriveFont(Font.HANGING_BASELINE));
        g.drawString("ASTUCE:",m_posX+10,m_posY+30);
        g.setFont(m_fnt);
        g.drawString(m_currentText,m_posX+10,m_posY+50);
    }

    public void setM_posX(int m_posX) {
        this.m_posX = m_posX;
    }

    public void setM_posY(int m_posY) {
        this.m_posY = m_posY;
    }
}
