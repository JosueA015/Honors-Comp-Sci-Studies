package WhackAMole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Combines the {@link MolePanel}, {@link Mole}, and {@link PausePanel} classes into one panel that is the sole panel for the "Whack-a-mole" game.
 * This is the panel that the user plays the game on.
 */
public class MoleGamePanel extends JPanel
{
    /**
     * A new MolePanel object that will be in the center of the panel.
     */
    static MolePanel mp;

    /**
     * Gets all of the components that were deleted by pausing the game
     * @return all of the deleted components.
     */
    public ArrayList<Component> getDeletedComponents()
    {
        return deletedComponents;
    }
    /**
     * An ArrayList of deleted components that will be used to reinstantiate them.
     */
    ArrayList<Component> deletedComponents;

    /**
     * The pause button that shows the PausePanel.
     */
    static JButton pauseButton;

    /**
     * Constructs the "Whack-a-mole" game.
     */
    public MoleGamePanel()
        {
            mp = new MolePanel();
            addKeyListener(new EscapeListener());
            deletedComponents = new ArrayList<>();

            pauseButton = new JButton("Pause?");
            pauseButton.setBackground(Color.green);
            pauseButton.setFont(new Font("Monocraft", Font.PLAIN, 10));
            pauseButton.setMnemonic(KeyEvent.VK_P);
            pauseButton.setPreferredSize(new Dimension(350,70));


            JPanel pauseButtonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));

            pauseButtonContainer.setBackground(new Color(116, 220, 129));

            pauseButton.addActionListener(e ->
                    {
                        Component[] components = this.getComponents();
                        for (Component component : components)
                        {
                            deletedComponents.add(component);
                            remove(component);
                        }
                        add(new PausePanel(mp, this));
                        revalidate();
                    });

            setLayout(new BorderLayout(0, 20));
            setBackground(new Color(0x74dc81));

            pauseButtonContainer.add(pauseButton);
            add(mp.getScoreLabel(), BorderLayout.NORTH);
            add(mp, BorderLayout.CENTER);
            add(pauseButtonContainer, BorderLayout.SOUTH);
            requestFocusInWindow();


        }

    /**
     * A KeyAdapter class that listens for escape presses and quits the game when it receives one.
     */
    private static class EscapeListener extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }
        }

    }



}