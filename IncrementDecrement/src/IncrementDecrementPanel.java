import javax.swing.*;
import java.awt.*;

public class IncrementDecrementPanel extends JPanel
{

    public int getCounter()
    {
        return counter;
    }

    private int counter;


    private JLabel label;
    public IncrementDecrementPanel()
    {
        counter = 50;

        this.setLayout(null);

        JButton increment = new JButton("Increment");
        JButton decrement = new JButton("Decrement");

        increment.setPreferredSize(new Dimension(150,100));
        decrement.setPreferredSize(new Dimension(150,100));

        label = new JLabel("" + counter);
        label.setFont(new Font("Monocraft", Font.PLAIN, 24));

        add(increment);
        add(decrement);
        add(label);

        setPreferredSize(new Dimension(400, 180));
        setBackground(Color.cyan);

        increment.setBounds(50,10,150,100);
        decrement.setBounds(210,10,150,100);
        label.setBounds(190, 125, 50, 30);

        increment.addActionListener(e -> setIncrementButtonCounter());

        decrement.addActionListener(e -> setDecrementButtonCounter());
    }

    public void setIncrementButtonCounter()
    {
       counter++;
       label.setText("" + counter);
    }

    public void setDecrementButtonCounter()
    {
        counter--;
        label.setText("" + counter);

    }
}
