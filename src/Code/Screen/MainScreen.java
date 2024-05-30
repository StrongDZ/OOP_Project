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
    MovingImagePanel ground = new MovingImagePanel("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background.jpg");
//    MovingImagePanel ground1 = new MovingImagePanel("D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background1.jpg");

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
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.insets = new Insets(70,20,20,25);
        addComponent(panel, appliedForce, 2, 1,1,1);
        gbc.insets = new Insets(70,25,20,20);
        addComponent(panel, characters,2,0,1,1);
        gbc.insets = new Insets(70,20,20,26);
        addComponent(panel, frictionCoeficient, 2,2,1,1);

        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.BOTH;
//        ground.setPreferredSize(new Dimension(1000, 200));
        addComponent(panel, ground, 2,0,1,3);

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
                appliedForce.SyncAppliedForce();
                frictionCoeficient.SyncFriction();
                Objectss mainChar = mainCharacter.mainCharacter;
                if(mainChar instanceof Square){
                    mainChar.calculateFriction(mainChar.getAppliedForce(),mainChar.getStaticfric(),mainChar.getKineticfric());
                    mainChar.updateObject(mainChar.calculateAcceleration());

//                    System.out.println("hah" + mainChar.getAcceleration() +" "+mainChar.getFriction());
                    showparameters.Update(mainChar);
                }
                if(mainChar instanceof Circle){
                    mainChar.calculateFriction(mainChar.getAppliedForce(),mainChar.getStaticfric(),mainChar.getKineticfric());
//                    System.out.println("hah" + mainChar.getAppliedForce() +" "+mainChar.getFriction()+ " "+mainChar.getSpeed());
                    showparameters.Update(mainChar);
                    ((Circle)mainChar).updateObject(mainChar.calculateAcceleration(),
                            ((Circle)mainChar).calculateGamma(mainChar.getFriction(),mainChar.getMass(),mainChar.getSide()));
                }
            }
        };

        long delay  = 0;  // 0 giây
        long period = 10;  // 0.01 giây

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