package WhackAMole;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * This is the runner class for the "Whack-a-mole" game.
 * Runs the MoleGamePanel and configures all objects to fit neatly on the panel.
 */
public class WhackAPaceKnight
{

    /**
     * Runs the game.
     */
    public static void main(String[] args)
    {
        startGame();
    }

    /**
     * Starts the "Whack-a-mole" game and implements nice quality of life perks (i.e. mnemonics, custom sword cursor).
     */
    public static void startGame()
    {
        SwingUtilities.invokeLater(() -> {

                MoleGamePanel whack = new MoleGamePanel();

                JFrame frame = new JFrame("Whack-a-Pace-Knight");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(1000, 750));

                frame.getContentPane().add(whack);

                ImageIcon cursorIcon = new ImageIcon(Objects.requireNonNull(WhackAPaceKnight.class.getResource("images.png")));
                Image cursorImage = cursorIcon.getImage().getScaledInstance(60,60,Image.SCALE_FAST);
                Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
                frame.getContentPane().setCursor(customCursor);


                frame.pack();
                frame.setVisible(true);

                whack.requestFocusInWindow();

        });

    }

}
