package Code.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingImagePanel extends JPanel implements ActionListener {
    private Image image;
    private int x1, x2, y;
    private int imageWidth, imageHeight;
    public int step = 5; // Bước di chuyển của ảnh
    private Timer timer;

    public MovingImagePanel(String s) {
        loadImage(s);
        timer = new Timer(10, this);
        timer.stop();
        this.setPreferredSize(new Dimension(1920, 1080));
    }

    private void loadImage(String s) {
        // Tải ảnh từ file
        ImageIcon icon = new ImageIcon(s);
        Image scaledImg = icon.getImage();
        // Resize the image if needed
        scaledImg = scaledImg.getScaledInstance(1920, 100, Image.SCALE_SMOOTH);
        ImageIcon haha = new ImageIcon(scaledImg);
        image = haha.getImage();
        imageWidth = image.getWidth(this);
        imageHeight = image.getHeight(this);

        x1 = -imageWidth;
        x2 = x1 + imageWidth;
        y = -10;
    }

    public void setImage(String imagePath) {
        loadImage(imagePath);
        repaint();
    }

    public void adjust(int velocity) {
        int speed = Math.abs(velocity);
        if (speed == 0) stopTimer();
        else {
            setTimerInterval(200 / speed);
            if (velocity < 0) {
                step = Math.max(1, speed / 5);
            } else step = -Math.max(1, speed / 5);
        }
    }

    public void setTimerInterval(int interval) {
        timer.setDelay(interval);
        startTimer();
    }

    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void startTimer() {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
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

        if (step > 0) { // Di chuyển sang phải
            if (x1 >= getWidth()) {
                x1 = x2 - imageWidth;
            }

            if (x2 >= getWidth()) {
                x2 = x1 - imageWidth;
            }
        } else { // Di chuyển sang trái
            if (x1 <= -imageWidth) {
                x1 = x2 + imageWidth;
            }

            if (x2 <= -imageWidth) {
                x2 = x1 + imageWidth;
            }
        }

        repaint(); // Vẽ lại JPanel
    }
}
