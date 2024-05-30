package Code.Screen;

import javax.swing.*;
import java.awt.*;

public class ArrowPanel extends JPanel {
    Arrow AFar, FRar, sum_arrow;

    public ArrowPanel(float AFlength, float FRlength) {
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(400, 150));
        setOpaque(false);
//        if(AFlength>0|| FRlength<0) {
            AFar = new Arrow(AFlength, Color.RED);
            FRar = new Arrow(FRlength, Color.BLUE);
//        }


        float sum_force = AFlength + FRlength;
        sum_arrow = new Arrow(sum_force, Color.GREEN);

        if (AFlength != 0) {
            AFar.setBounds(0, 0, 400, 100);
            layer.add(AFar, Integer.valueOf(2));
        }

        if (FRlength != 0) {
            FRar.setBounds(0, 0, 400, 100);
            layer.add(FRar, Integer.valueOf(1));
        }

        if (sum_force != 0) {
            sum_arrow.setBounds(0, 50, 400, 100);
            layer.add(sum_arrow, Integer.valueOf(3));
        }

        layer.setVisible(true);
        add(layer);
        repaint();
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Arrow Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ArrowPanel(20, -40));
            frame.setSize(400, 200);
            frame.setVisible(true);
            frame.repaint();
            frame.revalidate();
        });
    }
}

class Arrow extends JPanel {
    protected double forceLength;
    protected Color color;

    public Arrow(double forceLength, Color color) {
        setPreferredSize(new Dimension(400, 100));
        setOpaque(false);
        this.forceLength = forceLength;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Skip drawing if force length is zero
        if (forceLength == 0) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for better rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determine starting position
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int x1 = panelWidth / 2;
        int y1 = panelHeight / 2;

        // Adjust strength for drawing
        double strength = forceLength / 2;

        // Define arrow dimensions and position
        int arrowLength = (int) (Math.abs(strength / 2));
        int arrowHeight = 40;
        int arrowHeadLength = 20;
        int arrowHeadHeight = 20;

        // Determine the direction and adjust x2 based on the sign of strength
        boolean isPositive = strength > 0;
        int x2 = isPositive ? x1 + arrowLength : x1 - arrowLength;

        // Set arrow color
        g2d.setColor(color);

        // Draw arrow body
        if (isPositive) {
            g2d.fillRect(x1, y1, arrowLength, arrowHeight);
        } else {
            g2d.fillRect(x2, y1, arrowLength, arrowHeight);
        }

        // Draw arrow head
        int[] xPoints = isPositive ? new int[]{x1 + arrowLength, x1 + arrowLength + arrowHeadLength, x1 + arrowLength} :
                new int[]{x2, x2 - arrowHeadLength, x2};
        int[] yPoints = new int[]{y1 - 5, y1 + arrowHeadHeight, y1 + arrowHeight + 5};
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw the force strength label
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        String label = Math.abs((int) (strength * 2)) + "N";
        FontMetrics metrics = g.getFontMetrics(g2d.getFont());
        int labelWidth = metrics.stringWidth(label);
        int labelHeight = metrics.getHeight();
        int labelX = isPositive ? Math.max(x1, x1 + (arrowLength - labelWidth) / 2) :
                Math.min(x1 - labelWidth, x2 + (arrowLength - labelWidth) / 2);
        int labelY = y1 + (arrowHeight + labelHeight) / 2 - 5;
        g2d.drawString(label, labelX, labelY);
    }
}
