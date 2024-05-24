package Code.Screen;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame{
    GridBagConstraints gbc = new GridBagConstraints();;
    GridBagLayout gb = new GridBagLayout();
    Showparameters showparameters = new Showparameters();
    Menuofparameters menuofparameters = new Menuofparameters();
    Characters characters = new Characters();
    public MainScreen(String title){
        setting(title);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(showparameters,0,0,1,2);
        addComponent(menuofparameters,0,2,1,1);
        addComponent(characters,1,0,1,1);
//        addComponent(showparameters(),0,0,10,5);

    }

    public void setting(String title){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setLocation(0,0);
        setPreferredSize(new Dimension(1920, 1080));
//        setSize(900,700);
        setLayout(gb);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một JLabel để chứa ảnh
        ImageIcon imageIcon = new ImageIcon(Objects.class.getResource("Background.jpg"));
        JLabel backgroundLabel = new JLabel(imageIcon);

        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());

        // Thêm JLabel vào JFrame
        addComponent(backgroundLabel,0,0,1,1);

        // Đảm bảo rằng JLabel sẽ được vẽ sau tất cả các thành phần khác
//        setContentPane(backgroundLabel);
    }
    public static void main(String[] args){
        MainScreen screen = new MainScreen("Forces");
        screen.revalidate();
        screen.repaint();
        screen.pack();
        screen.setVisible(true);
    }
    public void addComponent(Component c, int row, int col, int nrow, int ncol)
    {
        gbc.gridx=col;
        gbc.gridy=row;

        gbc.gridwidth=ncol;
        gbc.gridheight=nrow;
        gbc.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(c,gbc);

        add(c);
    }
}
