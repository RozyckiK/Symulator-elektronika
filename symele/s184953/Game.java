package symele.s184953;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;

    public Game(){
        new Window(WIDTH, HEIGHT, "Symulator elektronika", this);
    }

    public synchronized void start(){

    }

    public void run(){
        
    }

    public static void main(String args[]){

        new Game();
    }
}
