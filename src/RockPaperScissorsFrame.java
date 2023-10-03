import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * RockPaperScissorsFrame class is the main GUI frame for the Rock, Paper, Scissors game.
 *
 * @author YourName
 */
public class RockPaperScissorsFrame extends JFrame implements ActionListener {
    // Define GUI components
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultArea;
    private JScrollPane scroll;

    // Define game variables
    private int playerWins = 0, computerWins = 0, ties = 0;
    private int playerWinCount = 0, computerWinCount = 0, tieCount = 0;


    /**
     * The constructor initializes the frame and its components.
     */
    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setLayout(new BorderLayout());

        // Button Panel
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon rockIcon = new ImageIcon("src/images/rock.png");
        ImageIcon paperIcon = new ImageIcon("src/images/paper.png");
        ImageIcon scissorsIcon = new ImageIcon("src/images/scissors.png");
        ImageIcon exitIcon = new ImageIcon("src/images/exit.png");


        // make exit icon smaller
        Image img = exitIcon.getImage();
        Image newimg = img.getScaledInstance( 48, 48,  java.awt.Image.SCALE_SMOOTH ) ;
        exitIcon = new ImageIcon( newimg );

        rockButton = new JButton(rockIcon);
        paperButton = new JButton(paperIcon);
        scissorsButton = new JButton(scissorsIcon);
        quitButton = new JButton(exitIcon);

// Add rockButton to buttonPanel at (0,0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(rockButton, gbc);

// Add paperButton to buttonPanel at (1,0)
        gbc.gridx = 1;
        buttonPanel.add(paperButton, gbc);

// Add scissorsButton to buttonPanel at (2,0)
        gbc.gridx = 2;
        buttonPanel.add(scissorsButton, gbc);

// Add quitButton to buttonPanel at (0,1) but make it span 3 cells
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        buttonPanel.add(quitButton, gbc);

        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choices"));
        add(buttonPanel, BorderLayout.NORTH);

        add(buttonPanel, BorderLayout.NORTH);

        // Stats Panel
        JPanel statsPanel = new JPanel();
        statsPanel.add(new JLabel("Player Wins: "));
        playerWinsField = new JTextField(10);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins: "));
        computerWinsField = new JTextField(10);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties: "));
        tiesField = new JTextField(10);
        statsPanel.add(tiesField);
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        add(statsPanel, BorderLayout.SOUTH);

        // Result Panel
        resultArea = new JTextArea(10, 30);
        scroll = new JScrollPane(resultArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);

        // Event Handling
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        quitButton.addActionListener(this);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Handle button events
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = "";
        if (e.getSource() == rockButton) {
            playerChoice = "Rock";
        } else if (e.getSource() == paperButton) {
            playerChoice = "Paper";
        } else if (e.getSource() == scissorsButton) {
            playerChoice = "Scissors";
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }

        // Randomly select a strategy
        String[] strategies = {"LeastUsed", "MostUsed", "LastUsed", "Random", "Cheat"};
        Random rand = new Random();
        String strategy = strategies[rand.nextInt(strategies.length)];

        // Implement your strategies here to decide computerChoice
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[rand.nextInt(3)];  // For demonstration, using Random choice

        // Game logic to determine winner
        String result = "";
        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie";
            tieCount++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock"))) {
            result = playerChoice + " beats " + computerChoice + " (Player wins, strategy: " + strategy + ")";
            playerWinCount++;
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer wins, strategy: " + strategy + ")";
            computerWinCount++;
        }

        // Update stats
        playerWinsField.setText(String.valueOf(playerWinCount));
        computerWinsField.setText(String.valueOf(computerWinCount));
        tiesField.setText(String.valueOf(tieCount));

        // Update result area
        resultArea.append(result + "\n");
    }


    // Other helper methods and game logic can be added here.
}
