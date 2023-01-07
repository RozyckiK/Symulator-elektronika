package symele.s184953;

import java.awt.*;

/**
 * Klasa odpowiedzialna za wyświetlanie finałowego ekranu
 */
public class GameFinalResult {
    /**
     * Deklaracja instancji gry
     */
    private Game game;
    /**
     * Deklaracja instancji handlera
     */
    private Handler handler;
    /**
     * Deklaracja instancji loadera
     */
    private ResLoader loader;
    /**
     * Deklaracja instancji hudu
     */
    private Hud hud;

    /**
     * Deklaracja stringa z wynikiem rundy
     */
    private String resultString;

    /**
     * Deklaracja przycisku
     */
    private Button menuButton;

    /**
     * Konstruktor klasy
     * @param game przekaznie gry
     * @param handler przekazania handlera
     * @param loader przkazanie loadera
     * @param hud przekazanie hudu
     */
    public GameFinalResult(Game game, Handler handler, ResLoader loader, Hud hud){
        this.game = game;
        this.handler = handler;
        this.loader = loader;
        this.hud = hud;

        menuButton = new Button(handler, game, loader,Game.WIDTH*1/2 - 150,Game.HEIGHT - 175,300,130);
        game.addMouseListener(menuButton);
        game.addMouseMotionListener(menuButton);
    }

    /**
     * Powrót do menu i ustawienie poziomu spowrotem do 1
     */
    public void tick() {
        menuButton.tick();

        if(menuButton.buttonAction){
            menuButton.setBackActionButton();
            game.gameState = Game.STATE.Menu;
            hud.setLevel(1);
        }
    }

    /**
     * Renderowanie przycisków i napisów na ekranie
     * @param g przekazanie grafiki
     */
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
