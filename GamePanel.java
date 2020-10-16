import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.geom.Point2D;



public class GamePanel extends JPanel implements Runnable {
    private int frameRate;
    private HashSet<Ball> ballSet;
    private ExecutorService executor;
    private int pWidth;
    private int pHight;

    public GamePanel(JFrame f) {
        this.pWidth = 500;
        this.pHight = 500;
        this.setPreferredSize(new Dimension(this.pWidth, this.pHight));
        this.setBackground(Color.white);
        this.frameRate = 20;
        this.ballSet = new HashSet<>();
        this.executor = Executors.newCachedThreadPool();
        this.setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                for (Ball ball : ballSet) {
                    switch (key) {
                        case KeyEvent.VK_RIGHT:
                            ball.accX = absPlus(ball.accX,1);
                            break;
                        case KeyEvent.VK_LEFT:
                            ball.accX = absMinus(ball.accX,1);
                            break;
                        case KeyEvent.VK_UP:
                            ball.accY = absPlus(ball.accY,1);
                            break;
                        case KeyEvent.VK_DOWN:
                            ball.accY = absMinus(ball.accY,1);
                            break;
                        case KeyEvent.VK_SPACE:
                            ball.resetBall();
                            break;
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                boolean clickBall = false;
                for (Ball ball : ballSet) {
                    double distance = Point2D.distance(ball.getPositionX(), ball.getPositionY(), event.getX(), event.getY());
                    if (distance < ball.getRadius()) {
                        ball.changeColor(new Color(Ball.Random(255), Ball.Random(255), Ball.Random(255)));
                        clickBall = true;
                    }
                }
                if (!clickBall) {
                    Ball ball = new Ball(event.getX(), event.getY(), pWidth, pHight);
                    ballSet.add(ball);
                    executor.execute(ball);
                }
            }
        });

        executor.execute(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : ballSet) {
            ball.show(g);
        }
    }

    @Override
    public void run() {
        Timer timer = new Timer(frameRate, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        });
        timer.start();
    }
    public static int absPlus(int num1, int num2){
        return (num1>0) ? num1+num2 : num1-num2;
    }

    public static int absMinus(int num1, int num2){
        return (num1>0) ? num1-num2 : num1+num2;
    }
}
