package Code.Screen;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AppliedForce extends JPanel {
    private JLabel label;
    private JSlider slider;
    private JTextField textField;

    public AppliedForce() {
        setPreferredSize(new Dimension(500, 200));
        setLayout(new GridBagLayout());
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
        slider.setPreferredSize(new Dimension(250,50));
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
        textField = new JTextField(5);
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

        // Đồng bộ giá trị của JTextField với giá trị của JSlider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField.setText(String.valueOf(slider.getValue()));
            }
        });

        // Đồng bộ giá trị của JSlider với giá trị của JTextField khi người dùng thay đổi trên JTextField
        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                if (value >= -500 && value <= 500) {
                    slider.setValue(value);
                } else {
                    // Nếu giá trị nhập vào không hợp lệ, hiển thị thông báo và đặt lại giá trị của JTextField
                    JOptionPane.showMessageDialog(null, "Invalid value. Please enter a value between -500 and 500.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                // Nếu người dùng nhập vào không phải số, hiển thị thông báo và đặt lại giá trị của JTextField
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Applied Force");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AppliedForce appliedForce = new AppliedForce();
        frame.getContentPane().add(appliedForce);

        frame.pack();
        frame.setVisible(true);
    }
}
