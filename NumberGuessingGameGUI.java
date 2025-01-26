import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {

    private int numberToGuess;
    private int attemptsLeft;
    private int totalScore;
    private boolean guessedCorrectly;

    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JTextField guessInput;
    private JButton guessButton;
    private JButton resetButton;

    public NumberGuessingGameGUI() {
        // Initialize game variables
        resetGame();

        // Set up the GUI
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        messageLabel = new JLabel("Guess a number between 1 and 100!");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(messageLabel);

        guessInput = new JTextField();
        guessInput.setMaximumSize(new Dimension(200, 30));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessInput);

        guessButton = new JButton("Guess");
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessButton.addActionListener(new GuessButtonListener());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessButton);

        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(attemptsLabel);

        scoreLabel = new JLabel("Score: " + totalScore);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(scoreLabel);

        resetButton = new JButton("Reset Game");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.addActionListener(e -> resetGame());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(resetButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void resetGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attemptsLeft = 5;
        guessedCorrectly = false;
        totalScore = 0;
        if (messageLabel != null) {
            messageLabel.setText("Guess a number between 1 and 100!");
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            scoreLabel.setText("Score: " + totalScore);
            guessInput.setText("");
        }
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (guessedCorrectly || attemptsLeft == 0) {
                messageLabel.setText("Game over! Reset to play again.");
                return;
            }

            try {
                int userGuess = Integer.parseInt(guessInput.getText());
                if (userGuess == numberToGuess) {
                    messageLabel.setText("Correct! You guessed the number.");
                    totalScore += 10;
                    guessedCorrectly = true;
                } else if (userGuess > numberToGuess) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    messageLabel.setText("Too low! Try again.");
                }

                attemptsLeft--;
                attemptsLabel.setText("Attempts left: " + attemptsLeft);

                if (attemptsLeft == 0 && !guessedCorrectly) {
                    messageLabel.setText("Out of attempts! The number was: " + numberToGuess);
                }

                scoreLabel.setText("Score: " + totalScore);
                guessInput.setText("");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameGUI::new);
    }
}
