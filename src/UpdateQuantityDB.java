
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
    public static boolean updateQuantityDB(Connection connection, String productId, int quantityToUpdate,
            boolean addToExisting) {
        try {

            if (connection == null) {
                throw new IllegalArgumentException("Database connection is null");
            }

            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("Product ID is null or empty");
            }

            if (quantityToUpdate <= 0) {
                throw new IllegalArgumentException("Quantity to update must be a positive integer");
            }

            String selectQuery = "SELECT quantity FROM product WHERE pid = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, productId);
                ResultSet resultSet = selectStatement.executeQuery();

                int currentQuantity = 0;
                while (resultSet.next()) {
                    currentQuantity = resultSet.getInt("quantity");
                }
                resultSet.close();

                int newQuantity;
                if (addToExisting) {
                    newQuantity = currentQuantity + quantityToUpdate;
                } else {
                    newQuantity = quantityToUpdate;
                }

                String updateQuery = "UPDATE product SET quantity = ? WHERE pid = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, newQuantity);
                    updateStatement.setString(2, productId);
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {

                        JOptionPane.showMessageDialog(null, "Quantity updated successfully.");
                        return true;
                    } else {

                        JOptionPane.showMessageDialog(null, "Product not found or no changes made.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while updating quantity: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
