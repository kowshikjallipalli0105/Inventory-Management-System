
/**
 * Author: omteja04
 * Description: UpdateQuantityGUI
 */
import java.sql.Connection;
import javax.swing.JOptionPane;

public class UpdateQuantityGUI {
    static String productID;
    static String quantityToBeAdded;

    static int quantity;

    public static void main(String[] args) {
        updateDialogue();
    }

    public static void updateDialogue() {

        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            productID = JOptionPane.showInputDialog("Enter The Product ID : ");
            quantityToBeAdded = JOptionPane.showInputDialog("Enter The New Quantity : ");

            // Handle invalid input
            try {
                quantity = Integer.parseInt(quantityToBeAdded);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity should be a numeric value.");
                return; // Exit the method if the quantity is not numeric
            }
        }
        UpdateQuantityDB.updateQuantityDB(connection, productID, quantity);
        // if (UpdateQuantityDB.updateQuantityDB(connection, productID, quantity)) {
        // JOptionPane.showMessageDialog(null, "SuccessFully Updated");
        // } else {
        // JOptionPane.showMessageDialog(null, "Updating Failed");
        // }
    }
}
