
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RubberLinesPanel extends JPanel
{
    private Point point1 = null, point2 = null;

    public RubberLinesPanel()
    {
        LineListener listener = new LineListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);

        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 250));
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        page.setColor(Color.yellow);
        if (point1 != null && point2 != null)
        {
            page.drawLine(point1.x, point1.y, point2.x, point2.y);
        }
    }

    private class LineListener extends MouseAdapter
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            point1 = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            point2 = e.getPoint();
            repaint();
        }
    }
}
