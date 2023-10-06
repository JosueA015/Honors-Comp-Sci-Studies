package WhackAMole;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the pause menu for the "Whack-a-mole" game.
 * It displays the user's current score and houses 3 buttons:
 * one to quit the game, one to resume playing on your current game, and one to restart the game.
 */
public class PausePanel extends JPanel
{
    /**
     * Constructs the pause menu and all three of its buttons.
     * @param mp the MolePanel object from your current game.
     * @param gp the MoleGamePanel object from your current game.
     */
    public PausePanel(MolePanel mp, MoleGamePanel gp)
    {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(mp.getPreferredSize());

        // adds the score label from the game and centers the components
        JLabel moleScore = mp.getScoreLabel();
        moleScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(mp.getPreferredSize().height));
        add(moleScore);

        Dimension buttonSize = new Dimension(300, 60);

        // adds Quit button
        JButton quitButton = new JButton("Quit?");
        quitButton.setMaximumSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);
        quitButton.setMnemonic('q');
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        quitButton.setBackground(Color.green);
        quitButton.addActionListener(e ->
                System.exit(0));


        // adds Restart button
        JButton playButton = new JButton("Resume?");
        playButton.setMaximumSize(buttonSize);
        playButton.setPreferredSize(buttonSize);
        playButton.setMnemonic('r');
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        playButton.setBackground(Color.green);

        playButton.addActionListener(e ->
        {
            removeAll();
            setLayout(new BorderLayout(0, 20));
            for (Component component : gp.getDeletedComponents())
            {
                if (component instanceof JLabel)
                {
                    setBackground(new Color(116, 220, 129));
                    add(component, BorderLayout.NORTH);
                }
                else
                {
                    if (component instanceof JPanel)
                    {
                        LayoutManager layoutManager = ((JPanel) component).getLayout();
                        if (layoutManager instanceof FlowLayout)
                        {
                            component.setBackground(new Color(116, 220, 129));
                            add(component, BorderLayout.SOUTH);
                        }
                        else
                        {
                            add(component, BorderLayout.CENTER);
                        }
                    }
                }
            }
            requestFocusInWindow();
            revalidate();
        });

        // adds Restart button
        JButton restartButton = new JButton("Restart?");
        restartButton.setMaximumSize(buttonSize);
        restartButton.setPreferredSize(buttonSize);
        restartButton.setMnemonic('s');
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        restartButton.setBackground(Color.green);

        restartButton.addActionListener(e ->
        {
            SwingUtilities.invokeLater(() -> {
                removeAll();
                MoleGamePanel gp2 = new MoleGamePanel();
                add(gp2);
                gp2.requestFocusInWindow();
                revalidate();
            });
        });

            add(quitButton);
            add(playButton);
            add(restartButton);


    }

}
