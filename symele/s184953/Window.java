package symele.s184953;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Klasa odpowiadająca za tworzenie okna gry
 * @author Kacper Różycki
 */
public class Window extends Canvas {

    /**
     * Konstruktor klasy Window
     * @param width szerokość okna gry
     * @param height wysokość okna gry
     * @param title nazwa okna
     * @param game przekazanie gry
     */
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        game.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
