package Code.Screen;

import Code.Object.Circle;
import Code.Object.Objectss;
import Code.Utils.test;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JToggleButton playPauseButton,airModeButton,aiModeButton;;
    private JButton restartButton;
    MainScreen screen;

    public ControlPanel(MainScreen screen) {
        this.screen = screen;
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(450, 40));

        playPauseButton = new JToggleButton("Pause");
        playPauseButton.addActionListener(e -> {
            if(screen.mainCharacter.mainCharacter !=null){
                if (playPauseButton.isSelected()) {
                    screen.stopTimer();
                    screen.movingground.stopTimer();
                } else {
                    screen.startTimer();
                    screen.movingground.startTimer();
                }
            }
            if (playPauseButton.isSelected()) playPauseButton.setText("Play");
            else playPauseButton.setText("Pause");
        });

        add(Box.createGlue());
        add(playPauseButton);
        add(Box.createRigidArea(new Dimension(30, 0)));

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            Objectss main = screen.mainCharacter.mainCharacter;
            if (main != null) {
                main.resetObjectss();
                screen.appliedForce.reset();
                screen.frictionCoeficient.reset();
                screen.mainCharacter.reset();
            }
        });

        add(restartButton);
        add(Box.createRigidArea(new Dimension(30, 0)));

        // Add Air Mode button
        airModeButton = new JToggleButton("Air Mode");
        airModeButton.addActionListener(e -> {
            if (airModeButton.isSelected()) {
                airModeButton.setText("Normal Mode");
                screen.air.setVisible(true);
                screen.air.startTimers();
            } else {
                airModeButton.setText("Air Mode");
                screen.air.setVisible(false);
                screen.air.stopTimers();
            }
        });

        add(airModeButton);
        add(Box.createRigidArea(new Dimension(30, 0)));

        // Add AI Mode button
        aiModeButton = new JToggleButton("AI Mode");
        aiModeButton.addActionListener(e -> {
            // Implement AI Mode functionality here
            System.out.println("AI Mode activated");
        });

        add(aiModeButton);
        add(Box.createGlue());
        setOpaque(false);
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel(new MainScreen("faf"));
        new test(controlPanel);
    }
}
