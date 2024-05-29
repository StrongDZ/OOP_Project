package Code.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueInput{
    public int mass;
    public int length;
    public ValueInput(String s){
        char[] s1 = s.toCharArray();
        s1[0] = Character.toUpperCase(s1[0]);
        String sk = new String(s1);

        JDialog dialog = new JDialog((Frame) null, "Input Values", true);
        dialog.setLocation(600,300);
        dialog.setSize(400, 150);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel massLabel = new JLabel("Mass (10 < mass < 50): ");
        JTextField massField = new JTextField();
        massLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lengthLabel = new JLabel(sk + " (100 < " + s + " < 230): ");
        JTextField lengthField = new JTextField();
        lengthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton submitButton = new JButton("Submit");

        dialog.add(massLabel);
        dialog.add(massField);
        dialog.add(lengthLabel);
        dialog.add(lengthField);
        dialog.add(submitButton);

        final boolean[] validInput = {false};

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int mass1 = Integer.parseInt(massField.getText());
                    int length1 = Integer.parseInt(lengthField.getText());

                    if (mass1 < 10 || mass1 > 50) {
                        JOptionPane.showMessageDialog(dialog, "Mass must be greater than 10 and less than 50.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (length1 < 100 || length1 > 230){
                        JOptionPane.showMessageDialog(dialog, sk + " must be greater than 100 and less than 230.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    mass = mass1;
                    length = length1;
                    validInput[0] = true;
                    dialog.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                massField.setText("");
                lengthField.setText("");
            }
        });

    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
    }

}

