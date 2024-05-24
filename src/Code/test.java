package Code;

import Code.Screen.Showparameters;

import javax.swing.*;

public class test {
    public test(JPanel zui){
        JFrame haha = new JFrame();
        haha.add(zui);

        haha.revalidate();
        haha.repaint();
        haha.setSize(800,800);
        haha.pack();
        haha.setVisible(true);
    }
    public static void main(String[] args){
        new test(new Showparameters());
//        new test(new menuofparameters());
    }
}

