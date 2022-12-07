package symele.s184953;

import java.awt.*;

public class Hud{
    private Handler handler;
    private Game game;
    private ResLoader loader;
    private Button menuButton;

    public Hud(Handler handler, Game game, ResLoader loader){
        this.handler = handler;
        this.game = game;
        this.loader = loader;

        menuButton = new Button(handler, game, loader,Game.WIDTH-130, 10, 125, 50);
        game.addMouseMotionListener(menuButton);
        game.addMouseListener(menuButton);
    }

    public void tick(){
        menuButton.tick();

        if(menuButton.buttonAction){
            menuButton.setBackActionButton();
            game.gameState = Game.STATE.Menu;
        }
    }

    public void render(Graphics g){
        Font currentFont = loader.chakraPetchBold;
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 30F);
        g.setFont(newFont);


        g.setColor(new Color(60, 63, 65));
        g.fillRect(0, 0, game.WIDTH, 69);

        g.setColor(Color.white);
        menuButton.render(g);
        g.drawLine(0,70, Game.WIDTH, 70);
        g.drawString("Menu",Game.WIDTH-105,45);


    }


}
