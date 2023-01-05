package symele.s184953;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Solder extends GameObject{

    private ResLoader loader;
    private BufferedImage solderImage;
    private Handler handler;

    private Hud hud;

    private Hud.SOLDERSTATE solderstate;

    //Setting difficulty level
    private int[] better = {1000, 1000, 1000, 1000, 1000};
    private int[] perfect = {1500, 1400, 1300, 1200, 1100};
    private int[] burned = {2000, 1800, 1600, 1400, 1200};

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

        if(burningScore < better[hud.getLevel()]){
            solderImage = loader.solderStart;
            solderstate = Hud.SOLDERSTATE.start;
        } else if (burningScore < perfect[hud.getLevel()]) {
            solderImage = loader.solderBetter;
            solderstate = Hud.SOLDERSTATE.better;
        } else if (burningScore < burned[hud.getLevel()]) {
            solderImage = loader.solderPerfect;
            solderstate = Hud.SOLDERSTATE.perfect;
        }else {
            solderImage = loader.solderBurned;
            solderstate = Hud.SOLDERSTATE.burned;
        }

        int level = hud.getLevel();
        hud.setLevelResult(level, solderstate);
    }

    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.SolderingIron){
                if(getBounds().intersects(tempObject.getBounds())){
                    burningScore += 1 * (hud.getTemperature()-290)/10;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(solderImage, x, y, sizeX, sizeY, null);
    }
}
