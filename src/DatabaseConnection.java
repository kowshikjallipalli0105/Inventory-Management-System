
/**
 * Author: omteja04
 * Description: DatabaseConnection
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/ims";
    private static String username = "omteja";
    private static String password = "teja1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Welcome To Inventory Management System", "Successfully Connected",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                JOptionPane.showMessageDialog(null, "Exited", "Successfully Closed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
