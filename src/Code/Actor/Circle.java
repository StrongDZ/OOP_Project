package Code.Actor;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Circle extends Objectss{
    public Circle(int newWidth, int newHeight){
        ImageIcon img = new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Actor\\Duyen.jpg");
        Image scaledImg = img.getImage();

        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        setIcon(new ImageIcon(getCircularImage(icon.getImage())));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
}
