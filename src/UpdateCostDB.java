import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UpdateCostDB {

    public static boolean updateCostDB(Connection connection, String productID, int cost) {
        try {

            if (connection == null) {
                throw new IllegalArgumentException("Database connection is null");
            }

            if (productID == null || productID.isEmpty()) {
                throw new IllegalArgumentException("Product ID is null or empty");
            }

            if (cost <= 0) {
                throw new IllegalArgumentException("Cost to modify must be a positive integer");
            }

            String updateQuery = "UPDATE product SET cost = ? WHERE pid = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, cost);
                updateStatement.setString(2, productID);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {

                    JOptionPane.showMessageDialog(null, "Cost updated successfully.");
                    return true;
                } else {

                    JOptionPane.showMessageDialog(null, "Product not found or no changes made.");
                    return false;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while updating cost: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

}
