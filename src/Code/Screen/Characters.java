package Code.Screen;

import Code.Object.Circle;
import Code.Object.Square;
import Code.LinhTinh.ValueInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Characters extends JPanel {
    Square square = new Square(180);
    Circle circle = new Circle(180);
    MainScreen screen;

    public Characters(MainScreen screen) {
        this.screen = screen;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border with 2 pixels width
        setPreferredSize(new Dimension(500, 260));
        setBorder(border);
        add(Box.createGlue());
        add(square);
        add(Box.createRigidArea(new Dimension(50, 50)));
        add(circle);
        add(Box.createGlue());

        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValueInput values = new ValueInput("length");
                if (values != null) {
                    screen.mainCharacter.add(new Square(values.length), values.length, values.mass);
                }
            }
        });

        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValueInput values = new ValueInput("radius");
                if (values != null) {
                    screen.mainCharacter.add(new Circle(values.length), values.length, values.mass);
                }
            }
        });
    }
}




