package game.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class SpriteIndex {
    public static BufferedImage[] waterTiles;
    public static BufferedImage[] imagesBateaux;
    public static BufferedImage[] imagesFlags;
    public static BufferedImage[] viseurSprites;

    public static BufferedImage normalIleTile;
    public static BufferedImage radarWaterTile;
    public static BufferedImage radarIleTile;
    public static BufferedImage vsPannel;

    public static BufferedImage woodPancart;
    public static BufferedImage croixImage;

    public static BufferedImage pionRouge;
    public static BufferedImage pionBlanc;

    private static final int NBR_OF_FLAGS = 20;

    public static void loadImages() throws IOException {

        waterTiles = new BufferedImage[]{
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_01.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_02.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_03.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_04.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_05.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_06.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_07.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_08.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_09.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_10.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_11.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_12.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_13.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_14.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_15.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_16.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_17.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_18.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_19.png")),
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/animatedWater/sprite_20.png"))
        };

        imagesBateaux = new BufferedImage[]{
          ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/batDeuxCase.png")),
          ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/batTroisCase.png")),
          ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/sousousmarin.png")),
          ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/batQuatreCase.png")),
          ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/batCinqCase.png"))
        };


       viseurSprites = new BufferedImage[]{
                ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_0.png")),
               ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_1.png")),
               ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_2.png")),
               ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_3.png")),
               ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_4.png")),
               ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/fireCursor/sprite_5.png"))
       };

        normalIleTile = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/tileSable.png"));
        radarWaterTile = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/sprite_1.png"));
        radarIleTile = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/Tiles/sprite_0.png"));


        imagesFlags = new BufferedImage[NBR_OF_FLAGS];
        for(int i = 0; i < NBR_OF_FLAGS; i++){
            imagesFlags[i] = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/drapeaux/drapeau_"+i+".png"));
        }
        
        woodPancart = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/paneauVs.png"));
        vsPannel = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/vs.png"));
        croixImage = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/croixRouge.png"));
        pionRouge = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/pionRouge.png"));
        pionBlanc = ImageIO.read(SpriteIndex.class.getResourceAsStream("/images/pionBlanc.png"));

    }
}
