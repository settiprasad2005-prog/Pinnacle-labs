import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class FlightReservationSystem extends JFrame {

    private JComboBox<String> fromBox, toBox, classBox;
    private JTextField nameField;
    private JRadioButton windowSeat, middleSeat, aisleSeat;
    private JLabel priceLabel;
    private JTextArea bookingArea;

    public FlightReservationSystem() {

        setTitle("✈ SkyWings Airlines");
        setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header
        JLabel title = new JLabel("✈ SkyWings Airlines - Flight Reservation System",
                SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(41, 128, 185));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(850, 70));

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(245, 245, 245));

        String[] cities = {
                "Hyderabad",
                "Delhi",
                "Mumbai",
                "Bangalore",
                "Chennai",
                "Kolkata"
        };

        fromBox = new JComboBox<>(cities);
        toBox = new JComboBox<>(cities);

        nameField = new JTextField();

        windowSeat = new JRadioButton("Window (+₹500)");
        middleSeat = new JRadioButton("Middle (+₹200)");
        aisleSeat = new JRadioButton("Aisle (+₹300)");

        ButtonGroup seatGroup = new ButtonGroup();
        seatGroup.add(windowSeat);
        seatGroup.add(middleSeat);
        seatGroup.add(aisleSeat);

        classBox = new JComboBox<>(
                new String[]{"Economy", "Business", "First Class"});

        JButton calculateBtn = new JButton("Calculate Fare");
        calculateBtn.setBackground(new Color(52, 152, 219));
        calculateBtn.setForeground(Color.WHITE);

        JButton bookBtn = new JButton("Book Flight");
        bookBtn.setBackground(new Color(46, 204, 113));
        bookBtn.setForeground(Color.WHITE);

        priceLabel = new JLabel("Total Fare: ₹0");
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        formPanel.add(new JLabel("Passenger Name"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("From"));
        formPanel.add(fromBox);

        formPanel.add(new JLabel("To"));
        formPanel.add(toBox);

        formPanel.add(new JLabel("Seat Type"));
        JPanel seatPanel = new JPanel();
        seatPanel.add(windowSeat);
        seatPanel.add(middleSeat);
        seatPanel.add(aisleSeat);
        formPanel.add(seatPanel);

        formPanel.add(new JLabel("Travel Class"));
        formPanel.add(classBox);

        formPanel.add(calculateBtn);
        formPanel.add(priceLabel);

        formPanel.add(new JLabel(""));
        formPanel.add(bookBtn);

        bookingArea = new JTextArea();
        bookingArea.setEditable(false);
        bookingArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(bookingArea);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Booking Details"));

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        calculateBtn.addActionListener(e -> {
            int fare = calculateFare();
            priceLabel.setText("Total Fare: ₹" + fare);
        });

        bookBtn.addActionListener(e -> bookFlight());

        add(mainPanel);
        setVisible(true);
    }

    private int calculateFare() {

        int baseFare = 5000;

        if (windowSeat.isSelected())
            baseFare += 500;
        else if (middleSeat.isSelected())
            baseFare += 200;
        else if (aisleSeat.isSelected())
            baseFare += 300;

        String travelClass = classBox.getSelectedItem().toString();

        if (travelClass.equals("Business"))
            baseFare += 3000;
        else if (travelClass.equals("First Class"))
            baseFare += 6000;

        return baseFare;
    }

    private void bookFlight() {

        String passenger = nameField.getText().trim();

        if (passenger.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter passenger name!");
            return;
        }

        String from = fromBox.getSelectedItem().toString();
        String to = toBox.getSelectedItem().toString();

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Source and Destination cannot be same!");
            return;
        }

        String seat = "Not Selected";

        if (windowSeat.isSelected())
            seat = "Window";
        else if (middleSeat.isSelected())
            seat = "Middle";
        else if (aisleSeat.isSelected())
            seat = "Aisle";

        int fare = calculateFare();

        int ticketNo =
                new Random().nextInt(900000) + 100000;

        JOptionPane.showMessageDialog(
                this,
                "💳 Payment Successful!\n\nBooking Confirmed!");

        bookingArea.setText(
                "====================================\n" +
                "          SKYWINGS AIRLINES\n" +
                "====================================\n" +
                "Ticket No : SW" + ticketNo + "\n\n" +
                "Passenger : " + passenger + "\n" +
                "Route     : " + from + " ➜ " + to + "\n" +
                "Seat      : " + seat + "\n" +
                "Class     : " + classBox.getSelectedItem() + "\n" +
                "Fare      : ₹" + fare + "\n\n" +
                "Status    : CONFIRMED ✅\n" +
                "====================================");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                FlightReservationSystem::new);
    }
}