
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
    public static void updateQuantityDB(Connection connection, String productId, int quantityToAdd) {
        try {
            PreparedStatement selectStatement = connection
                    .prepareStatement("SELECT quantity FROM product WHERE product_id = ?");
            selectStatement.setString(1, productId);
            ResultSet resultSet = selectStatement.executeQuery();
            int currentQuantity = 0;
            while (resultSet.next()) {
                currentQuantity = resultSet.getInt("quantity");
            }
            resultSet.close();
            selectStatement.close();
            int newQuantity = currentQuantity + quantityToAdd;
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE product SET quantity = ? WHERE product_id = ?");
            updateStatement.setInt(1, newQuantity);
            updateStatement.setString(2, productId);
            updateStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            updateStatement.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}