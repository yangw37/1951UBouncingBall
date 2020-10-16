import javax.swing.*;
import java.awt.Color;

public class BouncingBall {
    public static void main(String[] args){
        JFrame frame = new JFrame("BouncingBall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel(frame);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
