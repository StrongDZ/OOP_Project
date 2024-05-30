package Code.Screen;
import Code.Actor.Objectss;
import Code.LinhTinh.ExceptionCase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FrictionCoeficient extends JPanel {
    StaticFric staticFricPanel = new StaticFric();
    KineticFric kineticFricPanel = new KineticFric();
    MainScreen screen;

    public FrictionCoeficient(MainScreen screen) {
        this.screen = screen;
        setLayout(new GridLayout(2, 1));

        add(staticFricPanel);
        add(kineticFricPanel);

        // Synchronize values between panels
        staticFricPanel.setKineticPanel(kineticFricPanel);
        kineticFricPanel.setStaticPanel(staticFricPanel);
    }
    public void SyncFriction(){
        if(staticFricPanel.change || kineticFricPanel.change) {
            staticFricPanel.change=false;
            kineticFricPanel.change=false;
            Objectss mainCharacter = screen.mainCharacter.mainCharacter;
            float staticfric = Float.parseFloat(staticFricPanel.textField.getText()),
                    kineticfric = Float.parseFloat(kineticFricPanel.textField.getText());
            mainCharacter.setStaticfric((float)(staticfric * 0.1));
            mainCharacter.setKineticfric((float) (kineticfric * 0.1));
        }
    }
    public void reset(){
        kineticFricPanel.slider.setValue(0);
        staticFricPanel.slider.setValue(1);

    }
}

class StaticFric extends JPanel {
    private JLabel label;
    protected JSlider slider;
    protected JTextField textField;
    private KineticFric kineticFricPanel;
    boolean change=false;

    public StaticFric() {
        setPreferredSize(new Dimension(450, 130));
        setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        GridBagConstraints gbc = new GridBagConstraints();

        label = new JLabel("Static Friction", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 30, 10);
        add(label, gbc);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        slider.setPreferredSize(new Dimension(300, 50));
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 15, 10);
        add(slider, gbc);

        textField = new JTextField(5);
        textField.setText(0 + "");
        textField.setPreferredSize(new Dimension(30, 30));
        textField.setEditable(true);
        textField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 30, 10);
        add(textField, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(new JLabel("*0.1"), gbc);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!slider.getValueIsAdjusting()) {
                    if (kineticFricPanel != null && slider.getValue() <= kineticFricPanel.slider.getValue()) {
                        slider.setValue(Integer.parseInt(textField.getText()));
                        new ExceptionCase("Static friction must be greater than kinetic friction.");
                    } else {
                        textField.setText(String.valueOf(slider.getValue()));
                        change=true;

                    }
                }
            }
        });

        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= 0 && value <= 10) {
                    if (kineticFricPanel != null && value <= kineticFricPanel.slider.getValue()) {
                        textField.setText(String.valueOf(slider.getValue()));
                        new ExceptionCase("Static friction must be greater than kinetic friction.");
                    } else {
                        slider.setValue(value);
                        change=true;
                    }
                } else {
                    new ExceptionCase("Invalid value. Please enter a value between 0 and 10.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                new ExceptionCase("Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }

    public void setKineticPanel(KineticFric kineticFricPanel) {
        this.kineticFricPanel = kineticFricPanel;
    }
}

class KineticFric extends JPanel {
    private JLabel label;
    protected JSlider slider;
    protected JTextField textField;
    private StaticFric staticFricPanel;
    boolean change=false;

    public KineticFric() {
        setPreferredSize(new Dimension(450, 130));
        setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        GridBagConstraints gbc = new GridBagConstraints();

        label = new JLabel("Kinetic Friction", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 30, 10);
        add(label, gbc);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        slider.setPreferredSize(new Dimension(300, 50));
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 15, 10);
        add(slider, gbc);

        textField = new JTextField(5);
        textField.setText(0 + "");
        textField.setPreferredSize(new Dimension(30, 30));
        textField.setEditable(true);
        textField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 30, 10);
        add(textField, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(new JLabel("*0.1"), gbc);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!slider.getValueIsAdjusting()) {
                    if (staticFricPanel != null && slider.getValue() >= (float)staticFricPanel.slider.getValue()) {
                        slider.setValue(Integer.parseInt(textField.getText()));
                        new ExceptionCase("Kinetic friction must be less than static friction.");
                    } else {
                        textField.setText(String.valueOf(slider.getValue()));
                        change=true;
                    }
                }
            }
        });

        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= 0 && value <= 10) {
                    if (staticFricPanel != null && value >= (float)staticFricPanel.slider.getValue()) {
                        textField.setText(String.valueOf(slider.getValue()));
                        new ExceptionCase("Kinetic friction must be less than static friction.");
                    } else {
                        slider.setValue(value);
                        change=true;
                    }
                } else {
                    new ExceptionCase("Invalid value. Please enter a value between 0 and 10.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                new ExceptionCase("Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }

    public void setStaticPanel(StaticFric staticFricPanel) {
        this.staticFricPanel = staticFricPanel;
    }
}
