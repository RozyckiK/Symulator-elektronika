package symele.s184953;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResLoader {

    private Handler handler;

    public BufferedImage menuButtonNormal, menuButtonHover, menuButtonClick;
    public BufferedImage backGround;
    public Font chakraPetch, chakraPetchBold;

    public ResLoader(Handler handler){
        this.handler = handler;
    }


    public void getMenuButtonImage() {
        try {
            //menuButtonNormal = ImageIO.read(getClass().getResourceAsStream("res/StandartButtonV1/StandartButtonNormal/StandartButtonNormal2.png"));
            //menuButtonHover = ImageIO.read(getClass().getResourceAsStream("res/StandartButtonV1/StandartButtonHover/StandartButtonHover2.png"));
            //menuButtonClick = ImageIO.read(getClass().getResourceAsStream("res/StandartButtonV1/StandartButtonActive/StandartButtonActive2.png"));

            menuButtonNormal = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/button4.png"));
            menuButtonHover = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonhover4.png"));
            menuButtonClick = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonpress4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBackgroundImage(){
        try {
            backGround = ImageIO.read(getClass().getResourceAsStream("res/backGround/backGround.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFont(){
        try {
            InputStream is = getClass().getResourceAsStream("res/font/ChakraPetch-Regular.ttf");
            chakraPetch = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("res/font/ChakraPetch-Bold.ttf");
            chakraPetchBold = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
