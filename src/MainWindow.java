import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));

        window.add(new JButton("NORTH"), BorderLayout.NORTH);
        window.add(new JButton("WEST"), BorderLayout.WEST);
        window.add(new JButton("EAST"), BorderLayout.EAST);
        window.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        window.add(panel, BorderLayout.CENTER);

        window.setLocationRelativeTo(null); //center window
    }

    public  void show(){
        window.setVisible(true);
    }
}
