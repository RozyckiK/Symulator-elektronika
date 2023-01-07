package symele.s184953;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Klasa odpowiadająca za przyciski w grze
 * @author Kacper Różycki
 */
public class Button implements MouseListener, MouseMotionListener {

    /**
     * Deklaracaja instancji gry
     */
    private Game game;
    /**
     * Deklaracja instancji handlera
     */
    private Handler handler;
    /**
     * Deklaracja instancji loadera zasobów
     */
    private ResLoader loader;

    protected int x, y, width, height;
    /**
     * Konstruktor przycisku w grze
     * @param handler przesłanie handlera
     * @param game przesłanie gry
     * @param loader przesłanie loadera
     * @param x pozycja "x" przycisku
     * @param y pozycja "y" przycisku
     * @param width szerokość przycisku
     * @param height wysokość przycsiku
     */
    public Button(Handler handler,Game game, ResLoader loader, int x, int y, int width, int height){
        this.handler = handler;
        this.game = game;
        this.loader = loader;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Enum z różnymi statusami przycisku
     */
    private enum BUTTONSTATUS{
        Normal,
        Hover,
        Click
    }


    /**
     *Sprawdzanie akcji przycisku
     */
    public boolean buttonAction = false;

    /**
     *przywracanie spowrotem możliwości akcji przycisku
     */
    public void setBackActionButton(){
        buttonAction = false;
    }

    /**
     * aktualna grafika przycisku
     */
    BufferedImage buttonImage;

    /**
     * aktualny status przycisku
     */
    BUTTONSTATUS buttonState = BUTTONSTATUS.Normal;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Metoda sprawdzająca klikanie w obszarze przyciku
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.mouseOver(mx, my, x, y, width, height)){
            buttonState = BUTTONSTATUS.Click;
        }
    }

    /**
     * Metoda wywołująca akcje w momencie puszczania przycisku myszki
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.mouseOver(mx, my, x, y, width, height)&& buttonState == BUTTONSTATUS.Click){
            buttonAction = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Sprawdzanie czy myszka jest nad przyciskiem
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if (game.mouseOver(mx, my, x, y, width, height)) {
            buttonState = BUTTONSTATUS.Hover;
        } else {
            buttonState = BUTTONSTATUS.Normal;
        }
    }

    /**
     * Metoda  przypisująca odpowiednią grafikę przycisku w zależności od jego aktualnego statusu
     */
    public void tick(){
        if(buttonState == BUTTONSTATUS.Normal){
            buttonImage = loader.menuButtonNormal;
        }
        else if (buttonState == BUTTONSTATUS.Hover){
            buttonImage = loader.menuButtonHover;
        }
        else if(buttonState == BUTTONSTATUS.Click){
            buttonImage = loader.menuButtonClick;
        }
        else{
            buttonImage = loader.menuButtonNormal;
        }
    }

    /**
     * Metoda renderująca grafikę przycisku
     * @param g przekazanie grafiki
     */
    public void render(Graphics g){
        g.drawImage(buttonImage, x, y, width, height, null);
    }
}
