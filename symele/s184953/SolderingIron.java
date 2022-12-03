package symele.s184953;

import java.awt.*;

public class SolderingIron extends GameObject{


    public SolderingIron(int x, int y, ID id, int sizeX, int sizeY) {
        super(x, y, id, sizeX, sizeY);
        velX = 0;
        velY = 0;
    }

    int tickCount = 0;
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

    public void render(Graphics g) {
        g.setColor(new Color(43,43,43));
        g.fillRect(x, y, sizeX, sizeY);
    }
}
