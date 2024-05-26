package Code.Screen;

import Code.Actor.Circle;
import Code.Actor.Square;
import Code.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Characters extends JPanel {
    Square hung = new Square(180,180);
    Circle duyen = new Circle(180,180);
    public Characters(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Viền đen có độ rộng là 2 pixels
        setPreferredSize(new Dimension(500,300));
        setBorder(border);
        add(Box.createGlue());
        add(hung);
        add(Box.createRigidArea(new Dimension(50,50)));
        add(duyen);
        add(Box.createGlue());
        hung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"asdfasfasdf");
            }
        });
    }
    public static void main(String[] args){
        new test(new Characters());
    }
}
