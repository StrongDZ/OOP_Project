package Code.Screen;

import Code.Actor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    MainCharacter mainCharacter = new MainCharacter();
    Showparameters showparameters = new Showparameters();
    Menuofparameters menuofparameters = new Menuofparameters(this);
    Characters characters = new Characters(this);
    AppliedForce appliedForce = new AppliedForce(this);
    FrictionCoeficient frictionCoeficient = new FrictionCoeficient(this);
    ControlPanel controlPanel = new ControlPanel(this);

    BackgroundPanel ground = new BackgroundPanel(new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\LinhTinh\\Background.jpg").getImage(),0,-250);
    MovingImagePanel movingground = new MovingImagePanel("D:\\Java\\OOP_Project\\src\\Code\\LinhTinh\\Background.jpg");
    Timer timer;

    public MainScreen(String title) {
        setting(title);
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\LinhTinh\\Background1.jpg").getImage(),0,-150);
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setLayout(gb);
        //Hàng 1
        gbc.insets = new Insets(10,10,30,30);
        addComponent(panel, showparameters,0,0,1,2);

        gbc.insets = new Insets(10,30,30,10);
        addComponent(panel, menuofparameters,0,2,1,1);
        //Hàng 2

        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.NORTH;
        addComponent(panel,controlPanel,2,2,1,1);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(panel, mainCharacter, 2,0,1,3);
        //Hàng 3
        gbc.fill = GridBagConstraints.HORIZONTAL;
        movingground.setPreferredSize(new Dimension(1200, 40));
        addComponent(panel, movingground, 3,0,1,3);
        gbc.anchor = GridBagConstraints.SOUTH;

        //Hàng 4
        gbc.insets = new Insets(20,20,10,25);
        addComponent(panel, appliedForce, 4, 1,1,1);
        gbc.insets = new Insets(20,25,10,20);
        addComponent(panel, characters,4,0,1,1);
        gbc.insets = new Insets(20,20,10,26);
        addComponent(panel, frictionCoeficient, 4,2,1,1);

        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ground.setPreferredSize(new Dimension(1200, 300));
        addComponent(panel, ground, 4,0,1,3);


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

    public void addComponent(JPanel zui, Component c, int row, int col, int nrow, int ncol) {
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
        screen.Synchronization();
        screen.setVisible(true);
    }

    public void Synchronization(){
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mainCharacter.mainCharacter == null) return;

                appliedForce.SyncAppliedForce();
                frictionCoeficient.SyncFriction();
                Objectss mainChar = mainCharacter.mainCharacter;

                if(mainChar instanceof Square || mainChar instanceof Circle) {
                    mainChar.calculateFriction(mainChar.getAppliedForce(), mainChar.getStaticfric(), mainChar.getKineticfric());
                    if (mainChar instanceof Square)
                        mainChar.updateObject(mainChar.calculateAcceleration());
                    else{
                        ((Circle) mainChar).updateObject(mainChar.calculateAcceleration(),
                                ((Circle) mainChar).calculateGamma(mainChar.getFriction(), mainChar.getMass(), mainChar.getSide()));
                        ((Circle)mainChar).rotateImage(mainChar.getPosition()/5);
                    }
                    showparameters.Update(mainChar);
                    movingground.adjust((int)mainChar.getSpeed());
                }
                mainCharacter.updateForce();
            }
        });
        timer.start();
    }

    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void startTimer() {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    int x,y;
    public BackgroundPanel(Image backgroundImage,int x, int y) {
        this.backgroundImage = backgroundImage;
        this.x=x;
        this.y=y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, x, y, 1920, 1080, this);
        }
    }
}
