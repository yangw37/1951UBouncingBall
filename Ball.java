import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Ball implements Runnable {
    public int accX;
    public int accY;

    private int originalPositionX;
    private int originalPositionY;
    private int originalAccX;
    private int originalAccY;

    private Color color;
    private int positionX;
    private int positionY;
    private int boundX;
    private int boundY;
    private int radius;
    private int delayRate;
    public static int Random(int maxValue) {
        return (int) Math.round((Math.random() * maxValue));
    }
    public Ball(int positionX, int positionY, int boundX, int boundY) {
        this.originalPositionX = positionX;
        this.originalPositionY = positionY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.boundX = boundX;
        this.boundY = boundY;
        this.radius = 15;
        this.accX = Random(10);
        this.accY = Random(10);
        this.originalAccX = this.accX;
        this.originalAccY = this.accY;
        this.delayRate = 60;
        this.color = new Color(Random(255),Random(255),Random(255));
    }
    public int getPositionX(){return positionX;}
    public int getPositionY(){return positionY;}
    public int getRadius(){return radius;}
    public void changeColor(Color newColor){ this.color = newColor; }
    public void resetBall(){
        this.accX = this.originalAccX;
        this.accY = this.originalAccY;
        this.positionX = this.originalPositionX;
        this.positionY = this.originalPositionY;
    }

    @Override
    public void run() {
        Timer timer = new Timer( this.delayRate, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                positionX += accX;
                positionY += accY;

                if (positionX<radius){
                    accX = -accX;
                    positionX = radius;
                }else if ((positionX+ radius)>boundX){
                    accX = -accX;
                    positionX = boundX -  radius;
                }

                if (positionY<radius){
                    accY = -accY;
                    positionY = radius;
                }else if ((positionY+ radius)>boundY){
                    accY = -accY;
                    positionY = boundY -  radius;
                }
            }
        });
        timer.start();

    }
    public void show(Graphics g){
        g.setColor(this.color);
        g.fillOval((this.positionX-this.radius) , (this.positionY-this.radius), (2 * this.radius), (2 * this.radius));
    }
}