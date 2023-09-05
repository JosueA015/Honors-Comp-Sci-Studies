import javax.swing.*;

public class Direction
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Direction");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add((new DirectionPanel()));

        frame.pack();
        frame.setVisible(true);
    }
}
