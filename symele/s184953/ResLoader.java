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


    public void loadAllAssets(){
        getMenuButtonImage();
        getBackgroundImage();
        getFont();
    }
    private void getMenuButtonImage() {
        try {
            menuButtonNormal = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/button.png"));
            menuButtonHover = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonhover.png"));
            menuButtonClick = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonpress.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getBackgroundImage(){
        try {
            backGround = ImageIO.read(getClass().getResourceAsStream("res/backGround/backGround.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFont(){
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
