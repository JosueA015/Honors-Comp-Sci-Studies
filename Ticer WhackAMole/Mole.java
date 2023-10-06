package WhackAMole;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * This class creates a JButton that acts as one "mole".
 * It sets the mole's background to the same as the MolePanel's background and gives the mole an icon of a Pace knight.
 */
public class Mole extends JButton
{
    /**
     * Constructs a "mole" button.
     */
    public Mole()
    {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Mole.class.getResource("download.png")));
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_FAST);
        icon = new ImageIcon(scaledImage);
        this.setBorderPainted(false);
        this.setBackground(new Color(0x74dc81));
        this.setIcon(icon);

    }
}
