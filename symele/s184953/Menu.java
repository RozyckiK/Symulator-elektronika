package symele.s184953;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(mouseOver(mx, my, Game.WIDTH*2/6-170, 180, 300, 150)){
            game.gameState = Game.STATE.Game1;
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

        g.drawRect(Game.WIDTH*2/6-170, 180, 300, 150);
        g.drawString("Graj",Game.WIDTH*2/6-70,270);

        g.drawRect(Game.WIDTH*4/6-130, 180, 300, 150);
        g.drawString("Lorem",Game.WIDTH*4/6-70,270);

        g.drawRect(Game.WIDTH*2/6-170, 380, 300, 150);
        g.drawString("Lorem",Game.WIDTH*2/6-100, 480);

        g.drawRect(Game.WIDTH*4/6-130, 380, 300, 150);
        g.drawString("Wyjdź",Game.WIDTH*4/6-60,480);


    }
}
