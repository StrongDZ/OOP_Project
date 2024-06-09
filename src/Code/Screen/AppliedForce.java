package Code.Screen;

import Code.Object.Objectss;
import Code.Utils.ExceptionCase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AppliedForce extends JPanel {
    private JLabel label;
    private JSlider slider;
    private JTextField textField;
    MainScreen screen;
    boolean change=false;

    public AppliedForce(MainScreen screen) {
        this.screen= screen;
        setPreferredSize(new Dimension(450, 260));
        setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        GridBagConstraints gbc = new GridBagConstraints();

        // Tạo và thiết lập JLabel
        label = new JLabel("Applied force", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 60, 10);
        add(label, gbc);

        // Tạo và thiết lập JSlider
        slider = new JSlider(JSlider.HORIZONTAL, -500, 500, 0);
        slider.setPreferredSize(new Dimension(300,50));
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 30, 10);
        add(slider, gbc);

        // Tạo và thiết lập JTextField
        textField = new JTextField(7);
        textField.setText(0+"");
        textField.setPreferredSize(new Dimension(30, 30));
        textField.setEditable(true);
        textField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 50, 10);
        add(textField, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 50, 0);
        add(new JLabel("(N)"), gbc);

        // Đồng bộ giá trị của JTextField với giá trị của JSlider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
//                if (!slider.getValueIsAdjusting()){
                    textField.setText(String.valueOf(slider.getValue()));
                    change=true;
//                }
            }
        });

        // Đồng bộ giá trị của JSlider với giá trị của JTextField khi người dùng thay đổi trên JTextField
        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= -500 && value <= 500) {
                    slider.setValue(value);
                    change = true;
                } else {
                    // Nếu giá trị nhập vào không hợp lệ, hiển thị thông báo và đặt lại giá trị của JTextField
                    new ExceptionCase("Invalid value. Please enter a value between -500 and 500.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                // Nếu người dùng nhập vào không phải số, hiển thị thông báo và đặt lại giá trị của JTextField
                new ExceptionCase("Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }
    public void SyncAppliedForce() {
        if (!change) return;
        change = false;
        Objectss mainCharacter = screen.mainCharacter.mainCharacter;
        String text = textField.getText();
        if (text.matches("-?\\d+")) {
            int appliedForce = Integer.parseInt(text);
            mainCharacter.setAppliedForce(appliedForce);
        }
    }
    public void reset(){
        slider.setValue(0);
    }
}
