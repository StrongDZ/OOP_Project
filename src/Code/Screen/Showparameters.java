package Code.Screen;

//import javafx.*;


import Code.Actor.Objectss;

import javax.swing.*;
import java.awt.*;

public class Showparameters extends JPanel {
    protected float angular_position=0, velocity=0, angular_velocity=0, acceleration=0, position=0, angualr_acceleration=0;
    JLabel langular_velocity, lvelocity, langular_position, lacceleration, lposition, langualr_acceleration;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    public Showparameters(){

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
//        new test(this);
    }
    public void start(){
        langualr_acceleration=setLabel("Angular acceleration");
        langular_velocity=setLabel("Angular velocity");
        langular_position=setLabel("Angular position");
        lposition=setLabel("Position");
        lacceleration=setLabel("Acceleration");
        lvelocity=setLabel("Velocity");
        this.stateAcceleration(false);
        this.stateVelocity(false);
        this.statePosition(false);
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

    public void Update(Objectss mainChar){
        langular_velocity.setText("<html><div style='text-align: center;'>Angular velocity <br>" +  String.format("%.2f",mainChar.getOmega()) +" (rad/s)</div></html>");
        langualr_acceleration.setText("<html><div style='text-align: center;'>Angular acceleration<br>" +  String.format("%.2f",mainChar.getGamma()) +" (rad/s^2)</div></html>");
        langular_position.setText("<html><div style='text-align: center;'>Angular position<br>" +  String.format("%.2f",mainChar.getTheta()) +" (*)</div></html>");
        lacceleration.setText("<html><div style='text-align: center;'>Acceleration<br>" +  String.format("%.2f",mainChar.getAcceleration()) +" (m/s^2)</div></html>");
        lvelocity.setText("<html><div style='text-align: center;'>Velocity<br>" +  String.format("%.2f",mainChar.getSpeed()) +" (m/s)</div></html>");
        lposition.setText("<html><div style='text-align: center;'>Position<br>" +  String.format("%.2f",mainChar.getPosition()) +" (m)</div></html>");
    }

    public void statePosition(boolean zui){
        lposition.setVisible(zui);
        langular_position.setVisible(zui);
    }

    public void stateVelocity(boolean zui){
        lvelocity.setVisible(zui);
        langular_velocity.setVisible(zui);
    }

    public void stateAcceleration(boolean zui){
        lacceleration.setVisible(zui);
        langualr_acceleration.setVisible(zui);
    }
    public static void main(String[] args){
//        new test((JPanel)new Showparameters());
    }

}
