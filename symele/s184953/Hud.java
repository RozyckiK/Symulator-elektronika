package symele.s184953;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hud extends MouseAdapter {

    private Handler handler;
    private Game game;

    public Hud(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e){
        if(game.gameState == Game.STATE.Game1) {

            int mx = e.getX();
            int my = e.getY();

            if(game.mouseOver(mx, my, Game.WIDTH-130, 10, 125, 50)){
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        Font fnt = new Font("arial", 1 ,30);
        g.setFont(fnt);
        g.setColor(Color.white);

        g.drawRect(Game.WIDTH-130, 10, 125, 50);
        g.drawLine(0,70, Game.WIDTH, 70);
        g.drawString("Menu",Game.WIDTH-105,45);
    }
}
