import controllers.ReadUSER;
import controllers.UserDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class BalanceWindow extends JPanel {
    private MainWindow mainWindow;
    private JLabel amountField;
    private Long cardNumber;   

    public BalanceWindow(MainWindow mainWindow, Long cardNumber) {
        this.mainWindow = mainWindow;
        this.cardNumber = cardNumber;

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = mainWindow.createLabel("BALANCE", 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center: input field
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel label = mainWindow.createLabel("Your Balance: ", 30);
        amountField = new JLabel();
        amountField.setFont(new Font("Serif", Font.PLAIN, 30));
        amountField.setText(getBalance(cardNumber));
        centerPanel.add(label);
        centerPanel.add(amountField);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom: action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        /*
        JButton depositButton = new JButton("Confirm Deposit");
        depositButton.setFont(new Font("Serif", Font.BOLD, 20));
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusable(false);
        */

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);

        // use lambda with no extra parameters
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String getBalance(Long cardNumber){
        String text = "";
        try {
            List<UserDTO> users = ReadUSER.getAllUsers();
            for (UserDTO user : users) {
                if (user.card() == cardNumber) {
                    text = String.valueOf(user.balance());
                    break;
                }
            }
            return text;            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Cannot Get .",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return text;
    }

    /*
    // update balance using the database helper
    private void updateBalance(double newBalance) {
        if (UpdateBalance.update(newBalance, cardNumber)) {
            System.out.println("Balance updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update balance in database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // actionListener for deposit button – signature must be (ActionEvent e)
    private void handleDeposit(ActionEvent e) {
        String text = amountField.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter an amount.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(text);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Amount must be positive.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // get current balance from database
            double currentBalance = 0;
            List<UserDTO> users = ReadUSER.getAllUsers();
            for (UserDTO user : users) {
                if (user.card() == cardNumber) {
                    currentBalance = user.balance();
                    break;
                }
            }

            double newBalance = currentBalance + amount;

            // apdate database
            updateBalance(newBalance);

            JOptionPane.showMessageDialog(this,
                    "Deposited: " + amount + " TND\nNew balance: " + newBalance + " TND",
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
    */
}