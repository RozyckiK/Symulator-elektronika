package symele.s184953;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Dragger implements MouseListener, MouseMotionListener {

    Point startLoc, objectPos, objectSize, mousePos, mouseAnchor;
    GameObject tempObject, checkObject;
    int lastX = 0, lastY = 0, velX, velY;

    private final Handler handler;

    public Dragger(Handler handler){
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startLoc = new Point(e.getX(), e.getY());

        for(int i = 0; i < handler.object.size(); i++) {
            checkObject = handler.object.get(i);


            objectPos = new Point(checkObject.getX(), checkObject.getY());
            objectSize = new Point(checkObject.getSizeX(), checkObject.getSizeY());


            if((checkObject.getId() == ID.SolderingIron) && Game.inBoundsXY(startLoc, objectPos, objectSize)){
                mouseAnchor = new Point(startLoc.x-objectPos.x, startLoc.y-objectPos.y);
                tempObject = checkObject;
                tempObject.setVelX(0);
                tempObject.setVelY(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(tempObject != null){
            tempObject.setVelX(velX);
            tempObject.setVelY(velY);


            tempObject = null;

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(tempObject != null) {

            mousePos = new Point(e.getX(), e.getY());
            velX = mousePos.x - lastX;
            velY = mousePos.y - lastY;

            int setX = Game.clamp(mousePos.x - mouseAnchor.x, 0, Game.WIDTH-tempObject.getSizeX());
            int setY = Game.clamp(mousePos.y - mouseAnchor.y, 70, Game.HEIGHT-tempObject.getSizeY());

            tempObject.setX(setX);
            tempObject.setY(setY);

            lastX = mousePos.x;
            lastY = mousePos.y;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}