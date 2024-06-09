package Code.Object;

import javax.swing.*;
import java.awt.*;

public class Square extends Objectss{
    public Square(int Length){
        ImageIcon img = new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\LinhTinh\\Hung.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(Length, Length, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(icon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
    public void calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient){
        float out ;
        if(Math.abs(AppliedForce)<=this.normalForce()*staticCoeffient&&this.getSpeed()==0){
            out = -AppliedForce;
        }
        else if(Math.abs(AppliedForce)>this.normalForce()*staticCoeffient&&this.getSpeed()==0){
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
        if(AppliedForce == 0 && this.getSpeed()==0)out=0;
        setFriction(out);
    }
}
