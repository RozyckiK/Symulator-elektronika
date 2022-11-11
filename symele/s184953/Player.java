package symele.s184953;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    public Player(int x, int y, ID id, int sizeX, int sizeY) {
        super(x, y, id, sizeX, sizeY);
        //Wartości na testowanie


    }

    public void tick() {


        //Do ustawienia
        x = Game.clamp(x, 0, Game.WIDTH-sizeX);
        y = Game.clamp(y, 0, Game.HEIGHT-sizeY);
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, sizeX, sizeY);
    }
}
