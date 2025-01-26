import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private JFrame frame;
    private JComboBox<String> baseCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JTextField amountField;
    private JTextArea resultArea;
    private JButton convertButton;
    
    // Hardcoded exchange rates (base currency -> target currency)
    private static final Map<String, Map<String, Double>> exchangeRates = new HashMap<>();
    
    static {
        // Exchange rates with USD as the base currency
        Map<String, Double> usdRates = new HashMap<>();
        usdRates.put("USD", 1.0);
        usdRates.put("EUR", 0.92);
        usdRates.put("INR", 76.5);
        usdRates.put("GBP", 0.75);
        usdRates.put("JPY", 132.6);
        usdRates.put("AUD", 1.45);
        
        // Exchange rates with EUR as the base currency
        Map<String, Double> eurRates = new HashMap<>();
        eurRates.put("USD", 1.09);
        eurRates.put("EUR", 1.0);
        eurRates.put("INR", 83.3);
        eurRates.put("GBP", 0.81);
        eurRates.put("JPY", 144.3);
        eurRates.put("AUD", 1.58);
        
        // Add more currencies as needed
        exchangeRates.put("USD", usdRates);
        exchangeRates.put("EUR", eurRates);
    }

    public CurrencyConverter() {
        frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel for UI components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Base Currency selection
        JLabel baseCurrencyLabel = new JLabel("Base Currency:");
        baseCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY", "AUD"});
        panel.add(baseCurrencyLabel);
        panel.add(baseCurrencyComboBox);

        // Target Currency selection
        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        targetCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY", "AUD"});
        panel.add(targetCurrencyLabel);
        panel.add(targetCurrencyComboBox);

        // Amount to Convert
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        panel.add(amountLabel);
        panel.add(amountField);

        // Convert Button
        convertButton = new JButton("Convert");
        panel.add(convertButton);

        // Result area to display conversion
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add ActionListener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseCurrency = baseCurrencyComboBox.getSelectedItem().toString();
                String targetCurrency = targetCurrencyComboBox.getSelectedItem().toString();
                double amount = Double.parseDouble(amountField.getText());

                // Perform conversion
                double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);
                if (convertedAmount != -1) {
                    resultArea.setText(String.format("%.2f %s = %.2f %s", amount, baseCurrency, convertedAmount, targetCurrency));
                } else {
                    resultArea.setText("Conversion rate not available.");
                }
            }
        });

        frame.setVisible(true);
    }

    // Method to perform currency conversion using the hardcoded exchange rates
    private double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
        if (exchangeRates.containsKey(baseCurrency) && exchangeRates.get(baseCurrency).containsKey(targetCurrency)) {
            double rate = exchangeRates.get(baseCurrency).get(targetCurrency);
            return amount * rate;
        }
        return -1;  // Conversion rate not found
    }

    public static void main(String[] args) {
        new CurrencyConverter();  // Start the currency converter app
    }
}
