package symele.s184953;

import java.awt.*;

public class Game1FinalResult {
    private Game game;
    private Handler handler;
    private ResLoader loader;
    private Hud hud;

    private String resultString;

    private Button menuButton;

    public Game1FinalResult(Game game, Handler handler, ResLoader loader, Hud hud){
        this.game = game;
        this.handler = handler;
        this.loader = loader;
        this.hud = hud;

        menuButton = new Button(handler, game, loader,Game.WIDTH*1/2 - 150,Game.HEIGHT - 175,300,130);
        game.addMouseListener(menuButton);
        game.addMouseMotionListener(menuButton);
    }

    public void tick() {
        menuButton.tick();

        if(menuButton.buttonAction){
            menuButton.setBackActionButton();
            game.gameState = Game.STATE.Menu;
            hud.setLevel(1);
        }
    }

    public void render(Graphics g){
        Font currentFont = loader.chakraPetchBold;
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 60F);
        g.setFont(newFont);
        g.setColor(Color.white);

        g.drawString("Poziom 1:",40,140);
        g.drawString("Poziom 2:",40,240);
        g.drawString("Poziom 3:",40,340);

        if(Hud.SOLDERSTATE.perfect == hud.getLevelResult(1)){
            resultString = "Dobrze!";
        }else{
            resultString = "No nie do końca";
        }
        g.drawString(resultString,400,140);

        if(Hud.SOLDERSTATE.perfect == hud.getLevelResult(2)){
            resultString = "Dobrze!";
        }else{
            resultString = "No nie do końca";
        }
        g.drawString(resultString,400,240);

        if(Hud.SOLDERSTATE.perfect == hud.getLevelResult(3)){
            resultString = "Dobrze!";
        }else{
            resultString = "No nie do końca";
        }
        g.drawString(resultString,400,340);

        menuButton.render(g);
        g.drawString("Menu",Game.WIDTH*1/2 - 75,Game.HEIGHT - 95);
    }
}
