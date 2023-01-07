package symele.s184953;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Klasa odpowiadająca za możliwość przesuwania obiektu sposobem drag
 * @author Kacper Różycki
 */
public class Dragger implements MouseListener, MouseMotionListener {

    /**
     * Deklaracja pointów, pozycji myszki, obiektu i kotwicy myszki
     */
    Point startLoc, objectPos, objectSize, mousePos, mouseAnchor;

    /**
     * Tymczasowe obiekty
     */
    GameObject tempObject, checkObject;
    /**
     * Ostatnie pozycje i prędkość myszki
     */
    int lastX = 0, lastY = 0, velX, velY;

    private Handler handler;

    /**
     * Konstruktor klasy
     * @param handler przekazanie handlera
     */
    public Dragger(Handler handler){
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * <p>Metoda sprawdzająca czy myszka jest nad obiektem który można przesunąć</p>
     * <p>I przypisująca pozycje w stosunku do obiektu</p>
     * <p>Pozwala to na przesuwanie obiektu w dowolnym miejscu jego trzymania</p>
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        startLoc = new Point(e.getX(), e.getY());

        for(int i = 0; i < handler.object.size(); i++) {
            checkObject = handler.object.get(i);


            objectPos = new Point(checkObject.getX(), checkObject.getY());
            objectSize = new Point(checkObject.getSizeX(), checkObject.getSizeY());


            if((checkObject.getId() == ID.SolderingIron) && Game.inBoundsXY(startLoc, objectPos, objectSize)){
                mouseAnchor = new Point(startLoc.x-objectPos.x, startLoc.y-objectPos.y);
                tempObject = checkObject;
                tempObject.setVelX(0);
                tempObject.setVelY(0);
            }
        }
    }

    /**
     * W celach wizualnych po "rzuceniu" obiektem będzie się on poruszał przez ułamek sekundy
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(tempObject != null){
            tempObject.setVelX(velX);
            tempObject.setVelY(velY);


            tempObject = null;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * <p>Przenoszenie obiektu do aktualnej pozycji myszki</p>
     * <p>Co cykl jest wyliczana aktualna prędkość obiektu na podstawie ostatniej pozycji myszki</p>
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        if(tempObject != null) {

            mousePos = new Point(e.getX(), e.getY());
            velX = mousePos.x - lastX;
            velY = mousePos.y - lastY;

            int setX = Game.clamp(mousePos.x - mouseAnchor.x, 0, Game.WIDTH-tempObject.getSizeX());
            int setY = Game.clamp(mousePos.y - mouseAnchor.y, 70, Game.HEIGHT-tempObject.getSizeY());

            tempObject.setX(setX);
            tempObject.setY(setY);

            lastX = mousePos.x;
            lastY = mousePos.y;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}