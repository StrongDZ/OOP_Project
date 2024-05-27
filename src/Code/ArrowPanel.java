package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

    public class ArrowPanel extends JComponent {
        private int bodyWidth;
        private int bodyHeight;
        private int headWidth;
        private int headHeight;

        public ArrowPanel(int bodyWidth, int bodyHeight, int headWidth, int headHeight) {
            this.bodyWidth = bodyWidth;
            this.bodyHeight = bodyHeight;
            this.headWidth = headWidth;
            this.headHeight = headHeight;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Vẽ thân mũi tên
            Rectangle body = new Rectangle(10,10, bodyWidth, bodyHeight);
            g2d.fillRect(body.x, body.y, body.width, body.height);

            // Vẽ đầu mũi tên
            int x1 = 10 + bodyWidth+headWidth;
            int y1 = 10 + bodyHeight / 2;
            int x2 = x1 - headWidth;
            int y2 = y1 - headHeight / 2;
            int x3 = x1 - headWidth;
            int y3 = y1 + headHeight / 2;
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g2d.fillPolygon(xPoints, yPoints, 3);
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rectangular Arrow Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        // Example with custom arrow size
        ArrowPanel arrowPanel = new ArrowPanel(300, 30, 40, 50);
        frame.add(arrowPanel);
//        frame.pack();
        frame.setVisible(true);
    }}


