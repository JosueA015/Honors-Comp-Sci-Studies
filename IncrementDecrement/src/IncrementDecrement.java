import javax.swing.*;

public class IncrementDecrement
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Push Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new IncrementDecrementPanel());
        frame.pack();
        frame.setVisible(true);



    }
}