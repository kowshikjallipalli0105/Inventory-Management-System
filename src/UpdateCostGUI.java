/**
 * Author: omteja04
 * Description: UpdateCostGUI
 */
import java.sql.Connection;
import javax.swing.JOptionPane;

public class UpdateCostGUI {
    static String productID;
    static String costToBeModified;

    static int cost;

    public static void main(String[] args) {
        updateDialogue();
    }

    public static void updateDialogue() {

        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            productID = JOptionPane.showInputDialog("Enter The Product ID : ");
            costToBeModified = JOptionPane.showInputDialog("Enter The New Cost : ");

            
            try {
                cost = Integer.parseInt(costToBeModified);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cost should be a numeric value.");
                return; 
            }
        }
        UpdateCostDB.updateCostDB(connection, productID, cost);
        
        
        
        
        
    }
}
