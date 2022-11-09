package symele.s184953;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

        //Wartości na testowanie
        velX = 3;
        velY = 0;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH-100);
        y = Game.clamp(y, 0, Game.HEIGHT);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
