package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadUSER {
    public static ResultSet main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");

            System.out.println(System.getProperty("user.dir"));
            String url = "jdbc:sqlite:accounts/myDB.db";

            try (
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

                System.out.println("Connected to accounts/myDB.db ✅");

                ResultSet rs = stmt.executeQuery("SELECT * FROM \"USER\"");
                
                while (rs.next()) {
                    System.out.println(
                        rs.getLong("CARD") + " | " +
                        rs.getInt("CVV") + " | " +
                        rs.getInt("PIN") + " | " +
                        rs.getDouble("BALANCE") + " | " +
                        rs.getString("NAME")
                    );
                }
                return rs;
            }

        } catch (Exception e) {
            e.printStackTrace();
            ResultSet rs = null;
            return rs;
        }
    }
}