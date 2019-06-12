package game.engine.ui;

import game.Objects.SoundLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public abstract class Boutton {

    protected int m_xPos;
    protected int m_yPos;
    protected int m_width;
    protected int m_height;
    protected String m_text;
    protected Font m_police;
    protected boolean m_isEnabled;
    protected boolean m_mousein;

    public void checkMouse(MouseEvent e ,String eventType){
        if(!m_isEnabled){
            return;
        }
        if(e.getX() < m_xPos || e.getY() < m_yPos || e.getX() > (m_xPos + m_width) || e.getY() > (m_yPos + m_height)){
            m_mousein = false;
            return;
        }
        m_mousein = true;

        if(eventType == "mP"){
            try {
                SoundLibrary.playClickSound();
                action();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (FontFormatException e1) {
                e1.printStackTrace();
            }
        }
    }
    public abstract void update();
    public abstract void draw(Graphics g, JPanel p);
    public abstract void action() throws IOException, FontFormatException;

    public void setXpos(int pos){
        m_xPos = pos;
    }

    public  void setYpos(int pos){
        m_yPos = pos;
    }

    public boolean isEnabled(){
        return m_isEnabled;
    }

    public void setEnabled(boolean state){
        m_isEnabled = state;
    }

}
