package symele.s184953;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Menu menu;
    private Spawner spawner;
    private Hud hud;
    private ResLoader loader;
    private Game1FinalResult game1FinalResult;

    public enum STATE {
        Menu,
        Game,
        GameFinalResult,
    }

    public STATE gameState = STATE.Menu;

    public Game(){
        //------------------------------------------------
        handler = new Handler();
        loader = new ResLoader(handler);
        menu = new Menu(this, handler, loader);
        hud = new Hud(handler, this, loader);
        spawner = new Spawner(handler, this, loader, hud);
        game1FinalResult = new Game1FinalResult(this,handler,loader,hud);
        Dragger dragger = new Dragger(handler);
        //------------------------------------------------
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(dragger);
        this.addMouseMotionListener(dragger);
        //-----------------------------------------------
        loader.loadAllAssets();
        new Window(WIDTH, HEIGHT, "Symulator elektronika", this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;


                if (running) {
                    render();
                }
                frames++;
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        spawner.tick();

        if(gameState == STATE.Menu){
            menu.tick();
        }

        if(gameState == STATE.Game){
            hud.tick();
        }

        if(gameState == STATE.GameFinalResult){
            game1FinalResult.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();


        if(gameState == STATE.Menu){
            g.drawImage(loader.backGroundMenu, 0 , 0 ,WIDTH, HEIGHT, null);
            menu.render(g);
        }
        else if(gameState == STATE.Game){
            g.drawImage(loader.backGroundGame, 0 , 0 ,WIDTH, HEIGHT, null);
            hud.render(g);
        }
        else if(gameState == STATE.GameFinalResult){
            g.drawImage(loader.backGroundMenu, 0 , 0 ,WIDTH, HEIGHT, null);
            game1FinalResult.render(g);
        }

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            var = max;
            return var;
        }
        else if(var <= min){
            var = min;
            return var;
        }
        else{
            return var;
        }
    }

    public static boolean inBoundsXY(Point mousePos, Point objectPos, Point objectSize){
        return (mousePos.x >= objectPos.x) && (mousePos.x <= objectPos.x + objectSize.x) &&
                (mousePos.y >= objectPos.y) && (mousePos.y <= objectPos.y + objectSize.y);
    }

    public static boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public static void main(String args[]){
        new Game();
    }
}
