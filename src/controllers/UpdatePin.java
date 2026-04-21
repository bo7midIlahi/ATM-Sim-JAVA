package controllers;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdatePin {
    public static boolean update(int pin, Long CardNumber) {
        var url = "jdbc:sqlite:accounts/myDB.db";
        var sql = "UPDATE USER SET PIN = ? WHERE CARD = ?";

        try (
            var conn = DriverManager.getConnection(url);
            var pstmt = conn.prepareStatement(sql)) {
            // set the parameters
            pstmt.setInt(1, pin);
            pstmt.setLong(2, CardNumber); //w/o L the card number is too large for setLong
            // update
            pstmt.executeUpdate();
            System.out.println("UPDATE DONE");
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}