
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
    static boolean addToExisting;

    public static void main(String[] args) {
        updateDialogue();
    }

    public static void updateDialogue() {

        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            productID = JOptionPane.showInputDialog("Enter The Product ID : ");
            quantityToBeAdded = JOptionPane.showInputDialog("Enter The New Quantity : ");

            
            try {
                quantity = Integer.parseInt(quantityToBeAdded);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity should be a numeric value.");
                return; 
            }
            int option = JOptionPane.showConfirmDialog(null, "Do you want to add to the existing quantity?",
                    "Choose an Option", JOptionPane.YES_NO_OPTION);
            addToExisting = (option == JOptionPane.YES_OPTION);
        }
        UpdateQuantityDB.updateQuantityDB(connection, productID, quantity, addToExisting);

    }
}
