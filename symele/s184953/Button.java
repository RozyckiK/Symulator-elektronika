package symele.s184953;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Button implements MouseListener, MouseMotionListener {

    private Game game;
    private Handler handler;
    private ResLoader loader;

    public Button(Handler handler,Game game, ResLoader loader, int x, int y, int width, int height){
        this.handler = handler;
        this.game = game;
        this.loader = loader;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    private enum BUTTONSTATUS{
        Normal,
        Hover,
        Click
    }

    protected int x, y, width, height;

    public boolean buttonAction = false;

    public void setBackActionButton(){
        buttonAction = false;
    }

    BufferedImage buttonImage;

    BUTTONSTATUS buttonState = BUTTONSTATUS.Normal;

    public BufferedImage getButtonImage() {
        return buttonImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.mouseOver(mx, my, x, y, width, height)){
            buttonState = BUTTONSTATUS.Click;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.mouseOver(mx, my, x, y, width, height)){
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

    public void render(Graphics g){
        g.drawImage(buttonImage, x, y, width, height, null);
    }
}
