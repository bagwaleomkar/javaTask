import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// BankAccount class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

// ATMInterface class with GUI
public class ATMInterface {
    private JFrame frame;
    private JPanel panel;
    private JTextField amountField;
    private JTextArea displayArea;
    private BankAccount account;

    public ATMInterface() {
        account = new BankAccount(1000);  // Initial balance

        // Frame setup
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Panel setup with GridBagLayout for proper alignment
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0x3E8E41));  // Green background for the panel)

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Adding padding between components

        // Display area for messages
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(0xF1F1F1));  // Light background for text area
        displayArea.setForeground(Color.BLACK);  // Black text for better visibility
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        displayArea.setLineWrap(true);  // Enable line wrapping
        displayArea.setWrapStyleWord(true);  // Wrap at word boundaries
        displayArea.setCaretPosition(displayArea.getDocument().getLength());  // Always show the last line
        displayArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border around displayArea
        gbc.gridwidth = 2;  // Span across 2 columns
        panel.add(new JScrollPane(displayArea), gbc);

        // Label and text field for entering amounts
        gbc.gridwidth = 1;
        panel.add(new JLabel("Enter Amount:"), gbc);

        amountField = new JTextField(15);
        amountField.setBackground(new Color(0xFFFFFF));  // White background for text field
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        // Create buttons with colors and proper alignment
        JButton checkBalanceButton = new JButton("Check Balance");
        styleButton(checkBalanceButton, new Color(0x008CBA), Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(checkBalanceButton, gbc);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("Current Balance: $" + account.getBalance());
            }
        });

        JButton depositButton = new JButton("Deposit");
        styleButton(depositButton, new Color(0x28A745), Color.WHITE);
        gbc.gridy = 3;
        panel.add(depositButton, gbc);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.deposit(amount);
                    displayArea.setText("Deposited: $" + amount + "\nNew Balance: $" + account.getBalance());
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        styleButton(withdrawButton, new Color(0xDC3545), Color.WHITE);
        gbc.gridy = 4;
        panel.add(withdrawButton, gbc);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (account.withdraw(amount)) {
                        displayArea.setText("Withdrew: $" + amount + "\nNew Balance: $" + account.getBalance());
                    } else {
                        displayArea.setText("Insufficient funds or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, new Color(0xFFC107), Color.BLACK);
        gbc.gridy = 5;
        panel.add(exitButton, gbc);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set up the frame with the panel
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to style buttons
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40)); // Button size
    }

    public static void main(String[] args) {
        new ATMInterface();  // Start the ATM interface
    }
}
