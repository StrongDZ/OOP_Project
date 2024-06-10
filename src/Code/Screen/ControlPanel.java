package Code.Screen;

import Code.HandDetection.HandDetection;
import Code.Object.Objectss;
import Code.Utils.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class ControlPanel extends JPanel {
    private JToggleButton playPauseButton, airModeButton, aiModeButton;
    private JButton restartButton;
    MainScreen screen;
    private HandDetection hand;
    private Timer aiTimer;
    private Thread handDetectionThread;

    public ControlPanel(MainScreen screen) {
        this.screen = screen;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(450, 40));

        playPauseButton = new JToggleButton("Pause");
        playPauseButton.addActionListener(e -> {
            if (screen.mainCharacter.mainCharacter != null) {
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

        airModeButton = new JToggleButton("Air Mode");
        airModeButton.addActionListener(e -> {
            if (airModeButton.isSelected()) {
                airModeButton.setText("Void Mode");
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

        aiModeButton = new JToggleButton("AI Mode");
        aiModeButton.addActionListener(e -> {
            if (aiModeButton.isSelected()) {
                hand = new HandDetection();
                handDetectionThread = new Thread(hand);
                handDetectionThread.start();
                startAI();
                System.out.println("AI Mode activated");
            } else {
                stopAI();
            }
        });

        add(aiModeButton);
        add(Box.createGlue());
        setOpaque(false);
    }

    public void startAI() {
        System.out.println("AI Mode activated");
        aiTimer = new javax.swing.Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hand != null) {
                    screen.appliedForce.setValue(-2*hand.getX_out());
                }
            }
        });
        aiTimer.start();
    }

    public void stopAI() {
        if (aiTimer != null) {
            aiTimer.stop();
        }
        if (hand != null) {
            hand.closeWindow();
            handDetectionThread.interrupt();
            hand = null;
        }
        System.out.println("AI Mode deactivated");
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel(new MainScreen("faf"));
        new test(controlPanel);
    }
}

