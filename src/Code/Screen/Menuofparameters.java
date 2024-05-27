package Code.Screen;

import Code.test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menuofparameters extends JPanel {
    JToggleButton bforce, bposition, bvelocity, bacceleration, bmass, bsum;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    JLabel name = new JLabel("Menu of parameters");
    MainScreen screen;
    public Menuofparameters(MainScreen screen) {
        this.screen = screen;
        setLayout(gb);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setPreferredSize(new Dimension(400,200));

        start();

        // Set up GridBagConstraints for the title label
        name.setPreferredSize(new Dimension(150, 30));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.PLAIN, 20));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,20,0);
        addComponent(name, 0, 0, 1, 3);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        addComponent(bforce, 1, 0, 1, 1);
        addComponent(bsum, 1, 1, 1, 1);
        addComponent(bposition, 1, 2, 1, 1);

        addComponent(bvelocity, 2, 0, 1, 1);
        addComponent(bmass, 2, 1, 1, 1);
        addComponent(bacceleration, 2, 2, 1, 1);
    }

    public void start() {
        bforce = setToggle("Force");
        bposition = setToggle("Position");
        bvelocity = setToggle("Velocity");
        bacceleration = setToggle("Acceleration");
        bmass = setToggle("Mass");
        bsum = setToggle("Sum of force");

        bposition.addActionListener(new ActionListener() {
//            JFrame screen1 = screen;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bposition.isSelected()){
                    screen.showparameters.statePosition(false);
                }
                else screen.showparameters.statePosition(true);
            }
        });

        bvelocity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bvelocity.isSelected()){
                    screen.showparameters.stateVelocity(false);
                }
                else screen.showparameters.stateVelocity(true);
            }
        });

        bacceleration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bacceleration.isSelected()){
                    screen.showparameters.stateAcceleration(false);
                }
                else screen.showparameters.stateAcceleration(true);
            }
        });
    }

    public JToggleButton setToggle(String s) {
        JToggleButton label = new JToggleButton(s);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150, 30));
        return label;
    }

    public void addComponent(Component c, int row, int col, int nrow, int ncol) {
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = ncol;
        gbc.gridheight = nrow;

        gb.setConstraints(c, gbc);
        add(c);
    }
}
