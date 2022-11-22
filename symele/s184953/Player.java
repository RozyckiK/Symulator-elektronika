package symele.s184953;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id, int sizeX, int sizeY) {
        super(x, y, id, sizeX, sizeY);
        velX = 0;
        velY = 0;
    }

    int tickCount = 0;
    public void tick() {

        x += velX;
        y += velY;

        if(tickCount == 5){
            if(velX > 0 ) {velX--;}

            if(velX < 0 ) {velX++;}

            if(velY > 0 ) {velY--;}

            if(velY < 0 ) {velY++;}

            tickCount = 0;
        }

        x = Game.clamp(x, 0, Game.WIDTH-sizeX);
        y = Game.clamp(y, 0, Game.HEIGHT-sizeY);

        velX = Game.clamp(velX, -3, 3);
        velY = Game.clamp(velY, -3, 3);



        if(x+sizeX >= Game.WIDTH || x <= 0){
            velX = velX * -1;
        }

        if(y+sizeY >= Game.HEIGHT || y <= 0){
            velY = velY * -1;
        }

        tickCount++;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, sizeX, sizeY);
    }
}
