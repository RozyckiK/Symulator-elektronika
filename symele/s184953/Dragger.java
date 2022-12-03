package symele.s184953;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Dragger implements MouseListener, MouseMotionListener {

    Point startLoc, objectPos, objectSize, mousePos, mouseAnchor;
    GameObject tempObject, checkObject;
    int lastX = 0, lastY = 0, velX, velY;

    private Handler handler;

    public Dragger(Handler handler){
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startLoc = new Point((int) e.getX(),(int) e.getY());

        for(int i = 0; i < handler.object.size(); i++) {
            checkObject = handler.object.get(i);


            objectPos = new Point((int) checkObject.getX(),(int) checkObject.getY());
            objectSize = new Point((int) checkObject.getSizeX(),(int) checkObject.getSizeY());
            mouseAnchor = new Point((int)(startLoc.x-objectPos.x),(int)(startLoc.y-objectPos.y));

            if((checkObject.getId() == ID.Player) && Game.inBoundsXY(startLoc, objectPos, objectSize)){
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


            mousePos = new Point((int) e.getX(), (int) e.getY());
            velX = mousePos.x - lastX;
            velY = mousePos.y - lastY;

            tempObject.setX(mousePos.x - mouseAnchor.x);
            tempObject.setY(mousePos.y - mouseAnchor.y);

            lastX = mousePos.x;
            lastY = mousePos.y;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}