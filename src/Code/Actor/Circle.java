package Code.Actor;

import javax.swing.*;
import java.awt.*;

public class Circle extends Objectss{
    public Circle(int Length){
        ImageIcon img = new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Actor\\Duyen.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(Length, Length, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(new ImageIcon(getCircularImage(icon.getImage())));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }




    public void resetObjectss(){
        super.resetObjectss();
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }

    public void calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient){
        float out;
//        System.out.println("hah" + this.getAppliedForce() +" "+this.getFriction()+ " "+this.getSpeed());
        if(Math.abs(AppliedForce)<=3*this.normalForce()*staticCoeffient&&this.getSpeed()==0){
//            System.out.println("hahahaha");
            out = -AppliedForce/3;
        }
        else if(Math.abs(AppliedForce)>3*this.normalForce()*staticCoeffient&&this.getSpeed()==0){

            if(AppliedForce>0){
                out = -this.normalForce()*kinetiCoefficient;
            }
            else{
                out = this.normalForce()*kinetiCoefficient;
            }
        }
        else if(this.getSpeed()<0){
            out = this.normalForce()*kinetiCoefficient;
        }
        else{
            out = -this.normalForce()*kinetiCoefficient;
        }
        if(AppliedForce == 0 && this.getSpeed()==0){
            out=0;
//            System.out.println("hahahahahahahahahaha");
        }
        setFriction(out);
    }

    public void updateObject(float acceleration, float newGamma){
        updateObject(acceleration);
        this.setGamma(newGamma);
        if(this.getOmega()*(this.getOmega()+time*newGamma)<0){
            this.setOmega(0);
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

    public float calculateGamma(float friction, int mass, int radius){
        return -2*friction/(mass*radius*radius)*10000;
    }
}