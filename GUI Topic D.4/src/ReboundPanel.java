import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReboundPanel extends JPanel
{
    private final int WIDTH = 300, HEIGHT = 200;
    private final int IMAGE_SIZE = 30;
    private final Timer timer;
    private final int DELAY = 20;
    private final ImageIcon image;
    private int x, y, moveX, moveY;
    public ReboundPanel()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);

        timer = new Timer(DELAY,e -> {
            BounceBall();
            repaint();
        });

        image = new ImageIcon(ReboundPanel.class.getResource("download-1.png"));
        x = 40;
        y = 40;
        moveX = moveY = 2;

        timer.start();
    }

    private void BounceBall() {
        int boundsX = getWidth();
        int boundsY = getHeight();


        x += moveX;
        y += moveY;
        if (x >= boundsX - IMAGE_SIZE || x < 0)
        {
            moveX = moveX * -1;
        }

        if (y >= boundsY - IMAGE_SIZE || y < 0)
        {
            moveY = moveY * -1;
        }

        if (y >= boundsY - 20 || x >= boundsX - 20)
        {
            x = 40;
            y = 40;
        }
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        image.paintIcon(this, page, x, y);
    }
}
