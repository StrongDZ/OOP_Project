import javax.swing.*;
import java.awt.*;

public class GridBagLayoutBackgroundExample {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("GridBagLayout Background Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE   );
        frame.setSize(400, 400);

        // Đường dẫn đến ảnh trên máy tính của bạn
        String imagePath = "D:\\Java\\OOP_Project\\src\\Code\\Screen\\Background.jpg"; // Thay "path/to/your/background.jpg" bằng đường dẫn thực tế

        // Tạo một JPanel với GridBagLayout và ảnh nền
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon(imagePath).getImage());

        panel.setLayout(new GridBagLayout());

        // Đặt constraints cho GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Thêm khoảng cách giữa các ô

        // Thêm các thành phần vào JPanel
        for (int i = 0; i < 4; i++) {
            gbc.gridx = i % 2; // Cột (0 hoặc 1)
            gbc.gridy = i / 2; // Hàng (0 hoặc 1)
            JButton button = new JButton("Button " + (i + 1));
            panel.add(button, gbc);
        }

        // Thêm JPanel vào JFrame
        frame.add(panel);

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}

// Lớp BackgroundPanel kế thừa từ JPanel để vẽ ảnh nền
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Vẽ ảnh nền
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
