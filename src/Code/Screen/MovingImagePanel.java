package Code.Screen;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingImagePanel extends JPanel implements ActionListener {
    private Image image;
    private int x1, x2, y;
    private int imageWidth, imageHeight;
    private int step = 5; // Bước di chuyển của ảnh
    private Timer timer;

    public MovingImagePanel(String s) {
        // Tải ảnh từ file
        ImageIcon icon = new ImageIcon(s);
        image = icon.getImage();
        imageWidth = image.getWidth(this);
        imageHeight = image.getHeight(this);

        x1 = -imageWidth;
        x2 = x1 + imageWidth;
        y = 0;

        timer = new Timer(1, this);
        timer.start();

//        this.setPreferredSize(new Dimension(1920, 1080));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ hai bản sao của ảnh
        g.drawImage(image, x1, y, this);
        g.drawImage(image, x2, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x1 += step;
        x2 += step;

        if (x1 >= getWidth()) {
            x1 = x2 - imageWidth;
        }

        if (x2 >= getWidth()) {
            x2 = x1 - imageWidth;
        }

        repaint(); // Vẽ lại JPanel
    }

    public static void main(String[] args) {
        // Tạo JFrame để chứa JPanel
//        JFrame frame = new JFrame("Moving Image Panel");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
////        MovingImagePanel movingImagePanel = new MovingImagePanel();
//
//        frame.add(movingImagePanel, BorderLayout.CENTER);
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }
}
