package symele.s184953;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class Hud extends MouseAdapter {

    private Handler handler;
    private Game game;

    public Hud(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    public void tick(){

    }

    public void render(Graphics g){

    }
}
