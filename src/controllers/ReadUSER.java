package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadUSER {
    public static List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String url = "jdbc:sqlite:accounts/myDB.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USER")) {

            while (rs.next()) {
                users.add(new UserDTO(
                    rs.getLong("CARD"),
                    rs.getInt("CVV"),
                    rs.getInt("PIN"),
                    rs.getDouble("BALANCE"),
                    rs.getString("NAME")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}