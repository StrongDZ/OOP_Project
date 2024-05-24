package Code.Screen;

import Code.test;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainScreen extends JFrame{
    GridBagConstraints gbc = new GridBagConstraints();;
    GridBagLayout gb = new GridBagLayout();
    Showparameters showparameters = new Showparameters();
    Menuofparameters menuofparameters = new Menuofparameters();
    Characters characters = new Characters();
    AppliedForce appliedForce = new AppliedForce();
    BackgroundPanel panel;
    public MainScreen(String title){
        setting(title);
        panel = new BackgroundPanel(new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background.jpg").getImage());
//        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setLayout(gb);
        gbc.insets = new Insets(10,10,10,30);
        addComponent(panel, showparameters,0,0,1,2);
        gbc.insets = new Insets(10,30,10,10);
        addComponent(panel, menuofparameters,0,2,1,1);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10,10,10,0);
        addComponent(panel, characters,1,0,1,1);
        addComponent(panel, appliedForce, 1, 1,1,1);
        panel.revalidate();
        panel.repaint();
        add(panel);
//        new test(panel);
//        addComponent(showparameters(),0,0,10,5);

    }

    public void setting(String title){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setLocation(0,0);
        setPreferredSize(new Dimension(1920, 1080));
//        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addComponent(JPanel zui, Component c, int row, int col, int nrow, int ncol)
    {
        gbc.gridx=col;
        gbc.gridy=row;

        gbc.gridwidth=ncol;
        gbc.gridheight=nrow;
//        gbc.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(c,gbc);

        zui.add(c);
    }
    public static void main(String[] args){
        MainScreen screen = new MainScreen("Forces");
        screen.revalidate();
        screen.repaint();
        screen.pack();
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
            // Vẽ ảnh nền
            g.drawImage(backgroundImage, 0, 0, 1920, 1080, this);
        }
    }
}