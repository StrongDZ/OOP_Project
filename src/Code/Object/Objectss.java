package Code.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public abstract class Objectss extends JButton {
    protected float position=0, speed=0, acceleration, time=(float)0.01, friction=0,
            appliedForce=0, staticfric=0, kineticfric=0, airfric = 0;
    protected int mass=0, side;
    protected float gamma=0, omega=0,theta=0;
    public Image getCircularImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new Ellipse2D.Float(0, 0, img.getWidth(null), img.getHeight(null)));
        g2d.drawImage(img, 0, 0, null);

        g2d.dispose();
        return bufferedImage;
    }
    public float getGamma(){
        return gamma;
    }

    public void setGamma(float gamma){
        this.gamma=gamma;
    }

    public float getOmega(){
        return omega;
    }

    public void setOmega(float omega){
        this.omega=omega;
    }

    public float getTheta(){
        return theta;
    }

    public float getStaticfric() {
        return staticfric;
    }

    public void setStaticfric(float staticfric) {
        this.staticfric = staticfric;
    }

    public float getKineticfric() {
        return kineticfric;
    }

    public void setKineticfric(float kineticfric) {
        this.kineticfric = kineticfric;
    }

    public void setTheta(float theta){
        this.theta=theta;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public void resetObjectss(){
        this.position=0;
        this.speed=0;
        this.acceleration=0;
        this.airfric = 0;
    }
    public int getMass(){
        return mass;
    }

    public float getAppliedForce() {
        return appliedForce;
    }
    public void setAppliedForce(int appliedForce) {
        this.appliedForce = appliedForce;
    }
    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }
    public void setMass(int mass){
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
    public int normalForce(){
        return 10*mass;
    }
    public float sumofforce(){return this.getAppliedForce()+this.getFriction();}
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

    public float calculateAcceleration(){
        return this.sumofforce()/this.getMass();
    }

    public void setAirfric(float airfric) {
        this.airfric = airfric;
    }

    public abstract void calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient);

}
