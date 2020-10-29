package src;

import java .awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 * This is the main for BouncingBall.
 * @author Jun
 */
public class BouncingBall {
    /**
     * The main will create a JFrame and add a BallPanel.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Bouncing Ball Game");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container a = frame.getContentPane();
        a.setBackground(Color.WHITE);
        frame.setContentPane(new GamePanel(frame));
        frame.pack();
        frame.setVisible(true);
    }
}