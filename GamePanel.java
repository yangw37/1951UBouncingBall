package  src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private int panelHeight;
    private int panelWidth;
    private ArrayList<Ball> balls;
    private ExecutorService executor;
    private boolean isPaused;
    final int FRAME_RATE = 10;
    final int DEFAULT_HEIGHT = 600;
    final int DEFAULT_WIDTH = 800;


    public GamePanel(JFrame f) {
        this.panelHeight = DEFAULT_HEIGHT;
        this.panelWidth = DEFAULT_WIDTH;
        this.setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        this.setBackground(Color.WHITE);
        this.executor = Executors.newCachedThreadPool();
        this.setFocusable(true);
        this.balls = new ArrayList<Ball>();
        this.isPaused = false;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                    switch (key) {
                        case KeyEvent.VK_Z:
                            Ball newBall = new Ball(DEFAULT_WIDTH, DEFAULT_HEIGHT,isPaused);
                            balls.add(newBall);
                            executor.execute(newBall);
                            break;
                        case KeyEvent.VK_X:
                            if (balls.size() >=1) {
                                balls.remove(0);
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            if (isPaused){
                                for(Ball ball:balls) {
                                    ball.start();
                                }
                            } else {
                                for(Ball ball:balls) {
                                    ball.stop();
                                }
                            }
                            isPaused = !isPaused;
                            break;
                        case KeyEvent.VK_C:
                            balls = new ArrayList<Ball>();
                            break;
                        case KeyEvent.VK_UP:
                            for(Ball ball:balls) {
                                ball.faster();
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            for(Ball ball:balls) {
                                ball.slower();
                            }
                            break;
                    }
                }
        });
        executor.execute(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("Been here");
        g.drawString("Z: add \r X: delete\r C: clear all\r Space: stop/start \r Up: faster \r Down: speed slower", 30, 30);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    @Override
    public void run() {
        Timer timer = new Timer(FRAME_RATE, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        });
        timer.start();

    }


}
