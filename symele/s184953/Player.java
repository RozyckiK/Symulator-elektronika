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

        if(tickCount == 30){
            if(velX > 0 ) {velX -= 0.1;}

            if(velX < 0 ) {velX += 0.1;}

            if(velY > 0 ) {velY -= 0.1;}

            if(velY < 0 ) {velY += 0.1;}

            tickCount = 0;
        }

        x = (int) Game.clamp(x, 0, Game.WIDTH-sizeX);
        y = (int) Game.clamp(y, 70, Game.HEIGHT-sizeY);

        velX = Game.clamp(velX, -30, 30);
        velY = Game.clamp(velY, -30, 30);



        if(x+sizeX >= Game.WIDTH || x <= 0){
            velX = velX * -1;
        }

        if(y+sizeY >= Game.HEIGHT || y <= 70){
            velY = velY * -1;
        }

        tickCount++;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, sizeX, sizeY);

        //Napisać kod odpowiadający za wgrywanie obrazu
        //g.drawImage(, x, y, width, height, null);
    }
}
