package game.scenes;

import java.awt.*;
import java.io.IOException;

public class PoliceIndex {
    public static Font bitcrusher;
    public static Font autoradio;

    public static void loadFonts() throws IOException, FontFormatException {
        bitcrusher = Font.createFont(Font.TRUETYPE_FONT,PoliceIndex.class.getResourceAsStream("/polices/bitcrusher.ttf"));
        autoradio = Font.createFont(Font.TRUETYPE_FONT,PoliceIndex.class.getResourceAsStream("/polices/autoradio.ttf"));
    }
}
