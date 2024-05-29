package Code.Screen;

import javax.swing.*;
import java.awt.*;

public class ArrowPanel extends JPanel {
    private int positiveLength;
    private int negativeLength;

    public ArrowPanel(int positiveLength, int negativeLength) {
        // Kiểm tra các tham số đầu vào để đảm bảo tính hợp lệ
        if (positiveLength <= 0 || negativeLength >= 0) {
            throw new IllegalArgumentException("positiveLength phải lớn hơn 0 và negativeLength phải nhỏ hơn 0");
        }

        this.positiveLength = positiveLength;
        this.negativeLength = negativeLength;
        this.setPreferredSize(new Dimension(400, 400)); // Cài đặt kích thước ưu tiên cho JPanel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Lấy kích thước của JPanel
        int width = getWidth();
        int height = getHeight();

        // Tính toán tọa độ của tâm JPanel
        int centerX = width / 2;
        int centerY = height / 2;

        // Vẽ mũi tên dương với màu xanh
        drawArrow(g2d, centerX, centerY, centerX + positiveLength, centerY, 30, Color.BLUE);

        // Vẽ mũi tên âm với màu đỏ
        drawArrow(g2d, centerX, centerY, centerX + negativeLength, centerY, 30, Color.RED);
    }

    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2, int arrowWidth, Color color) {
        // Đặt màu sắc cho mũi tên
        g2d.setColor(color);

        // Vẽ thân mũi tên có độ rộng 30
        g2d.setStroke(new BasicStroke(arrowWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        g2d.drawLine(x1, y1, x2, y2);

        // Tính toán góc của mũi tên
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Độ dài của các nhánh mũi tên
        int arrowHeadLength = 20;

        // Tạo các điểm của polygon cho đầu mũi tên
        int[] xPoints = {
                x2,
                (int) (x2 - arrowHeadLength * Math.cos(angle - Math.PI / 6)),
                (int) (x2 - arrowHeadLength * Math.cos(angle + Math.PI / 6))
        };
        int[] yPoints = {
                y2,
                (int) (y2 - arrowHeadLength * Math.sin(angle - Math.PI / 6)),
                (int) (y2 - arrowHeadLength * Math.sin(angle + Math.PI / 6))
        };

        // Tạo polygon cho đầu mũi tên
        Polygon arrowHead = new Polygon(xPoints, yPoints, 3);
        g2d.fill(arrowHead);
    }

    public static void main(String[] args) {
        // Tạo JFrame để hiển thị ArrowPanel
        JFrame frame = new JFrame("Arrow Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo ArrowPanel với độ dài mũi tên dương và âm
        ArrowPanel arrowPanel = new ArrowPanel(100, -50);

        frame.add(arrowPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Hiển thị JFrame ở giữa màn hình
        frame.setVisible(true);
    }
}
