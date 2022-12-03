package symele.s184953;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    private BufferedImage gameButton;
    private String direction;

    public Menu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        if(game.gameState == Game.STATE.Menu) {

            int mx = e.getX();
            int my = e.getY();

            if(mouseOver(mx, my, Game.WIDTH*1/2-150, 180, 300, 150)){
                game.gameState = Game.STATE.Game1;
            }

            if(mouseOver(mx, my, Game.WIDTH*1/2-150, 380, 300, 150)) {
                System.exit(1);
            }
        }
    }


    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        Font fnt = new Font("arial", 1 ,50);
        g.setFont(fnt);
        g.setColor(Color.white);

        g.drawString("Menu",Game.WIDTH/2-70,120);

        g.drawRect(Game.WIDTH*1/2-150, 180, 300, 150);
        g.drawString("Graj",Game.WIDTH*1/2-70,270);


        g.drawRect(Game.WIDTH*1/2-150, 380, 300, 150);
        g.drawString("Wyjdź",Game.WIDTH*1/2-60,480);
    }
}
