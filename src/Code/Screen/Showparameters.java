package Code.Screen;

//import javafx.*;


import Code.test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Showparameters extends JPanel {
    protected float angular_position=0, velocity=0, angular_velocity=0, acceleration=0, position=0, angualr_acceleration=0;
    JLabel langular_velocity, lvelocity, langular_position, lacceleration, lposition, langualr_acceleration;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    public Showparameters(){
//        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
//        setBorder(border);
        setLayout(gb);
        setPreferredSize(new Dimension(800,200));
        setOpaque(false);
//        setSize(800,200);
        gbc = new GridBagConstraints();
        start();
        Insets inset = new Insets(10,10,10,10);
        gbc.insets = inset;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        addComponent(lposition,0,0,1,1);
        addComponent(lvelocity,0,1,1,1);
        addComponent(lacceleration,0,2,1,1);
        addComponent(langular_position,2,0,1,1);
        addComponent(langular_velocity,2,1,1,1);
        addComponent(langualr_acceleration,2,2,1,1);
        Update();
//        new test(this);
    }
    public void start(){
        langualr_acceleration=setLabel("Angular acceleration");
        langular_velocity=setLabel("Angular velocity");
        langular_position=setLabel("Angular position");
        lposition=setLabel("Position");
        lacceleration=setLabel("Acceleration");
        lvelocity=setLabel("Velocity");
    }
    public JLabel setLabel(String s){
        JLabel label = new JLabel(s);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200,50));
        return label;
    }
    public void addComponent(Component c, int row, int col, int nrow, int ncol){
        gbc.gridx = col;
        gbc.gridy = row;

        gbc.gridwidth = ncol;
        gbc.gridheight = nrow;
//        Insets inset = new Insets(10,10,10,10);
//        gbc.insets = inset;
        gb.setConstraints(c, gbc);
        add(c);
    }

    public void Update(){
        langular_velocity.setText("<html><div style='text-align: center;'>Angular velocity <br>" + angular_velocity +" (rad/s)</div></html>");
        langualr_acceleration.setText("<html><div style='text-align: center;'>Angular acceleration<br>" + angualr_acceleration +" (rad/s^2)</div></html>");
        langular_position.setText("<html><div style='text-align: center;'>Angular position<br>" + angular_position +" (*)</div></html>");
        lacceleration.setText("<html><div style='text-align: center;'>Acceleration<br>" + acceleration +" (m/s^2)</div></html>");
        lvelocity.setText("<html><div style='text-align: center;'>Velocity<br>" + velocity +" (m/s)</div></html>");
        lposition.setText("<html><div style='text-align: center;'>Position<br>" + position +" (m)</div></html>");
    }
    public static void main(String[] args){
        new test((JPanel)new Showparameters());
    }

}
