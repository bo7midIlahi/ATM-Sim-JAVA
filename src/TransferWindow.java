import controllers.ReadUSER;
import controllers.TransferMoney;
import controllers.UpdateBalance;
import controllers.UserDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
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

        confirmButton.addActionListener(this::handleTransit);
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleTransit(ActionEvent e) {
    String receiverCard = recipientField.getText().trim();
    String money = amountField.getText().trim();

    if (receiverCard.isEmpty() || money.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "All fields are required.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Long rxCard = Long.parseLong(receiverCard);
        float amount = Float.parseFloat(money);

        if (amount <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Amount must be positive.",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prevent self-transfer
        if (cardNumber.equals(rxCard)) {
            JOptionPane.showMessageDialog(this,
                    "Cannot transfer to your own account.",
                    "Transfer Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<UserDTO> users = ReadUSER.getAllUsers();
        UserDTO sender = null;
        UserDTO receiver = null;

        for (UserDTO user : users) {
            if (user.card() == cardNumber) {
                sender = user;
            }
            if (user.card() == rxCard) {
                receiver = user;
            }
        }

        if (sender == null) {
            JOptionPane.showMessageDialog(this,
                    "Sender account not found.",
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (receiver == null) {
            JOptionPane.showMessageDialog(this,
                    "Recipient account does not exist.",
                    "Transfer Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sender.balance() < amount) {
            JOptionPane.showMessageDialog(this,
                    "Insufficient balance.",
                    "Transfer Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform updates
        boolean senderUpdated = UpdateBalance.update(sender.balance() - amount, cardNumber);
        boolean receiverUpdated = UpdateBalance.update(receiver.balance() + amount, rxCard);

        if (!senderUpdated || !receiverUpdated) {
            // Attempt to rollback if one failed
            if (senderUpdated) {
                UpdateBalance.update(sender.balance(), cardNumber);
            }
            JOptionPane.showMessageDialog(this,
                    "Transfer failed due to a database error.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Record the transaction
        TransferMoney.transfer(cardNumber, LocalDate.now().toString(), amount, receiver.name(), rxCard);

        JOptionPane.showMessageDialog(this,
                String.format("Transferred %.2f TND to %s", amount, receiver.name()),
                "Transfer Successful",
                JOptionPane.INFORMATION_MESSAGE);

        // Clear fields and return to menu
        recipientField.setText("");
        amountField.setText("");
        mainWindow.showCard(MainWindow.MENU_CARD);

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Invalid number format. Please enter numeric values.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
}