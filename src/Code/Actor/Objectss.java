package Code.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public abstract class Objectss extends JButton {
    protected float side, position=0, friction=0, speed=0, acceleration, mass=0, time;
    public Image getCircularImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new Ellipse2D.Float(0, 0, img.getWidth(null), img.getHeight(null)));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    public Objectss(float side, float mass){
        this.side=side;
        this.mass=mass;
    }
    public float getSide(){
        return side;
    }

    public void setSide(float side){
        this.side=side;
    }
    public float getMass(){
        return mass;
    }

    public void setMass(float mass){
        this.mass=mass;
    }

    public float getPosition(){
        return position;
    }

    public void setPosition(float position){
        this.position=position;
    }
    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speed){
        this.speed=speed;
    }

    public float getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(float acceleration){
        this.acceleration=acceleration;
    }
    public float getTime(){
        return time;
    }

    public void setTime(float time){
        this.time=time;
    }
    public void resetObjectss(){
        this.position=0;
        this.speed=0;
        this.acceleration=0;
    }

    public float normalForce(){
        return 10*mass;
    }
    public void updateObject(float acceleration){
        //Update acceleration
        this.setAcceleration(acceleration);
        //Update velocity
        if(this.getSpeed()*(this.getSpeed()+time*acceleration)<0){
            this.setSpeed(0);
        }
        else{
            this.setSpeed(this.getSpeed()+time*acceleration);
        }
        //Update position
        this.setPosition(this.getPosition()+time*this.getSpeed());
    }

    public float calculateAcceleration(float AppliedForce, float friction){
        return(AppliedForce+friction)/this.getMass();
    }

    public abstract float calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient);
    public Objectss(){}

}
