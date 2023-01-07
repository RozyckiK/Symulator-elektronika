package symele.s184953;

import java.awt.*;

/**
 * Klasa odpowiedzialna za hud w górnej częsci ekranu podczas gry
 * @author Kacper Różycki
 */
public class Hud{
    /**
     * Dekalracaja instancji handlera
     */
    private Handler handler;
    /**
     * Deklaracja instancji gry
     */
    private Game game;
    /**
     * Deklaracja instancji loadera
     */
    private ResLoader loader;
    /**
     * Deklaracaja przycisków znajdujących się na hud
     */
    private Button menuButton, addTemperature, subtractTemperature, levelChanger;

    /**
     * Deklaracja napisu na przycisku do zmiany poziomów
     */
    private String levelChangerString;

    /**
     * Enum ze stanami spoiwa
     */
    public enum SOLDERSTATE{
        start,
        better,
        perfect,
        burned
    }

    /**
     * Lista stanów spoiwa dla różnych poziomów
     */
    public SOLDERSTATE[] levelResult = {SOLDERSTATE.start, SOLDERSTATE.start, SOLDERSTATE.start, SOLDERSTATE.start, SOLDERSTATE.start};

    /**
     * Deklaracja początkowej temperatury i poziomu
     */
    private int temperature = 320, level = 1;

    /**
     * Konstruktor hud
     * @param handler przekazuje handler
     * @param game przekazuje gre
     * @param loader przekazuje loader
     */
    public Hud(Handler handler, Game game, ResLoader loader){
        this.handler = handler;
        this.game = game;
        this.loader = loader;

        menuButton = new Button(handler, game, loader,Game.WIDTH-130, 10, 125, 50);
        game.addMouseMotionListener(menuButton);
        game.addMouseListener(menuButton);

        addTemperature = new Button(handler, game, loader, 10, 10, 50,50);
        game.addMouseMotionListener(addTemperature);
        game.addMouseListener(addTemperature);

        subtractTemperature = new Button(handler, game, loader, 70, 10, 50,50);
        game.addMouseMotionListener(subtractTemperature);
        game.addMouseListener(subtractTemperature);

        levelChanger = new Button(handler, game, loader, Game.WIDTH-310, 10, 125, 50);
        game.addMouseMotionListener(levelChanger);
        game.addMouseListener(levelChanger);
    }

    /**
     * Getter temperatury
     * @return zwraca aktualną temperature
     */
    public int getTemperature(){
        return temperature;
    }

    /**
     * Metoda odświeżająca wszystkie przyciski w hud
     */
    private void buttons(){
        menuButton.tick();
        addTemperature.tick();
        subtractTemperature.tick();
        levelChanger.tick();

        if(menuButton.buttonAction){
            menuButton.setBackActionButton();
            game.gameState = Game.STATE.Menu;
        }

        if(addTemperature.buttonAction){
            addTemperature.setBackActionButton();
            temperature += 5;
        }

        if(subtractTemperature.buttonAction){
            subtractTemperature.setBackActionButton();
            temperature -= 5;
        }

        if(levelChanger.buttonAction){
            levelChanger.setBackActionButton();


            level += 1;



            handler.clearAllObjects();
            handler.addObject(new Solder(378, 552, ID.Solder,128,48, loader, handler, this));
            handler.addObject(new SolderingIron(200, 100, ID.SolderingIron, 2*48,2*128,loader.solderingIron));
        }
    }

    /**
     *Metoda odpowiedzialna za odświeżanie
     */
    public void tick(){
        buttons();

        temperature = Game.clamp(temperature,300,400);

        if(level < 3){
            levelChangerString = "Kolejny";
        }else if(level < 4){
            levelChangerString = "Koniec";
        }else{
            game.gameState = Game.STATE.GameFinalResult;
        }
    }

    /**
     * Metoda odpowiedzialna za renderowanie hud
     * @param g przekazanie grafiki
     */
    public void render(Graphics g){
        Font currentFont = loader.chakraPetchBold;
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 30F);
        g.setFont(newFont);


        g.setColor(new Color(60, 63, 65));
        g.fillRect(0, 0, game.WIDTH, 69);

        g.setColor(Color.white);
        menuButton.render(g);
        addTemperature.render(g);
        subtractTemperature.render(g);
        levelChanger.render(g);
        g.drawLine(0,70, Game.WIDTH, 70);
        g.drawString("Menu",Game.WIDTH-105,45);
        g.drawString("Temperatura:",140,45);
        g.drawString("+",27,41);
        g.drawString("-",89,41);
        g.drawString(Integer.toString(temperature), 340,45);
        g.drawString("Poziom:",Game.WIDTH-455,45);
        g.drawString(Integer.toString(level),Game.WIDTH-337,45);


        newFont = currentFont.deriveFont(currentFont.getSize() * 28F);
        g.setFont(newFont);
        g.drawString(levelChangerString,Game.WIDTH-299,45);
    }

    /**
     * Getter Level
     * @return zwraca aktualny poziom
     */
    public int getLevel(){
        return level;
    }

    /**
     * Setter Level
     * @param x ustawia dany poziom
     */
    public void setLevel(int x){
        level = x;
    }

    /**
     * Setter levelResult, ustawia stan spoiwa dla danego poziomu
     * @param x przekazuje aktualny poziom
     * @param solderstate przekazuje stan spoiwa
     */
    public void setLevelResult(int x, SOLDERSTATE solderstate){
        levelResult[x] = solderstate;
    }

    /**
     * Getter levelResult, zwraca stan spoiwa dla danego poziomu
     * @param x przekazuje dany poziom
     * @return zwraca stan spoiwa
     */
    public SOLDERSTATE getLevelResult(int x){
        return levelResult[x];
    }
}
