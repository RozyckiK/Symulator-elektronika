package symele.s184953;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa obiektu lutownicy
 * @author Kacper Różycki
 */
public class SolderingIron extends GameObject{

    /**
     * Deklaracja obrazu lutownicy
     */
    private BufferedImage solderingIronImage;

    /**
     * Konstruktor klasy lutownicy
     * @param x początkowa pozycja "x" obiektu
     * @param y początkowa pozycja "y" obiektu
     * @param id id obiektu
     * @param sizeX szerokość obiektu
     * @param sizeY wysokość obiektu
     * @param solderingIronImage przekazanie obrazu lutownicy
     */
    public SolderingIron(int x, int y, ID id, int sizeX, int sizeY,BufferedImage solderingIronImage) {
        super(x, y, id, sizeX, sizeY);
        velX = 0;
        velY = 0;
        this.solderingIronImage = solderingIronImage;
    }

    /**
     * Metoda odpowiadjąca za przekaznie granic obiektu
     * @return zwraca granice obiektu
     */
    public Rectangle getBounds(){
        return new Rectangle(x+38, y+230 ,19, 25);
    }

    /**
     * Deklaracja inta odpowiedzialnego za liczenie ticków
     */
    int tickCount = 0;

    /**
     * Metoda odpowiedzialna za odświeżenie obiektu
     */
    public void tick() {

        x += velX;
        y += velY;

        if(tickCount == 2){
            if(velX > 0 ) {velX -= 0.1;}

            if(velX < 0 ) {velX += 0.1;}

            if(velY > 0 ) {velY -= 0.1;}

            if(velY < 0 ) {velY += 0.1;}

            tickCount = 0;
        }

        x = (int) Game.clamp(x, 0, Game.WIDTH-sizeX);
        y = (int) Game.clamp(y, 0, Game.HEIGHT-sizeY);

        velX = Game.clamp(velX, -30, 30);
        velY = Game.clamp(velY, -30, 30);



        if(x+sizeX >= Game.WIDTH || x <= 0){
            velX = velX * -1;
        }

        if(y+sizeY >= Game.HEIGHT || y <= 0){
            velY = velY * -1;
        }

        tickCount++;
    }

    /**
     * Metoda odpowiedzialna za renderowanie obiektu
     * @param g przekazanie grafiki
     */
    public void render(Graphics g) {
        g.drawImage(solderingIronImage, x, y, sizeX, sizeY, null);
    }
}
