package symele.s184953;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Solder extends GameObject{

    private ResLoader loader;
    private BufferedImage solderImage;
    private Handler handler;

    private Hud hud;

    private int burningScore = 0;
    public Solder(int x, int y, ID id, int sizeX, int sizeY, ResLoader loader, Handler handler, Hud hud) {
        super(x, y, id, sizeX, sizeY);
        this.loader = loader;
        this.handler = handler;
        this.hud = hud;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y ,sizeX, sizeY);
    }

    public void tick() {
        solderImage = loader.solderStart;
        collision();

        if(burningScore < 1000){
            solderImage = loader.solderStart;
        } else if (burningScore < 2000) {
            solderImage = loader.solderBetter;
        } else if (burningScore < 3000) {
            solderImage = loader.solderPerfect;
        }else {
            solderImage = loader.solderBurned;
        }
    }

    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.SolderingIron){
                if(getBounds().intersects(tempObject.getBounds())){
                    burningScore += 1 * (hud.getTemperature()-290)/10;
                    System.out.println(burningScore);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(solderImage, x, y, sizeX, sizeY, null);
    }
}
