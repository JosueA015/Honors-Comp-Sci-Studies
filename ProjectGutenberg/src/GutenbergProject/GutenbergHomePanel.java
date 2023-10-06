package GutenbergProject;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class that creates the home screen.
 */
public class GutenbergHomePanel extends JPanel
{
    /**
     * Constructs the home screen. This screen displays a JPanel with 3 buttons corresponding to Shakespeare plays.
     */
    public GutenbergHomePanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1000,800));
        setBackground(Color.cyan);

        ImageIcon macBethIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/download.jpeg")));
        ImageIcon romeoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-1.jpg")));
        ImageIcon midsummerIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-2.jpg")));

        String title = "<html><h1>Welcome to Project Gutenberg's Shakespeare Collection!</h1><br><h2><center>" +
                "Click on a Shakespeare play to learn more.</h2></center></html>";

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monocraft", Font.PLAIN, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));

        add(titleLabel);

        JButton macBethButton = new JButton("Macbeth");
        macBethButton.setPreferredSize(new Dimension(300,100));
        macBethButton.setMaximumSize(new Dimension(300,100));
        macBethButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        macBethButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        macBethButton.addActionListener(e -> {
            removeAll();
            GutenbergBookInfoPanel gbip = new GutenbergBookInfoPanel(macBethButton);
            add(gbip);
            gbip.requestFocusInWindow();
            revalidate();
        });

        JButton midSummerButton = new JButton("A Midsummer Night's Dream");
        midSummerButton.setPreferredSize(new Dimension(300,100));
        midSummerButton.setMaximumSize(new Dimension(300,100));
        midSummerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        midSummerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        midSummerButton.addActionListener(e -> {
            removeAll();
            GutenbergBookInfoPanel gbip = new GutenbergBookInfoPanel(midSummerButton);
            add(gbip);
            gbip.requestFocusInWindow();
            revalidate();
        });

        JButton romeoButton = new JButton("Romeo and Juliet");
        romeoButton.setPreferredSize(new Dimension(300,100));
        romeoButton.setMaximumSize(new Dimension(300,100));
        romeoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        romeoButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        romeoButton.addActionListener(e -> {
            removeAll();
            GutenbergBookInfoPanel gbip = new GutenbergBookInfoPanel(romeoButton);
            add(gbip);
            gbip.requestFocusInWindow();
            revalidate();
        });

        add(macBethButton);
        add(romeoButton);
        add(midSummerButton);
        add(Box.createVerticalStrut(50));

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(10,10));
        imagePanel.setBackground(Color.cyan);
        imagePanel.add(new JLabel(macBethIcon));
        imagePanel.add(new JLabel(romeoIcon));
        imagePanel.add(new JLabel(midsummerIcon));
        add(imagePanel);
    }
}
