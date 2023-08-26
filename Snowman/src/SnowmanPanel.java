import javax.swing.*;
import java.awt.*;

public class SnowmanPanel extends JPanel
{
    private final int MID = 150;
    private final int TOP = 50;

    int[] xStar1 = {25, 32, 50, 37, 45, 25, 5, 12, 0, 17};
    int[] yStar1 = {0, 20, 20, 32, 50, 37, 50, 32, 20, 20};

    int[] xStar2 = {75, 82, 100, 87, 95, 75, 55, 62, 50, 67};

    int[] yStar2 = {50, 70, 70, 82, 100, 87, 100, 82, 70, 70};

    public SnowmanPanel()
    {
        setPreferredSize(new Dimension(300, 225));
        setBackground(Color.black);
    }

    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        page.setColor(Color.gray);
        page.fillRect(0, 175, 300, 50);

        page.setColor(Color.gray);
        page.fillOval(260, -40, 80, 80);

        page.setColor(Color.yellow);
        page.fillPolygon(xStar1, yStar1, xStar1.length);
        page.fillPolygon(xStar2, yStar2, xStar2.length);

        page.setColor(Color.white);
        page.fillOval(MID - 20, TOP, 40, 40);
        page.fillOval(MID - 35, TOP + 35, 70, 50);
        page.fillOval(MID - 50, TOP + 80, 100, 60);

        page.setColor(Color.red);
        page.fillOval(MID - 5, TOP + 50, 8, 8);
        page.fillOval(MID - 5, TOP + 60, 8, 8);

        page.setColor(Color.gray);
        page.fillOval(MID - 10, TOP + 10, 5, 5);
        page.fillOval(MID + 5, TOP + 10, 5, 5);

        page.drawArc(MID - 10, TOP + 20, 20, 10, -190, -160);

        page.drawLine(MID - 25, TOP + 60, MID - 50, TOP + 40);
        page.drawLine(MID + 25, TOP + 60, MID + 55, TOP + 40);

        page.drawLine(MID - 20, TOP + 5, MID + 20, TOP + 5);
        page.fillRect(MID-15, TOP-20, 30, 25);
    }
}
