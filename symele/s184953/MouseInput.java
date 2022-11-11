package symele.s184953;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;

    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public void mouseDragged(MouseEvent e){
        int mousePosX = e.getX();
        int mousePosY = e.getY();


        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player  ){
                tempObject.setY(mousePosY - 16);
                tempObject.setX(mousePosX - 16);
            }
        }


        System.out.println("X: "+mousePosX + " Y: "+mousePosY);
    }


}
