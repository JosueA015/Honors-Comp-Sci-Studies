package GutenbergProject;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Class that creates a panel that displays user-selected info about a Shakespeare play.
 * When an option on a combo-box is clicked that details the fact that the user would like to learn about,
 * a JPanel is shuffled via a CardLayout that displays said fact (i.e. word count). Resources used: Oracle, Project Gutenberg
 */
public class GutenbergBookInfoPanel extends JPanel
{
    static HashMap<String, Scanner> bookMap = new HashMap<>();
    static JTextArea textArea;

    static JTextArea textArea2;

    /**
     * Constructs the book info panel.
     * @param button The button that was clicked in order to create this panel; used for its name.
     */
    public GutenbergBookInfoPanel(JButton button) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 500));
        setBackground(new Color(92, 239, 57, 219));

        String book = button.getText();

        String bookInfoTitle = "<html><h2>Welcome to the information panel for " + book +
                "!</h2><br><h3><center>Click on a button to learn various facts about this play.</h3></center></html>";
        JLabel bookInfoLabel = new JLabel(bookInfoTitle, SwingConstants.CENTER);
        bookInfoLabel.setFont(new Font("Monocraft", Font.PLAIN, 18));
        bookInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        comboBoxPane.setBackground(new Color(92, 239, 57, 219));
        JPanel cards = new JPanel(new CardLayout());
        cards.setBackground(new Color(92, 239, 57, 219));
        String[] comboBoxItems = {"Word Count", "Longest Word", "Longest Sentence"};
        JComboBox<String> cb = new JComboBox<>(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, (String) e.getItem());
        });
        comboBoxPane.add(cb);

        JPanel card1 = new JPanel(new FlowLayout());
        card1.setBackground(new Color(92, 239, 57, 219));
        JLabel wordLabel = new JLabel("Choose a section of the play to see the word count!");
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wordLabel.setFont(new Font("Monocraft", Font.PLAIN, 18));

        JComboBox<String> delimiterBox = getStringJComboBox(wordLabel, book, 0);

        card1.add(Box.createRigidArea(new Dimension(2000,50)));
        card1.add(wordLabel);
        card1.add(delimiterBox);
        card1.add(Box.createRigidArea(new Dimension(2000,0)));

        JScrollPane wordCountPane = getjScrollPane(0);
        wordCountPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        wordCountPane.setPreferredSize(new Dimension(500, 200));
        card1.add(wordCountPane);
        card1.add(Box.createRigidArea(new Dimension(2000,0)));

        JButton homeButton = getHomeButton();
        card1.add(homeButton);
        card1.add(Box.createRigidArea(new Dimension(2000,0)));

        JPanel card2 = new JPanel();
        card2.add(Box.createRigidArea(new Dimension(2000,150)));
        card2.setBackground(new Color(92, 239, 57, 219));

        JLabel longestWordLabel = new JLabel("Choose a section of the play to see the longest word!");
        longestWordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        longestWordLabel.setFont(new Font("Monocraft", Font.PLAIN, 24));
        card2.add(longestWordLabel);
        card2.add(Box.createRigidArea(new Dimension(2000,0)));

        JComboBox<String> longestWordBox = getStringJComboBox(longestWordLabel, book, 1);
        longestWordBox.removeActionListener(longestWordBox.getActionListeners()[0]);
        longestWordBox.addActionListener(e ->
                longestWordLabel.setText(longestWordCount(longestWordBox.getSelectedIndex(), book)));
        card2.add(longestWordBox);
        JButton homeButton2 = getHomeButton();
        card2.add(homeButton2);
        card2.add(Box.createRigidArea(new Dimension(2000,0)));

        JPanel card3 = new JPanel();
        card3.add(Box.createRigidArea(new Dimension(2000,50)));
        card3.setBackground(new Color(92, 239, 57, 219));

        JLabel longestSentenceLabel = new JLabel("Choose a section of the play to see the longest sentence!");
        longestSentenceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        longestSentenceLabel.setFont(new Font("Monocraft", Font.PLAIN, 24));
        card3.add(longestSentenceLabel);
        card3.add(Box.createRigidArea(new Dimension(2000,0)));

        JComboBox<String> longestSentenceBox = getStringJComboBox(longestSentenceLabel, book, 1);
        longestSentenceBox.removeActionListener(longestSentenceBox.getActionListeners()[0]);
        longestSentenceBox.addActionListener(e ->
                longestSentenceLabel.setText(longestSentenceCount(longestSentenceBox.getSelectedIndex(), book)));
        card3.add(longestSentenceBox);
        card3.add(Box.createRigidArea(new Dimension(2000,0)));

        JScrollPane sentencePane = getjScrollPane(1);
        sentencePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sentencePane.setPreferredSize(new Dimension(500, 200));
        card3.add(sentencePane);
        card3.add(Box.createRigidArea(new Dimension(2000,0)));

        JButton homeButton3 = getHomeButton();
        card3.add(homeButton3);
        card3.add(Box.createRigidArea(new Dimension(2000,0)));


        switch (button.getText()) {
            case "Macbeth" -> {
                card1.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download.jpeg")))));
                card2.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download.jpeg")))));
                card3.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download.jpeg")))));
            }
            case "Romeo and Juliet" -> {
                card1.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-1.jpg")))));
                card2.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-1.jpg")))));
                card3.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-1.jpg")))));
            }
            case "A Midsummer Night's Dream" -> {
                card1.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-2.jpg")))));
                card2.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-2.jpg")))));
                card3.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/download-2.jpg")))));
            }
        }

        //Create the panel that contains the "cards".

        cards.add(card1, comboBoxItems[0]);
        cards.add(card2, comboBoxItems[1]);
        cards.add(card3, comboBoxItems[2]);



        add(comboBoxPane, BorderLayout.NORTH);
        add(bookInfoLabel, BorderLayout.SOUTH);
        add(cards, BorderLayout.CENTER);

    }

    /**
     * Creates a JScrollPane with an added JTextArea.
     * @param i int designating which JTextArea to add to the created JScrollPane
     * @return a new JScrollPane
     */
    private static JScrollPane getjScrollPane(int i) {
        if (i == 0) {
            textArea = new JTextArea(5,5);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setFont(new Font("Monocraft", Font.PLAIN, 10));
            return new JScrollPane(textArea);
        } else {
            textArea2 = new JTextArea(5,5);
            textArea2.setLineWrap(true);
            textArea2.setWrapStyleWord(true);
            textArea2.setEditable(false);
            textArea2.setFont(new Font("Monocraft", Font.PLAIN, 10));
            return new JScrollPane(textArea2);
        }
    }

    /**
     * Creates a combo box that contains all 5 acts of each Shakespeare play in addition to the entire play itself.
     * @param wordLabel A JLabel describing the possible info that users can learn about the plays.
     * @param book The play that is being asked about.
     * @param i Denotes which version of the combo box to add
     * @return the combo box.
     */
    private static JComboBox<String> getStringJComboBox(JLabel wordLabel, String book, int i) {
        String[] delimiterBoxItems;
        if (i == 0) {
            delimiterBoxItems = new String[]{"Entire Play", "Act 1", "Act 2", "Act 3", "Act 4", "Act 5 (if applicable)"};
        } else
        {
            delimiterBoxItems = new String[]{"Act 1", "Act 2", "Act 3", "Act 4", "Act 5 (if applicable)"};
        }
        JComboBox<String> delimiterBox = new JComboBox<>(delimiterBoxItems);
        delimiterBox.setEditable(false);
        delimiterBox.setPreferredSize(new Dimension(300, 100));
        delimiterBox.addActionListener(e ->
        {
            try {
                wordLabel.setText(WordCountFinder(delimiterBox.getSelectedIndex(), book));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        return delimiterBox;
    }

    /**
     * Creates a home button that creates a new {@link GutenbergHomePanel}.
     * @return A new home button.
     */
    private JButton getHomeButton() {
        JButton homeButton = new JButton();
        homeButton.setMaximumSize(new Dimension(100,100));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        homeButton.setText("Home Button");
        homeButton.addActionListener(e -> {
            removeAll();
            GutenbergHomePanel gbip = new GutenbergHomePanel();
            add(gbip);
            gbip.requestFocusInWindow();
            revalidate();
        });
        return homeButton;
    }

    /**
     * Finds the number of words in a given act of Shakespeare
     * @param actNumber the act being searched
     * @param bookName the play being searched
     * @return A String detailing the number of words.
     * @throws FileNotFoundException if the .txt file is not found
     */
    private static String WordCountFinder(int actNumber, String bookName) throws FileNotFoundException {
        try
        {
            bookMap.put("Macbeth", new Scanner(new FileReader("src/GutenbergProject/res/Macbeth.txt")));
            bookMap.put("A Midsummer Night's Dream", new Scanner(new FileReader("src/GutenbergProject/res/Midsummer Night's Dream.txt")));
            bookMap.put("Romeo and Juliet", new Scanner(new FileReader("src/GutenbergProject/res/Romeo and Juliet.txt")));
        } catch (FileNotFoundException e) {throw new RuntimeException(e);}

        Scanner inputStream = bookMap.get(bookName);
        int count = 0;
        inputStream.useDelimiter("ACT");
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        while (inputStream.hasNext())
        {
            count++;
            hashMap.put("Act " + (count - 1), inputStream.next().substring(actNumber + 1));
        }
        int wordCount;

        try
        {
            StringTokenizer tokens = new StringTokenizer(hashMap.get("Act " + actNumber));
            textArea.setText(hashMap.get("Act " + actNumber));
            wordCount = tokens.countTokens();
        }
        catch (Exception e)
        {
            throw new FileNotFoundException("??");
        }
        if (actNumber == 0)
        {
            bookMap.remove(bookName);
            bookMap.put("Macbeth", new Scanner(new FileReader("src/GutenbergProject/res/Macbeth.txt")));
            bookMap.put("A Midsummer Night's Dream", new Scanner(new FileReader("src/GutenbergProject/res/Midsummer Night's Dream.txt")));
            bookMap.put("Romeo and Juliet", new Scanner(new FileReader("src/GutenbergProject/res/Romeo and Juliet.txt")));

            StringBuilder sb = new StringBuilder();
            for (String s : hashMap.values())
            {
                sb.append(s);
            }

            return "There are " + totalWordCount(bookName, String.valueOf(sb)) + " words in this play.";
        }
        return "There are " + wordCount + " words in this section of the play.";
    }

    /**
     * Counts the total number of words in a Shakespeare play.
     * @param bookName the play being searched
     * @param words Every word of the play
     * @return the total number of words in the play
     */
    private static int totalWordCount(String bookName, String words)
    {
        Scanner inputStream = bookMap.get(bookName);
        int count = 0;
        while (inputStream.hasNext())
        {
            count++;
            inputStream.next();
        }
        textArea.setText(words);
        return count;
    }

    /**
     * Creates a String detailing the longest word in a given act of Shakespeare
     * @param actNumber the act being searched
     * @param bookname the play being searched
     * @return a String detailing the longest word in a given act of Shakespeare
     */
    private static String longestWordCount(int actNumber, String bookname)
    {
        try
        {
            bookMap.put("Macbeth", new Scanner(new FileReader("src/GutenbergProject/res/Macbeth.txt")));
            bookMap.put("A Midsummer Night's Dream", new Scanner(new FileReader("src/GutenbergProject/res/Midsummer Night's Dream.txt")));
            bookMap.put("Romeo and Juliet", new Scanner(new FileReader("src/GutenbergProject/res/Romeo and Juliet.txt")));
        } catch (FileNotFoundException e) {throw new RuntimeException(e);}

        Scanner inputStream = bookMap.get(bookname);
        int count = 0;
        inputStream.useDelimiter("ACT");
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        while (inputStream.hasNext())
        {
            hashMap.put("Act " + (count - 1), inputStream.next().substring(actNumber + 1));
            count++;
        }
        int letterCount = 0;
        String longestWord = null;

        String[] result = hashMap.get("Act " + actNumber).split("[\\s;:.,–!?]");
        for (String s : result)
        {
            if (s.length() > letterCount)
            {
                letterCount = s.length();
                longestWord = s;
            }
        }

    return "The longest word is " + longestWord + ", its length is " + letterCount + " characters!";
    }

    /**
     * Creates a String detailing the longest sentence (i.e. ending with .!?) in a given act of Shakespeare
     * @param actNumber the act being searched
     * @param bookname the play being searched
     * @return a String detailing the longest sentence (i.e. ending with .!?) in a given act of Shakespeare
     */
    private static String longestSentenceCount(int actNumber, String bookname)
    {
        try
        {
            bookMap.put("Macbeth", new Scanner(new FileReader("src/GutenbergProject/res/Macbeth.txt")));
            bookMap.put("A Midsummer Night's Dream", new Scanner(new FileReader("src/GutenbergProject/res/Midsummer Night's Dream.txt")));
            bookMap.put("Romeo and Juliet", new Scanner(new FileReader("src/GutenbergProject/res/Romeo and Juliet.txt")));
        } catch (FileNotFoundException e) {throw new RuntimeException(e);}

        Scanner inputStream = bookMap.get(bookname);
        int count = 0;
        inputStream.useDelimiter("ACT");
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        while (inputStream.hasNext())
        {
            hashMap.put("Act " + (count - 1), inputStream.next().substring(actNumber + 1));
            count++;
        }
        int letterCount = 0;
        String longestSentence = null;
        int wordCounter = 0;

        String[] result = hashMap.get("Act " + actNumber).split("[.!?]");
        for (String s : result)
        {
            if (s.length() > letterCount)
            {
                longestSentence = s;
                StringTokenizer tokenizer = new StringTokenizer(s);
                wordCounter = tokenizer.countTokens();
                letterCount = longestSentence.length();
            }
        }

        textArea2.setText(longestSentence);
        return "The longest sentence is shown below, its length is " + (wordCounter) + " words!";
    }
}
