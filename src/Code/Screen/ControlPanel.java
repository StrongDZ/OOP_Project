package Code.Screen;

import Code.Object.Circle;
import Code.Object.Objectss;
import Code.Utils.test;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JToggleButton playPauseButton;
    private JButton restartButton;
    MainScreen screen;
    public ControlPanel(MainScreen screen) {
        this.screen=screen;
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(400,40));

        playPauseButton = new JToggleButton("Pause");
        playPauseButton.addActionListener(e -> {
            if (playPauseButton.isSelected()) {
                playPauseButton.setText("Play");
                screen.stopTimer();
                screen.movingground.stopTimer();

            } else {
                playPauseButton.setText("Pause");
                screen.startTimer();
                screen.movingground.startTimer();

            }
        });
        add(Box.createGlue());
        add(playPauseButton);
        add(Box.createRigidArea(new Dimension(50,0)));
        // Tạo và thêm nút JButton
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            Objectss main = screen.mainCharacter.mainCharacter;
            if(main !=null){
                if(main instanceof Circle){
                    main.resetObjectss();
                }
                else main.resetObjectss();
                screen.appliedForce.reset();
                screen.frictionCoeficient.reset();
                screen.mainCharacter.reset();
            }
        });
        add(restartButton);
        add(Box.createGlue());
        setOpaque(false);
    }
    public static void main(String[]args){
        ControlPanel controlPanel = new ControlPanel(new MainScreen("faf"));
        new test(controlPanel);
    }
}