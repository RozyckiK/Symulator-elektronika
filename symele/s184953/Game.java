package symele.s184953;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Klasa odpowiedzialna za bazę gry
 * @author Kacper Różycki
 */
public class Game extends Canvas implements Runnable{

    /**
     * Wysokość i szerokość obszru gry
     */
    public static final int WIDTH = 900, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Menu menu;
    private Spawner spawner;
    private Hud hud;
    private ResLoader loader;
    private GameFinalResult gameFinalResult;

    /**
     * Stany gry
     */
    public enum STATE {
        Menu,
        Game,
        GameFinalResult,
    }

    public STATE gameState = STATE.Menu;

    /**
     * <p>Metoda odpowiedzialna za tworzenie instancji innych klas</p>
     * <p>wywołuje metode która jest odpowiedziala za załadowanie assetów gry</p>
     */
    public Game(){
        //------------------------------------------------
        handler = new Handler();
        loader = new ResLoader(handler);
        menu = new Menu(this, handler, loader);
        hud = new Hud(handler, this, loader);
        spawner = new Spawner(handler, this, loader, hud);
        gameFinalResult = new GameFinalResult(this,handler,loader,hud);
        Dragger dragger = new Dragger(handler);
        //------------------------------------------------
        this.addMouseListener(dragger);
        this.addMouseMotionListener(dragger);
        //-----------------------------------------------
        loader.loadAllAssets();
        new Window(WIDTH, HEIGHT, "Symulator elektronika", this);
    }

    /**
     * Uruchomienie wątku związanego z grą
     */
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * zatrzymanie wątku
     */
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za cykl gry z ograniczeniem do 60 klatek na sekundę
     */
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

    /**
     * Metoda odpowiadająca za wywoływanie innych metod związanych z odświeżaniem stanów obiektów
     */
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
            gameFinalResult.tick();
        }
    }

    /**
     * <p>Metoda odopowiadająca za wywołowanie innych metod związanych z renderowaniem obiektów<p/>
     * <p>Również w zależności od kolejności wywoływania zmienia sie pozycja obiektów na osi "z"<p/>
     */
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
            gameFinalResult.render(g);
        }

        handler.render(g);

        g.dispose();
        bs.show();
    }

    /**
     * <p>Metoda która utrzymuje liczbę w danym zakresie</p>
     * <p>Przykładowo gdy nasz zakres jest od min = 0 do max = 400 a naszą liczbą var jest 500 to funkcja zwróci liczbę 400</p>
     * <p>Dla tego samego zakresu gdy podamy liczbę 333 funkcja zwróci 333</p>
     * @param var nasza liczba którą podajemy
     * @param min minimalna wartość tego zakresu
     * @param max maxymalna wartość tego zakresu
     * @return funkcja zwraca liczbę nie mniejszą niż "min" i nie większą niż "max"
     */
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

    /**
     * metoda sprawdzająca czy myszka jest w danym miejsci na polu gry oparta na Pointach
     * @param mousePos pozycja myszki "x" i "y"
     * @param objectPos pozycja początkowa obszaru "x" i "y"
     * @param objectSize rozmiar obiektu szerokość i wysokość
     * @return zwracany boolean, gdy myszka znajduje sie w obszarze zwracany jest "true", gdy jest poza "false"
     */
    public static boolean inBoundsXY(Point mousePos, Point objectPos, Point objectSize){
        return (mousePos.x >= objectPos.x) && (mousePos.x <= objectPos.x + objectSize.x) &&
                (mousePos.y >= objectPos.y) && (mousePos.y <= objectPos.y + objectSize.y);
    }

    /**
     * metoda sprawdzająca czy myszka jest w danym miejscu na polu gry oparta na intach
     * @param mx pozycja "x" myszki
     * @param my pozycja "y" myszki
     * @param x początkowa pozycja "x" sprawdzanego obszaru
     * @param y początkowa pozycja "y" sprawdzanego obszaru
     * @param width szerokość obszaru
     * @param height wysokość obszaru
     * @return zwracany boolean, gdy myszka znajduje sie w obszarze zwracany jest "true", gdy jest poza "false"
     */
    public static boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    /**
     * Klasa main
     * @param args
     */
    public static void main(String args[]){
        new Game();
    }
}
