
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
        productID = JOptionPane.showInputDialog("Enter The Product ID : ");
        quantityToBeAdded = JOptionPane.showInputDialog("Enter The New Quantity : ");
        quantity = Integer.parseInt(quantityToBeAdded);
        UpdateQuantityDB.updateQuantityDB(connection, productID, quantity);
    }
}
