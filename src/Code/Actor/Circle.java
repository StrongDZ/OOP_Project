package Code.Actor;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Circle extends Objectss implements rotatingObject{
    public Circle(int newWidth, int newHeight){
        ImageIcon img = new ImageIcon("/Users/macbookpro/Documents/OOP_Project-2/src/Code/Actor/Duyen.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(new ImageIcon(getCircularImage(icon.getImage())));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
    private float gamma=0, omega=0,theta=0;
    public Circle(float radius, float mass){
        super(radius, mass);
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
    
    public void setTheta(float theta){
        this.theta=theta;
    }

    public void resetObjectss(){
        super.resetObjectss();
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }

    public float calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient){
        if(Math.abs(AppliedForce)<=3*this.normalForce()*staticCoeffient&&this.getSpeed()==0){
            return -AppliedForce/3;
        }
        else if(Math.abs(AppliedForce)>3*this.normalForce()*staticCoeffient&&this.getSpeed()==0){
            if(AppliedForce>0){
                return -this.normalForce()*kinetiCoefficient;
            }
            else{
                return this.normalForce()*kinetiCoefficient;
            }
        }
        else if(this.getSpeed()<0){
            return this.normalForce()*kinetiCoefficient;
        }
        else{
            return -this.normalForce()*kinetiCoefficient;
        }
    }
    public void updateObject(float acceleration, float newGamma){
        updateObject(acceleration);
        this.setGamma(newGamma);
        if(this.getOmega()*(this.getOmega()+time*newGamma)<0){
            this.setGamma(0);
        }
        else{
            this.setOmega(this.getOmega()+time*newGamma);
        }
        if(this.getTheta()*(this.getTheta()+time*this.getOmega())<0){
            this.setTheta(0);
        }
        else{
            this.setTheta(this.getTheta()+time*this.getOmega());
        }
    }
    public float calculateGamma(float friction, float mass, float radius){
        return -2*friction/(mass*radius);
    }

    @Override
    public float calculateGamme(float friction, float mass, float radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateGamme'");
    }
}
