package symele.s184953;

import java.awt.*;

/**
 * Klasa odpowiedzialna za wyświetlanie menu
 * @author Kacper Różycki
 */
public class Menu{

    /**
     * Deklaracaj instancji gry
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
     * Deklaracja przycisku play
     */
    private Button playButton;
    /**
     * Deklaracja przycisku exit
     */
    private Button exitButton;

    /**
     * Konstruktor klasy Menu
     * @param game przekazuje gre
     * @param handler przekazuje handlera
     * @param loader przekazuje loadera
     */
    public Menu(Game game, Handler handler, ResLoader loader){
        this.game = game;
        this.handler = handler;
        this.loader = loader;

        playButton = new Button(handler, game, loader,Game.WIDTH*1/2 - 150,200,300,130);
        game.addMouseListener(playButton);
        game.addMouseMotionListener(playButton);

        exitButton = new Button(handler, game, loader,Game.WIDTH*1/2 - 150,350,300, 130);
        game.addMouseListener(exitButton);
        game.addMouseMotionListener(exitButton);
    }


    /**
     * Metoda ta odświeża działanie przycisków w menu
     */
    public void tick(){
        playButton.tick();
        exitButton.tick();

        if(playButton.buttonAction){
            playButton.setBackActionButton();
            game.gameState = Game.STATE.Game;
        }

        if(exitButton.buttonAction){
            exitButton.setBackActionButton();
            System.exit(1);
        }
    }

    /**
     * Metoda odpowiedzialna za renderowanie w menu
     * @param g przekazuje grafike
     */
    public void render(Graphics g){

        //font setup for MENU
        Font currentFont = loader.chakraPetchBold;
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 150F);
        g.setFont(newFont);
        g.setColor(Color.white);

        g.drawString("Menu",Game.WIDTH/2-200,140);

        //rendering buttons
        playButton.render(g);
        exitButton.render(g);


        //font size setup for buttons
        newFont = currentFont.deriveFont(currentFont.getSize() * 50F);
        g.setFont(newFont);
        g.setColor(new Color(64, 68, 70 ));

        g.drawString("Graj",Game.WIDTH*1/2-70,280);
        g.drawString("Wyjdź",Game.WIDTH*1/2-80,430);
    }
}
