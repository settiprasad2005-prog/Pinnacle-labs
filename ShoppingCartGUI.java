import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartGUI extends JFrame {

    private DefaultTableModel cartModel;
    private JLabel totalLabel;
    private double total = 0;

    public ShoppingCartGUI() {

        setTitle("🛒 ShopEasy Online Store");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header
        JLabel title = new JLabel("🛒 ShopEasy Online Store", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setOpaque(true);
        title.setBackground(new Color(41, 128, 185));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(1200, 70));

        // Sale Banner
        JLabel banner = new JLabel(
                "🔥 Mega Sale - Up to 70% OFF on Electronics 🔥",
                SwingConstants.CENTER);

        banner.setFont(new Font("Segoe UI", Font.BOLD, 18));
        banner.setForeground(Color.WHITE);
        banner.setOpaque(true);
        banner.setBackground(new Color(230, 126, 34));
        banner.setPreferredSize(new Dimension(1200, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(banner, BorderLayout.SOUTH);

        // Product Panel
        JPanel productPanel = new JPanel(new GridLayout(2, 5, 15, 15));
        productPanel.setBorder(BorderFactory.createTitledBorder("🛍 Available Products"));
        productPanel.setBackground(new Color(245, 245, 245));

        addProduct(productPanel, "Laptop", 59999);
        addProduct(productPanel, "Smartphone", 24999);
        addProduct(productPanel, "Smart Watch", 4999);
        addProduct(productPanel, "Headphones", 2999);
        addProduct(productPanel, "Keyboard", 1499);
        addProduct(productPanel, "Mouse", 799);
        addProduct(productPanel, "Gaming Mouse", 1999);
        addProduct(productPanel, "Bluetooth Speaker", 3499);
        addProduct(productPanel, "Power Bank", 1299);
        addProduct(productPanel, "Monitor", 12999);

        // Cart Table
        String[] columns = {"Product", "Price (₹)"};
        cartModel = new DefaultTableModel(columns, 0);

        JTable cartTable = new JTable(cartModel);
        cartTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cartTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("🛒 Shopping Cart"));

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        JButton removeBtn = new JButton("❌ Remove Selected");
        JButton checkoutBtn = new JButton("✅ Checkout");

        removeBtn.setBackground(new Color(231, 76, 60));
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        checkoutBtn.setBackground(new Color(46, 204, 113));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        totalLabel = new JLabel("Total: ₹0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        totalLabel.setForeground(new Color(142, 68, 173));

        removeBtn.addActionListener(e -> {
            int row = cartTable.getSelectedRow();

            if (row >= 0) {
                total -= Double.parseDouble(
                        cartModel.getValueAt(row, 1).toString());

                cartModel.removeRow(row);
                updateTotal();
            }
        });

        checkoutBtn.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "🎉 Order Placed Successfully!\n\nTotal Amount: ₹"
                            + String.format("%.2f", total)
                            + "\n\nThank you for shopping with ShopEasy!",
                    "Order Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);

            cartModel.setRowCount(0);
            total = 0;
            updateTotal();
        });

        bottomPanel.add(removeBtn);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(totalLabel);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(checkoutBtn);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(productPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addProduct(JPanel panel, String name, double price) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10)));

        JLabel icon = new JLabel("🛍", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));

        JLabel productName = new JLabel(name, SwingConstants.CENTER);
        productName.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel productPrice = new JLabel(
                "₹" + String.format("%.0f", price),
                SwingConstants.CENTER);

        productPrice.setForeground(new Color(231, 76, 60));
        productPrice.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton addBtn = new JButton("Add To Cart");

        addBtn.setBackground(new Color(46, 204, 113));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addBtn.setFocusPainted(false);

        addBtn.addActionListener(e -> {
            cartModel.addRow(new Object[]{name, price});
            total += price;
            updateTotal();
        });

        card.add(icon, BorderLayout.NORTH);
        card.add(productName, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.setBackground(Color.WHITE);

        bottom.add(productPrice);
        bottom.add(addBtn);

        card.add(bottom, BorderLayout.SOUTH);

        panel.add(card);
    }

    private void updateTotal() {
        totalLabel.setText(
                "Total: ₹" + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new ShoppingCartGUI());
    }
}