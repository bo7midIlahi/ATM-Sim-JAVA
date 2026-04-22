import java.awt.*;
import javax.swing.*;

public class TransferWindow extends JPanel {
    private MainWindow mainWindow;
    private Long cardNumber;          // sender's card number (passed from MainWindow)
    private JTextField amountField;
    private JTextField recipientField;

    public TransferWindow(MainWindow mainWindow, Long cardNumber) {
        this.mainWindow = mainWindow;
        this.cardNumber = cardNumber;

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = mainWindow.createLabel("TRANSFER FUNDS", 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center: input form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Recipient Card Number
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(mainWindow.createLabel("Recipient Card Number:", 18), gbc);
        gbc.gridx = 1;
        recipientField = new JTextField(16);
        recipientField.setFont(new Font("Serif", Font.PLAIN, 18));
        formPanel.add(recipientField, gbc);

        // Amount
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(mainWindow.createLabel("Amount (TND):", 18), gbc);
        gbc.gridx = 1;
        amountField = new JTextField(20);
        amountField.setFont(new Font("Serif", Font.PLAIN, 18));
        formPanel.add(amountField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Bottom: action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton confirmButton = new JButton("Confirm Transfer");
        confirmButton.setFont(new Font("Serif", Font.BOLD, 20));
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);

        // confirmButton.addActionListener(e -> { ... });
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}