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

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);

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
}