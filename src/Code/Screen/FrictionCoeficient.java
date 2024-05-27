package Code.Screen;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
public class FrictionCoeficient extends JPanel{
    public FrictionCoeficient(){
        setLayout(new GridLayout(2, 1));

        StaticFric staticFricPanel = new StaticFric();
        KineticFric kineticFricPanel = new KineticFric();

        add(staticFricPanel);
        add(kineticFricPanel);
    }
}

class StaticFric extends JPanel {
    private JLabel label;
    private JSlider slider;
    private JTextField textField;

    public StaticFric() {
        setPreferredSize(new Dimension(450, 150));
        setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        GridBagConstraints gbc = new GridBagConstraints();

        // Tạo và thiết lập JLabel
        label = new JLabel("Static Friction", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 30, 10);
        add(label, gbc);

        // Tạo và thiết lập JSlider
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

        // Tạo và thiết lập JTextField
        textField = new JTextField(5);
        textField.setText(0+"");
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
                if (value >= 0 && value <= 10) {
                    slider.setValue(value);
                } else {
                    // Nếu giá trị nhập vào không hợp lệ, hiển thị thông báo và đặt lại giá trị của JTextField
                    JOptionPane.showMessageDialog(null, "Invalid value. Please enter a value between 0 and 10.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                // Nếu người dùng nhập vào không phải số, hiển thị thông báo và đặt lại giá trị của JTextField
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }
}
class KineticFric extends JPanel {
    private JLabel label;
    private JSlider slider;
    private JTextField textField;

    public KineticFric() {
        setPreferredSize(new Dimension(450, 150));
        setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        GridBagConstraints gbc = new GridBagConstraints();

        // Tạo và thiết lập JLabel
        label = new JLabel("Kinetic Friction", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 30, 10);
        add(label, gbc);

        // Tạo và thiết lập JSlider
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

        // Tạo và thiết lập JTextField
        textField = new JTextField(5);
        textField.setText(0+"");
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
                if (value >= 0 && value <= 10) {
                    slider.setValue(value);
                } else {
                    // Nếu giá trị nhập vào không hợp lệ, hiển thị thông báo và đặt lại giá trị của JTextField
                    JOptionPane.showMessageDialog(null, "Invalid value. Please enter a value between 0 and 10.");
                    textField.setText(String.valueOf(slider.getValue()));
                }
            } catch (NumberFormatException ex) {
                // Nếu người dùng nhập vào không phải số, hiển thị thông báo và đặt lại giá trị của JTextField
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                textField.setText(String.valueOf(slider.getValue()));
            }
        });
    }
}

