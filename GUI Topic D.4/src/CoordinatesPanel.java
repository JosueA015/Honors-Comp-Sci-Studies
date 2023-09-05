import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoordinatesPanel extends JPanel
{
    private final int SIZE = 10;
    private int x = 50, y = 50;

    public CoordinatesPanel()
    {
        addMouseListener(new CoordinatesListener());

        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 250));
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        page.setColor(Color.green);

        page.fillOval(x,y,SIZE,SIZE);
        page.drawString("Coordinates: (" + x + ", " + y + ")", 5, 15);
    }
    private class CoordinatesListener implements MouseListener
    {
        public void mousePressed(MouseEvent event)
        {
            x = event.getX();
            y = event.getY();
            repaint();
        }

        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}

    }


}

