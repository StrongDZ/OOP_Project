package Code.Actor;

import javax.swing.*;
import java.awt.*;

public class Square extends Objectss{
    public Square(int newWidth, int newHeight){
        ImageIcon img = new ImageIcon("/Users/macbookpro/Documents/OOP_Project-2/src/Code/Actor/Hung.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(icon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
    public Square(float sideLength, float mass){
        super(sideLength, mass);
    }
    public float calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient){
        if(Math.abs(AppliedForce)<=this.normalForce()*staticCoeffient&&this.getSpeed()==0){
            return -AppliedForce;
        }
        else if(Math.abs(AppliedForce)>this.normalForce()*staticCoeffient&&this.getSpeed()==0){
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
}
