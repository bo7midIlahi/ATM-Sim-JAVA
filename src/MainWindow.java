import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow {
    private JFrame window;

    private JButton createButton(String name){
        JButton btn = new JButton(name);
        btn.setPreferredSize(new Dimension(350, 100));
        btn.setFont(new Font("Serif", Font.BOLD, 30));
        btn.setBackground(new Color(0,0,0));
        btn.setForeground(new Color(255, 255, 255)); //set font color
        btn.setFocusable(false); //remove the box around text when clicked

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
    };

    private JLabel createLabel(String message, int size){
        JLabel label = new JLabel(message);
        label.setFont(new Font("Serif", Font.BOLD, size));
        label.setForeground(new Color(0, 0, 0));
        return label;
    }

    public MainWindow(){
        window = new JFrame(); //create window
        window.setTitle("ATM"); //set title for atm
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //set cross button to close window when clicked
        window.setSize(800,600); //set size
        window.setLayout(new BorderLayout(5,5));
        
        JPanel panelText = new JPanel();
        panelText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel label = createLabel("CHOOSE OPERATION",30);
        panelText.add(label);

        JPanel panelFooter = new JPanel();
        panelFooter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel footer = createLabel("this app was made by BO7MID, DO NOT COPY!!",15);
        panelFooter.add(footer);

        JPanel panelButton = new JPanel(); //Panel is a container for other Swing components
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        //panel.setBackground(Color.black);

        //creating buttons
        JButton btnExit = createButton("EXIT");
        JButton btnDepost = createButton("DEPOSIT");
        JButton btnCahsWithdrawl = createButton("CASH WITHDRAWL");
        JButton btnPinChange =  createButton("PIN CHANGE");
        JButton btnBalanceEquity = createButton("BALANCE EQUITY");
        JButton btnTransfer = createButton("TRANSFER");
        JButton btnMiniStatement = createButton("MINI STATEMENT");

        //creating ActionListener for each button
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EXITING APP");
                System.exit(1);
            }
        });

        btnDepost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DEPOSING CASH");
            }
        });

        btnCahsWithdrawl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("WITHDRAWING CASH");
            }
        });

        btnPinChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CHANGING PIN");
            }
        });

        btnBalanceEquity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CHECKING BALANCE");
            }
        });

        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TRANSFERING MONEY");
            }
        });

        btnMiniStatement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("GETTING LAST TRANSACTION");
            }
        });

        //adding buttons to panel
        panelButton.add(btnDepost);
        panelButton.add(btnCahsWithdrawl);
        panelButton.add(btnPinChange);
        panelButton.add(btnBalanceEquity);
        panelButton.add(btnTransfer);
        panelButton.add(btnMiniStatement);
        panelButton.add(btnExit);


        window.add(panelText, BorderLayout.NORTH);
        window.add(panelButton, BorderLayout.CENTER);
        window.add(panelFooter, BorderLayout.SOUTH);

        window.setLocationRelativeTo(null); //center window
    }

    public  void show(){
        window.setVisible(true);
    }
}
