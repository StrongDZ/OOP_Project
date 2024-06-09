package Code.Screen;

import Code.Object.Objectss;

import javax.swing.*;
import java.awt.*;

public class MainCharacter extends JLayeredPane {
    public Objectss mainCharacter;
    public ArrowPanel Force = new ArrowPanel(0,0);
    int length;
    boolean vif=false, vis=false;
    public MainCharacter() {
        setPreferredSize(new Dimension(500, 250));
        add(Force);
        setVisible(true);
        setOpaque(false);
    }

    public void add(Objectss nvc, int length, int mass) {
        if (mainCharacter != null) {
            remove(mainCharacter);
        }
        this.length=length;
        mainCharacter = nvc;
        mainCharacter.setMass(mass);
        mainCharacter.setSide(length);
        mainCharacter.setBounds(750 - length / 2, 250 - length, length, length);
        add(mainCharacter,Integer.valueOf(1));

        revalidate();
        repaint();
    }

    public void updateForce(){
        remove(Force);
        float friction = mainCharacter.getFriction();
        float appliedForce = mainCharacter.getAppliedForce();
        Force = new ArrowPanel(appliedForce,friction);
        Force.setBounds(550,250-length,400,150);
        StatusOfForce(vif);
        StatusOfSum(vis);
        add(Force,Integer.valueOf(2));
        revalidate();
        repaint();
    }

    public void StatusOfForce(boolean status){
        Force.AFar.setVisible(status);
        Force.FRar.setVisible(status);
    }

    public void StatusOfSum(boolean status){
        Force.sum_arrow.setVisible(status);
    }
    public void reset(){
        remove(Force);
        Force = new ArrowPanel(0,0);
        add(Force,Integer.valueOf(2));
        revalidate();
        repaint();
    }
}
