import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

public class DepositWindow extends JPanel {
    private MainWindow mainWindow;
    private JTextField amountField;
    private File accountFile;

    public DepositWindow(MainWindow mainWindow, Long CardNumber) {
        this.mainWindow = mainWindow;
        this.accountFile = accountFile;

        setLayout(new BorderLayout(10, 10));

        //title
        JLabel titleLabel = mainWindow.createLabel("DEPOSIT FUNDS", 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        //centering input field
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel label = mainWindow.createLabel("Enter amount: ", 20);
        amountField = new JTextField(20);
        amountField.setFont(new Font("Serif", Font.PLAIN, 25));
        centerPanel.add(label);
        centerPanel.add(amountField);
        add(centerPanel, BorderLayout.CENTER);

        //action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton depositButton = new JButton("Confirm Deposit");
        depositButton.setFont(new Font("Serif", Font.BOLD, 20));
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);

        depositButton.addActionListener(this::handleDeposit);
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(depositButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateBalance(double newBalance, File accountFile) {
        try {
            var lines = Files.readAllLines(accountFile.toPath());
            if (lines.size() < 3) {
                JOptionPane.showMessageDialog(this,
                        "Account file is corrupted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Update the third line (index 2)
            lines.set(3, String.valueOf(newBalance));
            Files.write(accountFile.toPath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to update balance: " + e.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeposit(ActionEvent e) {
        String text = amountField.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.", "Error", JOptionPane.ERROR_MESSAGE);
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

            // Read current balance
            try {
                var lines = Files.readAllLines(accountFile.toPath());
                if (lines.size() < 3) {
                JOptionPane.showMessageDialog(this,
                        "Account file is corrupted.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                        double currentBalance = Double.parseDouble(lines.get(3).trim());
            double newBalance = currentBalance + amount;
                // Update the file
            updateBalance(newBalance, accountFile);

            JOptionPane.showMessageDialog(this,
                    "Deposited: " + amount + " TND\nNew balance: " + newBalance + " TND",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            amountField.setText("");
            mainWindow.showCard(MainWindow.MENU_CARD); // go back to menu after deposit
            }
            
            } catch (Exception e2) {
            }
            
            

            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }/* catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Could not read account file.",
                "File Error",
                JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
