package symele.s184953;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SolderingIron extends GameObject{

    private BufferedImage solderingIronImage;

    public SolderingIron(int x, int y, ID id, int sizeX, int sizeY,BufferedImage solderingIronImage) {
        super(x, y, id, sizeX, sizeY);
        velX = 0;
        velY = 0;
        this.solderingIronImage = solderingIronImage;
    }

    public Rectangle getBounds(){
        return new Rectangle(x+38, y+230 ,19, 25);
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
        g.drawImage(solderingIronImage, x, y, sizeX, sizeY, null);
    }
}
