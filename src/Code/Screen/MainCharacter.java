package Code.Screen;

import Code.Actor.Objectss;
import Code.test;

import javax.swing.*;
import java.awt.*;

public class MainCharacter extends JLayeredPane {
    public Objectss mainCharacter;
    ArrowPanel Force = new ArrowPanel(10,-300);

    public MainCharacter() {
        setPreferredSize(new Dimension(500, 250));
//        new test(Force);
        Force.setBounds(550,0,400,150);
        add(Force,Integer.valueOf(1));
        setVisible(true);
        setOpaque(false);
    }

    public void add(Objectss nvc, int length, int mass) {
        if (mainCharacter != null) {
            remove(mainCharacter);
        }

        mainCharacter = nvc;
        mainCharacter.setMass(mass);
        mainCharacter.setSide(length);
        Force.setBounds(820,250-length,400,150);
        mainCharacter.setBounds(750 - length / 2, 250 - length, length, length);
        add(mainCharacter,Integer.valueOf(2));

        revalidate();
        repaint();
    }
}
