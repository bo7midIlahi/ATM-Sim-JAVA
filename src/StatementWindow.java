import controllers.ReadTRANSACTION;
import controllers.TransactionDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StatementWindow extends JPanel {
    private MainWindow mainWindow;
    private Long cardNumber;
    private JTable transactionTable;
    private DefaultTableModel tableModel;

    public StatementWindow(MainWindow mainWindow, Long cardNumber) {
        this.mainWindow = mainWindow;
        this.cardNumber = cardNumber;

        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = mainWindow.createLabel("MINI STATEMENT", 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Date", "Description", "Amount (TND)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        transactionTable = new JTable(tableModel);
        transactionTable.setFont(new Font("Serif", Font.PLAIN, 16));
        transactionTable.setRowHeight(25);
        transactionTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Load data
        loadTransactionData();

        // Bottom: Cancel button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton cancelButton = new JButton("Back to Menu");
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(e -> mainWindow.showCard(MainWindow.MENU_CARD));

        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadTransactionData() {
        List<TransactionDTO> transactions = ReadTRANSACTION.getTransactionsForCard(cardNumber);
        for (TransactionDTO t : transactions) {
            String description;
            double amount = t.moneyMoved();

            if (amount >= 0) {
                description = "Transfer from " + t.sender();
            } else {
                description = "Transfer to " + t.sender();
                amount = -amount; // Display as positive
            }

            tableModel.addRow(new Object[]{
                t.date(),
                description,
                String.format("%.2f", amount)
            });
        }

        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No transactions found for this account.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}