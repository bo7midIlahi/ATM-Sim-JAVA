package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadTRANSACTION {
    public static ResultSet read(Long CardNumber) {
        try {
            Class.forName("org.sqlite.JDBC");

            System.out.println(System.getProperty("user.dir"));
            String url = "jdbc:sqlite:accounts/myDB.db";

            try (
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

                System.out.println("Connected to accounts/myDB.db ✅");

                ResultSet rs = stmt.executeQuery("SELECT * FROM \"TRANSACTION\" WHERE CARD=="+CardNumber+"");
                
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("ID") + " | " +
                        rs.getLong("CARD") + " | " +
                        rs.getString("DATE") + " | " +
                        rs.getDouble("MoneyMoved") + " | " +
                        rs.getString("SENDER") + " | " +
                        rs.getString("RECEIVER")
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