package game.engine.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BouttonImage extends Boutton {

    private BufferedImage m_Img;
    private BufferedImage m_disabledImage;

    public BouttonImage(int x , int y , int w , int h, String imagePath , String disImagePath) throws IOException {
        this.m_xPos = x;
        this.m_yPos = y;
        this.m_width = w;
        this.m_height = h;
        m_Img = ImageIO.read(getClass().getResourceAsStream(imagePath));
        m_disabledImage = ImageIO.read(getClass().getResourceAsStream(disImagePath));
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g, JPanel p) {

        if(m_isEnabled){
            g.drawImage(m_Img,m_xPos,m_yPos,m_width,m_height,p);
        }else{
            g.drawImage(m_disabledImage,m_xPos,m_yPos,m_width,m_height,p);
        }
    }

    @Override
    public void action() throws IOException, FontFormatException {

    }
}
