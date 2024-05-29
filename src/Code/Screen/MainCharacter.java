package Code.Screen;

import Code.Actor.Objectss;

import javax.swing.*;
import java.awt.*;

public class MainCharacter extends JPanel {
    public Objectss mainCharacter;

    public MainCharacter() {
        setPreferredSize(new Dimension(250, 250));
        setLayout(null);
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
        mainCharacter.setBounds(125 - length / 2, 250 - length, length, length);
        add(mainCharacter);

        revalidate();
        repaint();
    }
}
