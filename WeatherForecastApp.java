import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

public class WeatherForecastApp extends JFrame {

    private JTextField cityField;
    private JLabel cityLabel, tempLabel, weatherLabel, humidityLabel, windLabel;

    private static final String API_KEY = "46b1f70c7dbda07e8e1df98717a0b44a";

    public WeatherForecastApp() {

        setTitle("🌦 Weather Forecast Application");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBackground(new Color(135, 206, 250));

        JLabel title = new JLabel("Real-Time Weather Forecast", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        cityField = new JTextField();
        cityField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton searchButton = new JButton("Get Weather");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));

        cityLabel = createLabel("📍 City: ");
        tempLabel = createLabel("🌡 Temperature: ");
        weatherLabel = createLabel("☁ Weather: ");
        humidityLabel = createLabel("💧 Humidity: ");
        windLabel = createLabel("🌬 Wind Speed: ");

        searchButton.addActionListener(e -> getWeather());

        panel.add(title);
        panel.add(cityField);
        panel.add(searchButton);
        panel.add(cityLabel);
        panel.add(tempLabel);
        panel.add(weatherLabel);
        panel.add(humidityLabel);
        panel.add(windLabel);

        add(panel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }

    private void getWeather() {

        String city = cityField.getText().trim();

        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a city name.");
            return;
        }

        try {

            String apiUrl =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                            + city
                            + "&appid="
                            + API_KEY
                            + "&units=metric";

            URL url = new URL(apiUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            String json = response.toString();

            String cityName = extract(json, "\"name\":\"", "\"");
            String temperature = extract(json, "\"temp\":", ",");
            String humidity = extract(json, "\"humidity\":", ",");
            String windSpeed = extract(json, "\"speed\":", "}");
            String weather =
                    extract(json, "\"description\":\"", "\"");

            cityLabel.setText("📍 City: " + cityName);
            tempLabel.setText("🌡 Temperature: " + temperature + " °C");
            weatherLabel.setText("☁ Weather: " + weather);
            humidityLabel.setText("💧 Humidity: " + humidity + "%");
            windLabel.setText("🌬 Wind Speed: " + windSpeed + " m/s");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error retrieving weather data.\n"
                            + "Check your API key and internet connection.");
        }
    }

    private String extract(String text, String start, String end) {

        int startIndex = text.indexOf(start);

        if (startIndex == -1)
            return "N/A";

        startIndex += start.length();

        int endIndex = text.indexOf(end, startIndex);

        if (endIndex == -1)
            return "N/A";

        return text.substring(startIndex, endIndex);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new WeatherForecastApp().setVisible(true);
        });
    }
}