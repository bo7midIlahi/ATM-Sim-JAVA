package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTRANSACTION {
    public static List<TransactionDTO> getTransactionsForCard(Long cardNumber) {
        List<TransactionDTO> transactions = new ArrayList<>();
        String url = "jdbc:sqlite:accounts/myDB.db";
        String sql = "SELECT * FROM \"TRANSACTION\" WHERE CARD = ? ORDER BY ID DESC";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, cardNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new TransactionDTO(
                        rs.getInt("ID"),
                        rs.getLong("CARD"),
                        rs.getString("DATE"),
                        rs.getDouble("MoneyMoved"),
                        rs.getString("SENDER"),
                        rs.getLong("RECEIVERCARD")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}