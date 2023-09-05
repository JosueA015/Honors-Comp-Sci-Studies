import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class DirectionPanel extends JPanel
{
    private final int HEIGHT = 200, WIDTH = 250;

    private final JLabel label;

    int[] xPoints = {100, 100, 75, 125, 175, 150, 150, 100};
    int[] yPoints = {50, 100, 100, 150, 100, 100, 50, 50};
    int numPoints = xPoints.length;
    Polygon arrow = new Polygon(xPoints, yPoints, numPoints);
    double angle;
    double centerX = 125;
    double centerY = 100;

    public DirectionPanel()
    {
        this.setLayout(null);

        addKeyListener(new DirectionListener());
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        label = new JLabel("Press the arrow keys to change the arrow!");
        label.setFont(new Font("Monocraft", Font.PLAIN, 8));
        label.setForeground(Color.gray);

        add(label);
        label.setBounds(20, 162, 250, 50);
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        Graphics2D page2d = (Graphics2D) page;
        page2d.setColor(Color.cyan);

        AffineTransform rotation = AffineTransform.getRotateInstance(angle, centerX, centerY);
        Shape rotatedArrow = rotation.createTransformedShape(arrow);

        page2d.draw(rotatedArrow);
        page2d.fill(rotatedArrow);
    }

    private class DirectionListener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> angle = 0;
                case KeyEvent.VK_UP -> angle = Math.PI;
                case KeyEvent.VK_RIGHT -> angle = (3 * Math.PI) / 2;
                case KeyEvent.VK_LEFT -> angle = Math.PI / 2;
            }

            repaint();
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

}
