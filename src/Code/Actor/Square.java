package Code.Actor;

import javax.swing.*;
import java.awt.*;

public class Square extends Objectss{
    public Square(int newWidth, int newHeight){
        ImageIcon img = new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Actor\\Hung.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(icon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
}
