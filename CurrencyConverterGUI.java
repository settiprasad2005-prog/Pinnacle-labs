import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverterGUI extends JFrame {

    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;

    private HashMap<String, Double> rates = new HashMap<>();

    public CurrencyConverterGUI() {

        // Exchange Rates (Base: USD)
        rates.put("USD", 1.0);
        rates.put("INR", 83.20);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.79);
        rates.put("JPY", 156.40);
        rates.put("AUD", 1.52);
        rates.put("CAD", 1.37);
        rates.put("CHF", 0.91);
        rates.put("CNY", 7.24);
        rates.put("SGD", 1.35);
        rates.put("AED", 3.67);
        rates.put("SAR", 3.75);
        rates.put("NZD", 1.66);
        rates.put("ZAR", 18.40);
        rates.put("KRW", 1380.50);
        rates.put("RUB", 89.50);
        rates.put("BRL", 5.40);
        rates.put("MXN", 18.20);

        setTitle("🌎 Currency Converter");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(25, 25, 50));

        JLabel title = new JLabel("🌎 Currency Converter");
        title.setBounds(150, 20, 400, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        panel.add(title);

        JLabel amountLabel = new JLabel("Enter Amount");
        amountLabel.setBounds(70, 90, 150, 30);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(250, 90, 280, 35);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setBorder(new LineBorder(new Color(0, 255, 200), 2));
        panel.add(amountField);

        String[] currencies = {
                "USD", "INR", "EUR", "GBP", "JPY",
                "AUD", "CAD", "CHF", "CNY", "SGD",
                "AED", "SAR", "NZD", "ZAR", "KRW",
                "RUB", "BRL", "MXN"
        };

        JLabel fromLabel = new JLabel("From Currency");
        fromLabel.setBounds(70, 150, 150, 30);
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(fromLabel);

        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(250, 150, 280, 35);
        fromCurrency.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        panel.add(fromCurrency);

        JLabel toLabel = new JLabel("To Currency");
        toLabel.setBounds(70, 210, 150, 30);
        toLabel.setForeground(Color.WHITE);
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(toLabel);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(250, 210, 280, 35);
        toCurrency.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        panel.add(toCurrency);

        JButton convertButton = new JButton("Convert Currency");
        convertButton.setBounds(210, 290, 220, 50);
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        convertButton.setBackground(new Color(0, 200, 120));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(new LineBorder(Color.WHITE, 2));
        panel.add(convertButton);

        resultLabel = new JLabel("Converted Amount Will Appear Here");
        resultLabel.setBounds(80, 380, 500, 35);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(new Color(255, 215, 0));
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();

            double usdAmount = amount / rates.get(from);
            double result = usdAmount * rates.get(to);

            resultLabel.setText(
                    String.format("%.2f %s = %.2f %s",
                            amount, from, result, to));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new CurrencyConverterGUI());
    }
}