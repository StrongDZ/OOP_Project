package Code.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Circle extends Objectss {
    private Image originalImage;
    private int length;

    public Circle(int Length) {
        this.length = Length;
        ImageIcon img = new ImageIcon("src/Code/Utils/BongDa.png");
        Image scaledImg = img.getImage();
        scaledImg = scaledImg.getScaledInstance(Length, Length, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImg);
        this.originalImage = icon.getImage();
        setIcon(new ImageIcon(icon.getImage()));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    public void resetObjectss() {
        super.resetObjectss();
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }

    public void calculateFriction(float AppliedForce, float staticCoeffient, float kinetiCoefficient) {
        float out;
        if (Math.abs(AppliedForce) <= 3 * this.normalForce() * staticCoeffient && this.getSpeed() == 0) {
            out = -AppliedForce / 3;
        } else if (Math.abs(AppliedForce) > 3 * this.normalForce() * staticCoeffient && this.getSpeed() == 0) {
            if (AppliedForce > 0) {
                out = -this.normalForce() * kinetiCoefficient;
            } else {
                out = this.normalForce() * kinetiCoefficient;
            }
        } else if (this.getSpeed() < 0) {
            out = this.normalForce() * kinetiCoefficient;
        } else {
            out = -this.normalForce() * kinetiCoefficient;
        }
        if (AppliedForce == 0 && this.getSpeed() == 0) {
            out = 0;
        }
        setFriction(out+airfric);
    }

    public void updateObject(float acceleration, float newGamma) {
        updateObject(acceleration);
        this.setGamma(newGamma);
        if (this.getOmega() * (this.getOmega() + time * newGamma) < 0 || this.getSpeed()==0) {
            this.setOmega(0);
        } else {
            this.setOmega(this.getOmega() + time * newGamma);
        }
        if (this.getTheta() * (this.getTheta() + time * this.getOmega()) < 0) {
            this.setTheta(0);
        } else {
            this.setTheta(this.getTheta() + time * this.getOmega());
        }
    }

    public float calculateGamma(float friction, int mass, int radius) {
        return  -2*friction / (mass * radius)*20;
    }

    public void rotateImage(double angle) {
        // Calculate the new size of the image after rotation
        int newSize = (int) (length * Math.sqrt(2));

        // Create a buffered image large enough to hold the rotated image
        BufferedImage bufferedImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Clear the background
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, newSize, newSize);

        // Draw the original image centered in the new image
        g2d.setComposite(AlphaComposite.Src);
        g2d.drawImage(originalImage, (newSize - length) / 2, (newSize - length) / 2, length, length, null);

        // Create the transform object and rotate the image around its center
        AffineTransform tx = AffineTransform.getRotateInstance(angle, newSize / 2.0, newSize / 2.0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);

        BufferedImage croppedImage = bufferedImage.getSubimage((newSize - length) / 2, (newSize - length) / 2, length, length);

        setIcon(new ImageIcon(croppedImage));
    }
}
