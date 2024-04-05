
/**
 * Author: omteja04
 * Description: UpdateQuantityDB
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UpdateQuantityDB {
    public static boolean updateQuantityDB(Connection connection, String productId, int quantityToAdd) {
        try {
            // Validation for Connection Parameter
            if (connection == null) {
                throw new IllegalArgumentException("Database connection is null");
            }

            // Validation for Product ID Parameter
            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("Product ID is null or empty");
            }

            // Validation for Quantity to Add Parameter
            if (quantityToAdd <= 0) {
                throw new IllegalArgumentException("Quantity to add must be a positive integer");
            }

            // Prepare statement for selecting current quantity
            String selectQuery = "SELECT quantity FROM product WHERE pid = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, productId);
                ResultSet resultSet = selectStatement.executeQuery();

                int currentQuantity = 0;
                while (resultSet.next()) {
                    currentQuantity = resultSet.getInt("quantity");
                }
                resultSet.close();

                // Prepare statement for updating quantity
                int newQuantity = currentQuantity + quantityToAdd;
                String updateQuery = "UPDATE product SET quantity = ? WHERE pid = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, newQuantity);
                    updateStatement.setString(2, productId);
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Quantity updated successfully
                        JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
                        return true;
                    } else {
                        // No rows were affected, product not found
                        JOptionPane.showMessageDialog(null, "Product not found or no changes made.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Log the exception
            JOptionPane.showMessageDialog(null, "Error occurred while updating quantity: " + e.getMessage());
            return false; // Indicate failure to update quantity
        } catch (IllegalArgumentException e) {
            // Handle invalid input parameters
            e.printStackTrace(); // Log the exception
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false; // Indicate failure due to invalid input
        }
    }
}
