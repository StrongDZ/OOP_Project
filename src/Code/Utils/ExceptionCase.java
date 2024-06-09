package Code.Utils;

import javax.swing.*;

public class ExceptionCase extends JFrame{
    public ExceptionCase(String s){
        JFrame haha = new JFrame();
        JPanel zui = new JPanel();
        setTitle("ERROR!");
        zui.add(new JLabel(s));
        haha.add(zui);
        haha.setLocation(650,350);
        haha.revalidate();
        haha.repaint();
//        haha.setSize(800,800);
        haha.pack();
        haha.setVisible(true);
    }
}

