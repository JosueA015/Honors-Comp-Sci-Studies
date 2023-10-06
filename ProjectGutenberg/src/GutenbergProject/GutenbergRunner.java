package GutenbergProject;

import javax.swing.*;
import java.awt.*;

/**
 * Runner class that runs the project. Creates and fullscreens a {@link GutenbergHomePanel} object.
 */
public class GutenbergRunner
{
    public static void main(String[] args)
    {
            JFrame frame = new JFrame("Push Counter");
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new GutenbergHomePanel());


            frame.pack();
            frame.setVisible(true);
    }

}
