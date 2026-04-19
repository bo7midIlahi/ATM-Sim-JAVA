import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        return btn;
    };

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
        JButton btnExit = createButton("EXIT");
        JButton btnDepost = createButton("DEPOSIT");
        JButton btnCahsWithdrawl = createButton("CASH WITHDRAWL");
        JButton btnPinChange =  createButton("PIN CHANGE");
        JButton btnBalanceEquity = createButton("BALANCE EQUITY");
        JButton btnTransfer = createButton("TRANSFER");
        JButton btnMiniStatement = createButton("MINI STATEMENT");

        //adding buttons to panel
        panel.add(btnDepost);
        panel.add(btnCahsWithdrawl);
        panel.add(btnPinChange);
        panel.add(btnBalanceEquity);
        panel.add(btnTransfer);
        panel.add(btnMiniStatement);
        panel.add(btnExit);


        window.add(new JButton("NORTH"), BorderLayout.NORTH);
        window.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        window.add(panel, BorderLayout.CENTER);

        window.setLocationRelativeTo(null); //center window
    }

    public  void show(){
        window.setVisible(true);
    }
}
