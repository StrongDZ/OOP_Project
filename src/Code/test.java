package Code;

import Code.Actor.Square;
import Code.Screen.MainCharacter;
import Code.Screen.Showparameters;

import javax.swing.*;

public class test {
    public test(JPanel zui){
        JFrame haha = new JFrame();
        haha.add(zui);

        haha.revalidate();
        haha.repaint();
//        haha.setSize(800,800);
        haha.pack();
        haha.setVisible(true);
    }
    public static void main(String[] args){
        float a =0.0f;
        if(a==0)System.out.println("hahaha");
//        new test(new menuofparameters());
    }
}

