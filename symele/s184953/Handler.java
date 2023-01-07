package symele.s184953;

import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa odpowiedziala za wertowanie po wszystkich GameObiektach
 * @author Kacper Różycki
 */
public class Handler {

    /**
     * Lista obiektów
     */
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    /**
     * metoda odpowiedzialna za odświeżenie każdego obiektu
     */
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    /**
     * Metoda wywołująca metody renderowania wszystkich obiektów
     * @param g przekazania grafiki
     */
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    /**
     * Metoda dodająca obiekt do listy
     * @param object przekazuje obiekt
     */
    public void addObject(GameObject object){
        this.object.add(object);
    }

    /**
     * Metoda usuwająca obiekt
     * @param object przekazuje obiekt
     */
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    /**
     * Metoda usuwająca wszystkie obiekty na liście
     */
    public void clearAllObjects(){
        object.clear();
    }
}
