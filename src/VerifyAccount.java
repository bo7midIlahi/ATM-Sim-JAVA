import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VerifyAccount {
    private JFrame window;
    private JTextField cardNumberField;
    private JTextField cvvField;
    private JPasswordField pinField;

    //helper method to create styled labels
    public JLabel createLabel(String message, int size) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Serif", Font.BOLD, size));
        label.setForeground(new Color(0, 0, 0));
        return label;
    }

    //helper method to create styled buttons
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setFont(new Font("Serif", Font.BOLD, 18));
        btn.setBackground(new Color(0, 0, 0));
        btn.setForeground(new Color(255, 255, 255));
        btn.setFocusable(false);
        return btn;
    }

    public VerifyAccount() {
        window = new JFrame("ATM - Account Verification");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 350);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout(10, 10));

        //create main panel with GridBagLayout for form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //title label
        JLabel titleLabel = createLabel("WELCOME TO BO7MID ATM", 24);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // width=two columns
        formPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        //card Number
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(createLabel("Card Number:", 16), gbc);
        gbc.gridx = 1;
        cardNumberField = new JTextField(16);
        cardNumberField.setFont(new Font("Serif", Font.PLAIN, 16));
        formPanel.add(cardNumberField, gbc);

        //cvv
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(createLabel("CVV:", 16), gbc);
        gbc.gridx = 1;
        cvvField = new JTextField(3);
        cvvField.setFont(new Font("Serif", Font.PLAIN, 16));
        formPanel.add(cvvField, gbc);

        //pin
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(createLabel("PIN:", 16), gbc);
        gbc.gridx = 1;
        pinField = new JPasswordField(4);
        pinField.setFont(new Font("Serif", Font.PLAIN, 16));
        formPanel.add(pinField, gbc);

        //button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton checkButton = createButton("CHECK");
        JButton exitButton = createButton("EXIT");

        buttonPanel.add(checkButton);
        buttonPanel.add(exitButton);

        // Add panels to window
        window.add(formPanel, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        // Footer with creator credit
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JLabel footer = createLabel("this app was made by BO7MID, DO NOT COPY!!", 12);
        footerPanel.add(footer);
        window.add(footerPanel, BorderLayout.NORTH);
        
        JPanel southContainer = new JPanel(new BorderLayout());
        southContainer.add(buttonPanel, BorderLayout.CENTER);
        southContainer.add(footerPanel, BorderLayout.SOUTH);
        window.add(southContainer, BorderLayout.SOUTH);

        //action listeners
        checkButton.addActionListener(this::handleCheck);
        exitButton.addActionListener(e -> System.exit(0));
    }

    private boolean verifyCredentials(File accountFile, String cvv, String pin){
        try {
            List<String> lines = Files.readAllLines(accountFile.toPath());
            if (lines.size() < 2) {
                throw new IOException("Invalid account file format.");
            }
            String storedCvv = lines.get(1).trim();
            String storedPin = lines.get(2).trim();
            System.out.println("storedCvv = " + storedCvv);
            System.out.println("storedPin = " + storedPin);

            if (cvv.equals(storedCvv) && pin.equals(storedPin)) {
                JOptionPane.showMessageDialog(window,
                        "Verification successful!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE);
                window.dispose();
                new MainWindow(accountFile).show();
            } else {
                JOptionPane.showMessageDialog(window,
                        "Invalid CVV or PIN.",
                        "Verification Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(window,
                    "Error reading account file.",
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void handleCheck(ActionEvent e) {
        String cardNumber = cardNumberField.getText().trim();
        System.out.println("card N°: "+cardNumber);
        String cvv = cvvField.getText().trim();
        System.out.println("card cvv: "+cvv);
        String pin = new String(pinField.getPassword()).trim();
        System.out.println("card pin: "+pin);

        // 
        if (cardNumber.isEmpty() || cvv.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(window,
                    "All fields are required.",
                    "Verification Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cardNumber.matches("\\d{16}")) {
            JOptionPane.showMessageDialog(window,
                    "Card number must be 16 digits.",
                    "Invalid Card Number",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cvv.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(window,
                    "CVV must be 3 digits.",
                    "Invalid CVV",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!pin.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(window,
                    "PIN must be 4 digits.",
                    "Invalid PIN",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //getting card number
        File accountFile = new File("accounts", cardNumber + ".txt");
        if (accountFile.exists() && verifyCredentials(accountFile,cvv,pin)) {
            System.out.println("WELCOME - file found: " + accountFile.getName());
            
        } else {
            System.out.println("No account file found for card number: " + cardNumber);
        }

        //debugging:card "1234567812345678", cvv "123", pin "1234"
        if (cardNumber.equals("1234567812345678") && cvv.equals("123") && pin.equals("1234")) {
            JOptionPane.showMessageDialog(window,
                    "Verification successful!",
                    "Welcome",
                    JOptionPane.INFORMATION_MESSAGE);
            window.dispose(); // close verification window
            // Open main ATM window
            MainWindow mainWindow = new MainWindow(accountFile);
            mainWindow.show();
        } else {
            JOptionPane.showMessageDialog(window,
                    "Invalid card details. Please try again.",
                    "Verification Failed",
                    JOptionPane.ERROR_MESSAGE);
            
            //clear fields for retry
            //cardNumberField.setText("");
            cvvField.setText("");
            pinField.setText("");
        }
    }

    public void show() {
        window.setVisible(true);
    }
}