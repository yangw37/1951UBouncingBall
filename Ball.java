package src;
import java.awt.Color;
import java.awt.Graphics;


public class Ball implements Runnable {
    private int radius;
    private int x;
    private int y;
    private int verticalSpeed;
    private int horizontalSpeed;
    // r,g,b value for color
    private int r;
    private int g;
    private int b;
    private int boardWidth;
    private int boardHeight;
    private boolean isPuased;

    final int MAX_RADIUS = 15;
    final int MIN_RADIUS = 6;
    final int MAX_SPEED = 6;
    final int MIN_SPEED = 2;
    final int MAX_RGB_VALUE = 255;


    public Ball(int boardWidth, int boardHeight, boolean isGamePaused) {
        this.isPuased = isGamePaused;
        this.x = getRandomInt(0, boardWidth);
        this.y = getRandomInt(0, boardHeight);
        this.radius = getRandomInt(MIN_RADIUS, MAX_RADIUS);
        this.verticalSpeed = setPolarization(getRandomInt(MIN_SPEED, MAX_SPEED));
        this.horizontalSpeed = setPolarization(getRandomInt(MIN_SPEED, MAX_SPEED));
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.r = getRandomInt(0, MAX_RGB_VALUE);
        this.g = getRandomInt(0, MAX_RGB_VALUE);
        this.b = getRandomInt(0, MAX_RGB_VALUE);
    }

    private int getRandomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private int setPolarization(int n) {
        return Math.random() > 0.5 ? n : n * -1;
    }

    public void start() {
        this.isPuased = false;
    }

    public void stop() {
        this.isPuased = true;
    }

    public void faster() {
        this.verticalSpeed += this.verticalSpeed / Math.abs(this.verticalSpeed);
        this.horizontalSpeed += this.horizontalSpeed / Math.abs(this.horizontalSpeed);
    }

    public void slower() {
        if (Math.abs(this.verticalSpeed) > 1 && Math.abs(this.horizontalSpeed) > 1){
            this.verticalSpeed -= this.verticalSpeed / Math.abs(this.verticalSpeed);
            this.horizontalSpeed -= this.horizontalSpeed / Math.abs(this.horizontalSpeed);
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(new Color(this.r,this.g ,this.b));
        graphics.fillOval((this.x - this.radius) , (this.y - this.radius), (2 * this.radius), (2 * this.radius));
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.isPuased) {
                this.x = this.x + this.horizontalSpeed;
                this.y = this.y + this.verticalSpeed;
                // handles horizontal bouncing
                if (this.x < this.radius || this.x + this.radius > this.boardWidth) {
                    if (this.x < this.radius) {
                        this.x = this.radius;
                    } else if (this.x + this.radius > this.boardWidth) {
                        this.x = this.boardWidth - this.radius;
                    }
                    this.horizontalSpeed = -1 * this.horizontalSpeed;
                }
                // handles vertical bouncing
                if (this.y < this.radius || this.y + this.radius > this.boardHeight) {
                    if (this.y < this.radius) {
                        this.y = this.radius;
                    } else if (this.y + this.radius > this.boardHeight) {
                        this.y = this.boardHeight - this.radius;
                    }
                    this.verticalSpeed = -1 * this.verticalSpeed;
                }
            }
        }
    }

}
