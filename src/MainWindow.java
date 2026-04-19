import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow {
    private JFrame window;
    public MainWindow(){
        window = new JFrame(); //create window
        window.setTitle("ATM"); //set title for atm
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //set cross button to close window when clicked
        window.setSize(800,600); //set size
        window.setLayout(new BorderLayout(5,5));

        JPanel panel = new JPanel(); //Panel is a container for other Swing components
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        //panel.setBackground(Color.black);

        //creating buttons
        Button btnDepost = new Button("DEPOSIT");
        Button btnCahsWithdrawl = new Button("CASH WITHDRAWL");
        Button btnPinChange = new Button("PIN CHANGE");
        Button btnBalanceEquity = new Button("BALANCE EQUITY");
        Button btnTransfer = new Button("TRANSFER");
        Button btnMiniStatement = new Button("MINI STATEMENT");
        
        //setting size for btns
        btnDepost.setPreferredSize(new Dimension(350, 100));
        btnCahsWithdrawl.setPreferredSize(new Dimension(350, 100));
        btnPinChange.setPreferredSize(new Dimension(350, 100));
        btnBalanceEquity.setPreferredSize(new Dimension(350, 100));
        btnTransfer.setPreferredSize(new Dimension(350, 100));
        btnMiniStatement.setPreferredSize(new Dimension(350, 100));

        //setting fonst size
        btnDepost.setFont(new Font("Serif", Font.BOLD, 30));
        btnCahsWithdrawl.setFont(new Font("Serif", Font.BOLD, 30));
        btnPinChange.setFont(new Font("Serif", Font.BOLD, 30));
        btnBalanceEquity.setFont(new Font("Serif", Font.BOLD, 30));
        btnTransfer.setFont(new Font("Serif", Font.BOLD, 30));
        btnMiniStatement.setFont(new Font("Serif", Font.BOLD, 30));

        //adding buttons to panel
        panel.add(btnDepost);
        panel.add(btnCahsWithdrawl);
        panel.add(btnPinChange);
        panel.add(btnBalanceEquity);
        panel.add(btnTransfer);
        panel.add(btnMiniStatement);


        window.add(new JButton("NORTH"), BorderLayout.NORTH);
        window.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        window.add(panel, BorderLayout.CENTER);

        window.setLocationRelativeTo(null); //center window
    }

    public  void show(){
        window.setVisible(true);
    }
}
