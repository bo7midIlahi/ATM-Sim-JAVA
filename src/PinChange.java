import controllers.ReadUSER;
import controllers.UpdatePin;
import controllers.UserDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class PinChange extends JPanel {
    private MainWindow mainWindow;
    private JTextField amountField;
    private Long cardNumber;   // Store the card number as a field

    public PinChange(MainWindow mainWindow, Long cardNumber) {
        this.mainWindow = mainWindow;
        this.cardNumber = cardNumber;

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = mainWindow.createLabel("PIN CHANGE", 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center: input field
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel label = mainWindow.createLabel("Enter new PIN: ", 20);
        amountField = new JTextField(20);
        amountField.setFont(new Font("Serif", Font.PLAIN, 25));
        centerPanel.add(label);
        centerPanel.add(amountField);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom: action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton depositButton = new JButton("Confirm PIN Change");
        depositButton.setFont(new Font("Serif", Font.BOLD, 20));
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);

        // use lambda with no extra parameters
        depositButton.addActionListener(this::handleDeposit);
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(depositButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // update balance using the database helper
    private void updatePin(int pin) {
        if (UpdatePin.update(pin, cardNumber)) {
            System.out.println("PIN updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update PIN in database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeposit(ActionEvent e) {
        String text = amountField.getText();
        if (!text.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter an valid 4 digit PIN.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int pin = Integer.parseInt(text);
            if (pin <= 0) {
                JOptionPane.showMessageDialog(this,
                        "PIN must be positive.",
                        "Invalid PIN",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // get current card from database
            List<UserDTO> users = ReadUSER.getAllUsers();
            for (UserDTO user : users) {
                if (user.card() == cardNumber) {
                    break;
                }
            }

            // apdate database
            updatePin(pin);

            JOptionPane.showMessageDialog(this,
                    "New PIN: " + pin,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            amountField.setText("");
            mainWindow.showCard(MainWindow.MENU_CARD); // return to main menu

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}