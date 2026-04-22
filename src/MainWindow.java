import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow {
    private JFrame window;
    private JPanel mainPanel;        //hold all "cards"
    private CardLayout cardLayout;   //The layout manager that switches cards

    //constants for card names
    public static final String MENU_CARD = "Menu";
    public static final String DEPOSIT_CARD = "Deposit";
    public static final String PIN_CARD = "Pin";

    //styling helper
    public JButton createButton(String name) {
        JButton btn = new JButton(name);
        btn.setPreferredSize(new Dimension(350, 100));
        btn.setFont(new Font("Serif", Font.BOLD, 30));
        btn.setBackground(new Color(0,0,0));
        btn.setForeground(new Color(255, 255, 255));
        btn.setFocusable(false);
        switch (name) {
            case "EXIT":
                btn.setToolTipText("close the program");
                break;
            case "DEPOSIT":
                btn.setToolTipText("time to PUT some CHEESE on dat account");
                break;
            case "CASH WITHDRAWL":
                btn.setToolTipText("pay them bills yo BROKEY");
                break;
            case "PIN CHANGE":
                btn.setToolTipText("manage security yo IDIOT");
                break;
            case "BALANCE EQUITY":
                btn.setToolTipText("see your balance PEASANT");
                break;
            case "TRANSFER":
                btn.setToolTipText("give people their money yo THIEF");
                break;
            case "MINI STATEMENT":
                btn.setToolTipText("watch your expenses BALVAN");
                break;
            default:
                break;
        }
        return btn;
    }

    public JLabel createLabel(String message, int size) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Serif", Font.BOLD, size));
        label.setForeground(new Color(0, 0, 0));
        return label;
    }

    public MainWindow(Long CardNumber) {
        window = new JFrame();
        window.setTitle("ATM");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);

        //create the CardLayout and the main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //create the individual card panels
        JPanel menuPanel = createMenuPanel();
        JPanel depositPanel = new DepositWindow(this, CardNumber); // pass MainWindow reference so DepositPanel can switch back
        JPanel pinChange = new PinChange(this, CardNumber); // pass MainWindow reference so DepositPanel can switch back

        //add cards to the main panel
        mainPanel.add(menuPanel, MENU_CARD);
        mainPanel.add(depositPanel, DEPOSIT_CARD);
        mainPanel.add(pinChange, PIN_CARD);

        //add the main panel to the window
        window.add(mainPanel);

        //centering window
        window.setLocationRelativeTo(null);
    }

    //create the main menu panel
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel panelText = new JPanel();
        panelText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel label = createLabel("CHOOSE OPERATION", 30);
        panelText.add(label);

        JPanel panelFooter = new JPanel();
        panelFooter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel footer = createLabel("this app was made by BO7MID, DO NOT COPY!!", 15);
        panelFooter.add(footer);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton btnExit = createButton("EXIT");
        JButton btnDeposit = createButton("DEPOSIT");
        JButton btnCashWithdrawal = createButton("CASH WITHDRAWL");
        JButton btnPinChange = createButton("PIN CHANGE");
        JButton btnBalanceEquity = createButton("BALANCE EQUITY");
        JButton btnTransfer = createButton("TRANSFER");
        JButton btnMiniStatement = createButton("MINI STATEMENT");

        //button functionalities
        btnExit.addActionListener(e -> System.exit(0));

        //switch to the deposit card
        btnDeposit.addActionListener(e -> cardLayout.show(mainPanel, DEPOSIT_CARD));
        btnCashWithdrawal.addActionListener(e -> System.out.println("WITHDRAWING CASH"));
        btnPinChange.addActionListener(e -> cardLayout.show(mainPanel, PIN_CARD));
        btnBalanceEquity.addActionListener(e -> System.out.println("CHECKING BALANCE"));
        btnTransfer.addActionListener(e -> System.out.println("TRANSFERING MONEY"));
        btnMiniStatement.addActionListener(e -> System.out.println("GETTING LAST TRANSACTION"));

        panelButton.add(btnDeposit);
        panelButton.add(btnCashWithdrawal);
        panelButton.add(btnPinChange);
        panelButton.add(btnBalanceEquity);
        panelButton.add(btnTransfer);
        panelButton.add(btnMiniStatement);
        panelButton.add(btnExit);

        panel.add(panelText, BorderLayout.NORTH);
        panel.add(panelButton, BorderLayout.CENTER);
        panel.add(panelFooter, BorderLayout.SOUTH);

        return panel;
    }

    //method to switch cards from outside
    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    public void show() {
        window.setVisible(true);
    }
}