package symele.s184953;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa obiektu lutowia
 * @author Kacper Różycki
 */
public class Solder extends GameObject{

    /**
     * Deklaracja instancji loadera
     */
    private ResLoader loader;
    /**
     * Deklarcja obrazu lutowia
     */
    private BufferedImage solderImage;

    /**
     * Deklaracja instancji handlera
     */
    private Handler handler;

    /**
     * Deklarcja instacji hud
     */
    private Hud hud;

    /**
     * Delkarcja stanów lutowia
     */
    private Hud.SOLDERSTATE solderstate;

    //Setting difficulty level
    /**
     * Ilosc punktów potrzeba do osiągnięcia stanu Better lutowia dla różnych poziomów
     */
    private int[] better = {1000, 1000, 1000, 1000, 1000};
    /**
     * Ilosc punktów potrzeba do osiągnięcia stanu Perfect lutowia dla różnych poziomów
     */
    private int[] perfect = {1400, 1300, 1200, 1100, 1100};
    /**
     * Ilosc punktów potrzeba do osiągnięcia stanu Burned lutowia dla różnych poziomów
     */
    private int[] burned = {1800, 1600, 1400, 1200, 1200};

    /**
     * Punkty "palenia" lutowia, używane w celu sprawdzania jak długo lutownica była przyłożona do lutowia
     */
    private int burningScore = 0;

    /**
     * Konstruktor obiektu lutowia
     * @param x początkowa pozycja "x" obiektu
     * @param y początkowa pozycja "y" obiektu
     * @param id id obiektu
     * @param sizeX szerokość obiektu
     * @param sizeY wysokość obiektu
     * @param loader przkazanie loadera
     * @param handler przekazanie handlera
     * @param hud przekazanie hudu
     */
    public Solder(int x, int y, ID id, int sizeX, int sizeY, ResLoader loader, Handler handler, Hud hud) {
        super(x, y, id, sizeX, sizeY);
        this.loader = loader;
        this.handler = handler;
        this.hud = hud;
    }

    /**
     * Metoda odpowiadjąca za przekaznie granic obiektu
     * @return zwraca granice obiektu
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y ,sizeX, sizeY);
    }

    /**
     * Metoda odpowiadająca za odświeżenie obiektu lutowia
     */
    public void tick() {

        solderImage = loader.solderStart;
        collision();

        if(burningScore < better[hud.getLevel()]){
            solderImage = loader.solderStart;
            solderstate = Hud.SOLDERSTATE.start;
        } else if (burningScore < perfect[hud.getLevel()]) {
            solderImage = loader.solderBetter;
            solderstate = Hud.SOLDERSTATE.better;
        } else if (burningScore < burned[hud.getLevel()]) {
            solderImage = loader.solderPerfect;
            solderstate = Hud.SOLDERSTATE.perfect;
        }else {
            solderImage = loader.solderBurned;
            solderstate = Hud.SOLDERSTATE.burned;
        }

        int level = hud.getLevel();
        hud.setLevelResult(level, solderstate);
    }

    /**
     * <p>Metoda odpowiedzialna za sprawdzenie kolizji z innym obiektem</p>
     * <p>W tym przypadku podczas kolizji w zależności od temperatury</p>
     * <p>szybciej albo wolniej się zwiększa burningScore</p>
     */
    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.SolderingIron){
                if(getBounds().intersects(tempObject.getBounds())){
                    burningScore += 1 * (hud.getTemperature()-290)/10;
                }
            }
        }
    }

    /**
     * Metoda odpowiedzialna za renderowanie obiektu
     * @param g przekazanie grafiki
     */
    public void render(Graphics g) {
        g.drawImage(solderImage, x, y, sizeX, sizeY, null);
    }
}
