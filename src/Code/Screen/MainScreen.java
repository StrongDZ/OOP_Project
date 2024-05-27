package Code.Screen;

import Code.Actor.Square;
import Code.test;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainScreen extends JFrame{
    GridBagConstraints gbc = new GridBagConstraints();;
    GridBagLayout gb = new GridBagLayout();
    Showparameters showparameters = new Showparameters();
    Menuofparameters menuofparameters = new Menuofparameters(this);
    Characters characters = new Characters();
    AppliedForce appliedForce = new AppliedForce();
    FrictionCoeficient frictionCoeficient = new FrictionCoeficient();
    BackgroundPanel panel;

    public MainScreen(String title){
        setting(title);
        panel = new BackgroundPanel(new ImageIcon("src/Code/Screen/Background1.jpg").getImage());
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setLayout(gb);

        //Hàng đầu
        gbc.insets = new Insets(10,10,30,30);
        addComponent(panel, showparameters,0,0,1,2);

        gbc.insets = new Insets(10,30,30,10);
        addComponent(panel, menuofparameters,0,2,1,1);

        //Hàng cuối
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.insets = new Insets(70,20,20,25);
        addComponent(panel, appliedForce, 2, 1,1,1);
        gbc.insets = new Insets(70,20,20,26);
        addComponent(panel, frictionCoeficient, 2,2,1,1);
        gbc.insets = new Insets(70,25,20,20);
        addComponent(panel, characters,2,0,1,1);

        //Hàng giữa
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel panel1 = new BackgroundPanel(new ImageIcon("/Users/macbookpro/Documents/OOP_Project-2/src/Code/Screen/Background.jpg").getImage());
//        panel1.setPreferredSize(new Dimension(1000, 300));
        addComponent(panel, panel1, 2,0,1,3);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(10,0,0,0);
        Square square = new Square(180,180);
        addComponent(panel, square, 1,1,1,1);

        panel.revalidate();
        panel.repaint();
        add(panel);

    }

    public void setting(String title){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setLocation(0,0);
        setPreferredSize(new Dimension(1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addComponent(JPanel zui, Component c, int row, int col, int nrow, int ncol)
    {
        gbc.gridx=col;
        gbc.gridy=row;

        gbc.gridwidth=ncol;
        gbc.gridheight=nrow;

        gb.setConstraints(c,gbc);

        zui.add(c);
    }
    public static void main(String[] args){
        MainScreen screen = new MainScreen("Forces");
        screen.revalidate();
        screen.repaint();
        screen.pack();
        screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screen.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1920, 1080, this);
        }
    }
}