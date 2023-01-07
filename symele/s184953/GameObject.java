package symele.s184953;

import java.awt.*;

/**
 * Abstrakcyjna klasa toworząca podstawe do innych obiektów w grze
 * @author Kacper Różycki
 */
public abstract class GameObject {

    /**
     * Pozycja i wielkość obieku
     */
    protected int x, y, sizeX, sizeY;
    /**
     * ID obiektu
     */
    protected ID id;
    /**
     * Prędkość obiektu w danym kierunku
     */
    protected int velX, velY;

    /**
     * Konstruktor
     * @param x pozycja "x" obiektu
     * @param y pozycja "y" obietku
     * @param id ID obiektu
     * @param sizeX szerokość obiektu
     * @param sizeY wysokość obiektu
     */
    public GameObject(int x, int y, ID id, int sizeX, int sizeY){
        this.x = x;
        this.y = y;
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Metoda odświeżenia obiektu
     */
    public abstract void tick();

    /**
     * Metoda generowania obrazu obieku
     * @param g przekazanie grafiki
     */
    public abstract void render(Graphics g);

    /**
     * <p>Metoda do sprawdzania granic obiektu</p>
     * <p>W celu sprawdzania czy dane obiekty na siebie nie nachodzą</p>
     * @return zwraca granice obiektu
     */
    public abstract Rectangle getBounds();

    /**
     * Getter "x"
     * @return zwraca pozycje "x" obiektu
     */
    public int getX() {
        return x;
    }

    /**
     * Setter "x"
     * @param x ustawia pozycje "x" obiektu
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter "y"
     * @return zwraca pozycje "y" obiektu
     */
    public int getY() {
        return y;
    }

    /**
     * Setter "y"
     * @param y ustawia pozycje "y" obiektu
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter ID
     * @return zwraca ID obiektu
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter ID
     * @param id ustawia ID obiektu
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * Getter "velX"
     * @return zwraca prędkość obiektu na osi x
     */
    public int getVelX() {
        return velX;
    }

    /**
     * Setter "velX'
     * @param velX ustawia parametr prędkości obiektu na osi x
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Getter "velY"
     * @return zwraca prędkość obiektu na osi y
     */
    public int getVelY() {
        return velY;
    }

    /**
     * Setter "velX'
     * @param velY ustawia parametr prędkości obiektu na osi y
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * Getter "sizeX"
     * @return zwraca szerokość obiektu
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Setter "sizeX"
     * @param sizeX ustawia szerokość obiektu
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Getter "sizeY"
     * @return zwraca wysokość obiektu
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Setter "sizY"
     * @param sizeY ustawia wysokość obiektu
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
