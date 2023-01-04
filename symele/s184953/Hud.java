package symele.s184953;

import java.awt.*;

public class Hud{
    private Handler handler;
    private Game game;
    private ResLoader loader;
    private Button menuButton, addTemperature, subtractTemperature, levelChanger;

    private String levelChangerString;

    private int temperature = 320, level = 1;

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

    public int getTemperature(){
        return temperature;
    }

    private void buttons(){
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
        }
    }

    public void tick(){
        menuButton.tick();
        addTemperature.tick();
        subtractTemperature.tick();
        levelChanger.tick();

        buttons();


        temperature = Game.clamp(temperature,300,400);

        if(level < 3){
            levelChangerString = "Kolejny";
        }else if(level < 4){
            levelChangerString = "Koniec";
        }else{
            game.gameState = Game.STATE.Game1FinalResult;
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
}
