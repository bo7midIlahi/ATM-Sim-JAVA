package controllers;

import java.sql.DriverManager;
import java.sql.SQLException;

public class TransferMoney {
    public static boolean transfer(Long CardNumber, String Date, float amount, String Snder, Long CardDestination) {

        String url = "jdbc:sqlite:accounts/myDB.db";
        String sql = "INSERT INTO \"TRANSACTION\" (CARD,DATE,MoneyMoved,SENDER,RECEIVERCARD) VALUES(?,?,?,?,?)";

        try (
            var conn = DriverManager.getConnection(url);
            var pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, CardNumber);
            pstmt.setString(2, Date);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, Snder);
            pstmt.setLong(5, CardDestination);
            pstmt.executeUpdate();
            
            System.out.println("TRANSACTION SUCCESSEFUL");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
