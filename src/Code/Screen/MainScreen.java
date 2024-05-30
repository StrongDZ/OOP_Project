package Code.Screen;


import Code.Actor.Circle;
import Code.Actor.Objectss;
import Code.Actor.Square;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreen extends JFrame{
    GridBagConstraints gbc = new GridBagConstraints();;
    GridBagLayout gb = new GridBagLayout();
    Showparameters showparameters = new Showparameters();
    Menuofparameters menuofparameters = new Menuofparameters(this);
    Characters characters = new Characters(this);
    AppliedForce appliedForce = new AppliedForce(this);
    FrictionCoeficient frictionCoeficient = new FrictionCoeficient(this);

    MainCharacter mainCharacter = new MainCharacter();
    BackgroundPanel ground = new BackgroundPanel(new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background.jpg").getImage(),0,-250);
//    MovingImagePanel ground1 = new MovingImagePanel("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background1.jpg");
    MovingImagePanel movingground = new MovingImagePanel("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background.jpg");
    public MainScreen(String title){
        setting(title);
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background1.jpg").getImage(),0,-150);
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setLayout(gb);

        //Hàng đầu
        gbc.insets = new Insets(10,10,30,30);
        addComponent(panel, showparameters,0,0,1,2);

        gbc.insets = new Insets(10,30,30,10);
        addComponent(panel, menuofparameters,0,2,1,1);

        //Hàng cuối
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        movingground.setPreferredSize(new Dimension(1200, 40));
//        new test(ground)
        addComponent(panel, movingground, 2,0,1,3);
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.insets = new Insets(20,20,10,25);
        addComponent(panel, appliedForce, 3, 1,1,1);
        gbc.insets = new Insets(20,25,10,20);
        addComponent(panel, characters,3,0,1,1);
        gbc.insets = new Insets(20,20,10,26);
        addComponent(panel, frictionCoeficient, 3,2,1,1);

        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ground.setPreferredSize(new Dimension(1200, 300));
        addComponent(panel, ground, 3,0,1,3);

        //Hàng giữa
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(15,0,0,0);
        addComponent(panel, mainCharacter, 1,0,1,3);

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
        screen.Synchronization();
        screen.setVisible(true);

    }

    public void Synchronization(){
        Timer timer = new Timer();
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
//                System.out.println("Task executed");
                if(mainCharacter.mainCharacter == null)return;
//                System.out.println("agjbajgb");
                appliedForce.SyncAppliedForce();
                frictionCoeficient.SyncFriction();
                Objectss mainChar = mainCharacter.mainCharacter;
                if(mainChar instanceof Square||mainChar instanceof Circle) {
                    mainChar.calculateFriction(mainChar.getAppliedForce(), mainChar.getStaticfric(), mainChar.getKineticfric());
                    if (mainChar instanceof Square) mainChar.updateObject(mainChar.calculateAcceleration());
                    else{
                        ((Circle) mainChar).updateObject(mainChar.calculateAcceleration(),
                                ((Circle) mainChar).calculateGamma(mainChar.getFriction(), mainChar.getMass(), mainChar.getSide()));
                    }
                    showparameters.Update(mainChar);
                    int speed = Math.abs((int)mainChar.getSpeed());
                    if(speed == 0 ) movingground.stopTimer();
                    else movingground.setTimerInterval((int)(300/speed));
                }
                mainCharacter.updateForce();
            }
        };

        long delay  = 0;  // 0 giây
        long period = 10;  // 0.05 giây

        timer.scheduleAtFixedRate(repeatedTask, delay, period);
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