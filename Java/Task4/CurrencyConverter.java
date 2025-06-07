import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class CurrencyConverter extends JFrame {

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    // Replace this with your actual API key
    private static final String API_KEY = "ef6e29cbbde13839d011fce1";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        String[] currencies = {"USD", "EUR", "INR", "JPY", "GBP", "CAD"};

        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        amountField = new JTextField();
        resultLabel = new JLabel("Result: ");

        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(e -> convertCurrency());

        add(new JLabel("From:"));
        add(fromCurrency);
        add(new JLabel("To:"));
        add(toCurrency);
        add(new JLabel("Amount:"));
        add(amountField);
        add(convertBtn);
        add(resultLabel);
    }

    private void convertCurrency() {
        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String urlStr = API_URL + from;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStr = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseStr.append(line);
            }

            reader.close();
            String json = responseStr.toString();

            // Simple JSON parsing to find exchange rate
            String search = "\"" + to + "\":";
            int index = json.indexOf(search);
            if (index == -1) {
                throw new Exception("Currency not found");
            }

            int start = index + search.length();
            int end = json.indexOf(",", start);
            if (end == -1) {
                end = json.indexOf("}", start); // last currency in object
            }

            String rateStr = json.substring(start, end).trim();
            double rate = Double.parseDouble(rateStr);

            double result = amount * rate;
            resultLabel.setText("Result: " + String.format("%.2f", result) + " " + to);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching exchange rate", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverter().setVisible(true);
        });
    }
}
