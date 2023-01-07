package symele.s184953;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Klasa odpowiadjąca za ładowanie zasobów gry
 * @author Kacper Różycki
 */
public class ResLoader {

    /**
     * Deklaracja instancji handlera
     */
    private Handler handler;

    /**
     * Deklaracja obrazów dla przycisku
     */
    public BufferedImage menuButtonNormal, menuButtonHover, menuButtonClick;

    /**
     * Deklaracja obrazów dla tła
     */
    public BufferedImage backGroundMenu, backGroundGame;

    /**
     * Deklarcja obrazu lutownicy
     */
    public BufferedImage solderingIron;

    /**
     * Deklaracja obrazów spoiwa
     */
    public BufferedImage solderStart, solderBetter, solderPerfect, solderBurned;

    /**
     * Deklaracja czionek
     */
    public Font chakraPetch, chakraPetchBold;

    /**
     * Konstruktor klasy ResLoader
     * @param handler przekazuje handler
     */
    public ResLoader(Handler handler){
        this.handler = handler;
    }

    /**
     * <p>Metoda wywołująca inne metody odpowiedzialne za ładowanie obrazów</p>
     * <p>Rozbie na kilka metod w celu uporządkowania kodu</p>
     */
    public void loadAllAssets(){
        getMenuButtonImage();
        getBackgroundImage();
        getFont();
        getGameObjectImage();
    }

    /**
     * Metoda odpowiedzilna za ładowanie obrazów przycisku
     */
    private void getMenuButtonImage() {
        try {
            menuButtonNormal = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/button.png"));
            menuButtonHover = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonhover.png"));
            menuButtonClick = ImageIO.read(getClass().getResourceAsStream("res/StandardButton/buttonpress.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzialna za ładowanie obrazów tła
     */
    private void getBackgroundImage(){
        try {
            backGroundMenu = ImageIO.read(getClass().getResourceAsStream("res/backGround/backGround.png"));
            backGroundGame = ImageIO.read((getClass().getResourceAsStream("res/backGround/backGroundGame.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzialna za ładowanie czionki
     */
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

    /**
     * Metoda odpowiedzialna za ładowanie obrazów obiektów
     */
    private void getGameObjectImage(){
        try {
            solderingIron = ImageIO.read(getClass().getResourceAsStream("res/gameObject/solderingIron/solderingIron.png"));

            solderStart = ImageIO.read(getClass().getResourceAsStream("res/gameObject/solder/solderStart.png"));
            solderBetter = ImageIO.read(getClass().getResourceAsStream("res/gameObject/solder/solderBetter.png"));
            solderPerfect = ImageIO.read(getClass().getResourceAsStream("res/gameObject/solder/solderPerfect.png"));
            solderBurned = ImageIO.read(getClass().getResourceAsStream("res/gameObject/solder/solderBurned.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
