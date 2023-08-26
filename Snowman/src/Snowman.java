import javax.swing.*;
import java.awt.*;

public class Snowman
{
    public static void main(String[] args)
    {
       JFrame frame = new JFrame("Snowman");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JButton button = new JButton("Click to see a snowman!");

        frame.setPreferredSize(new Dimension(300, 225));
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);

       button.addActionListener(e -> {
           frame.getContentPane().remove(button);
           frame.getContentPane().add(new SnowmanPanel());
           frame.pack();
           frame.revalidate();
       });

    }
}