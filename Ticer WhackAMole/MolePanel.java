package WhackAMole;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Class that generates the main "whack-a-mole" panel where users will click on the enemies to score points.
 * Creates a 2D array of "moles" that will periodically move throughout the screen and will disappear if clicked on, earning points.
 */
public class MolePanel extends JPanel
{

    /**
     * The minimum time between "mole" spawns.
     */
    int timerOrigin = 1000;
    /**
     * The maximum time between "mole" spawns.
     */
    int timerBound = 3001;
    /**
     * The number of rows in the 2D array that spawns the "moles".
     */
    private final int rows;
    /**
     * The number of columns in the 2D array that spawns the "moles".
     */
    private final int cols;
    /**
     * The array that houses all of the "moles".
     */
    private final Mole[][] moleArray;
    /**
     * The player's score.
     */
    private int score;
    /**
     * A Timer object that moves or spawns a "mole" after a set delay that shortens as the score increases.
     */
    private final Timer timer;
    /**
     * The JLabel that displays the user's score.
     */
    private final JLabel scoreLabel;

    /**
     * Getter method for the score label.
     * @return the scoreLabel object.
     */
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Constructs a mole panel that generates a 2D array of "mole" objects and sets them to randomly appear and disappear.
     * This is the main gameplay of the "Whack-a-mole" game.
     */
    public MolePanel()
    {
        setBackground(new Color(116, 220, 129));

        // sets rows and cols of mole hole array to random ints
        Random random = new Random();
        rows = random.nextInt(3, 6);
        cols = random.nextInt(3, 6);
        moleArray = new Mole[rows][cols];

        // Sets score text
        score = 0;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setPreferredSize(new Dimension(100, 50));
        scoreLabel.setFont(new Font("Monocraft", Font.PLAIN, 30));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setText("Score: " + score);

        // Sets mole hole layout in panel
        setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows; i++)
        {
            for (int q = 0; q < cols; q++)
            {
                moleArray[i][q] = new Mole();
                moleArray[i][q].setPreferredSize(new Dimension(100,50));
                moleArray[i][q].setEnabled(false);
                moleArray[i][q].setFocusable(false);
                int finalI = i;
                int finalQ = q;

                moleArray[i][q].addActionListener(e ->
                {
                    if (moleArray[finalI][finalQ].isEnabled())
                    {
                        score+= 100;
                        scoreLabel.setText("Score: " + score);
                    }
                    moleArray[finalI][finalQ].setEnabled(false);
                    moleArray[finalI][finalQ].setVisible(false);
                });

                add(moleArray[finalI][finalQ]);
            }
        }

        timer = new Timer(random.nextInt(timerOrigin, timerBound), e -> moveMole());
        timer.start();
    }

    /**
     * Despawns every "mole" except for one, which will move around the 2D array if not clicked and will disappear if it is clicked.
     */
    private void moveMole()
    {
        Random random = new Random();
        int row = random.nextInt(rows);
        int col = random.nextInt(cols);

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                moleArray[i][j].setVisible(false);
                moleArray[i][j].setEnabled(false);
            }
        }
        
        moleArray[row][col].setEnabled(true);
        moleArray[row][col].setVisible(true);
        if (score != 0 && score % 500 == 0)
        {
            timerOrigin = (int) Math.max(timerOrigin * 0.8, 500);
            timerBound = (int) Math.max(timerBound * 0.8, 800);
        }
        timer.setDelay(random.nextInt(timerOrigin, timerBound));
    }



}
