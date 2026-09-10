import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JOptionPane;
/**
 * Driver Class.
 *
 * Shrujan Mapari
 * 29/07/2026
 */
public class BudgetManager
{
    // instance variables - replace the example below with your own
    private static Tracker tracker;
    
    //String username = UI.askString("Enter username");
    //String password = UI.askString("Enter password"); 
    

    /**
     * Constructor for objects of class Budget
     */
    public BudgetManager()
    {
        // initialise instance variables
        UI.initialise();
        
        tracker = new Tracker();
        
        UI.addButton("Quit", UI::quit);
        //UI.setMouseListener(this::doMouse)
        UI.addButton("Add Income", () -> this.transactions("incomes"));
        UI.addButton("Add Expense", () -> this.transactions("expenses"));
        
        UI.addButton("Show Balance", this::showBalance);
        
        UI.addButton("Show Transactions", this::showTransactions);
        
        UI.addButton("Delete Transaction", this::deleteTransaction);

        //User currentUser = new User(username, password);
    }
    
    
    
    
    
    //public void newPassword(){
       
        
        
        
    //}    
    
    /**
     * 
     */
    public void transactions(String type) {
        double amount;
        int choice;
        if (type.equals("expenses")) {
            UI.println("\nSelect Expense Category:");
            UI.println("1. Grocery");
            UI.println("2. Rent");
            UI.println("3. Clothes");
            UI.println("4. Textbooks");
            UI.println("5. Eating Out");
            UI.println("6. Vehicle Repairs/Petrol Prices");
            UI.println("7. Internet");
            UI.println("8. Subcriptions");
            UI.println("9. Debt Repayment");
            UI.println("10. Gym");
            UI.println("11. Trips/Holidays");
            UI.println("12.Photocopying/Printing");
            UI.println("13. Others");
            
            choice = UI.askInt("Enter choice (1-13):");
            String category = "";
    
            if (choice == 1) {
                category = "Grocery";
            } else if (choice == 2) {
                category = "Rent";
            } else if (choice == 3) {
                category = "Clothes";
            } else if (choice == 4) {
                category = "Textbooks";
            }else if (choice == 5) {
                category = "Eating Out";
            } else if (choice == 6) {
                category = "Vehicle Repairs/Petrol Prices";
            }else if (choice == 7) {
                category = "Internet";
            } else if (choice == 8) {
                category = "Subscriptions";
            }else if (choice == 9) {
                category = "Debt Repayment";
            } else if (choice == 10) {
                category = "Gym";
            }else if (choice == 11) {
                category = "Trips/Holidays";
            } else if (choice == 12) {
                category = "Photocopying/Printing";
            }else if (choice == 13){
                category = "Others";
            }else {
                UI.println("Invalid option selected!");
                return;
            }
    
            amount = UI.askDouble("How much did it cost? $");
            boolean success = tracker.addExpenses(category, amount);
            if (success) {
                UI.println("Expense added to " + category + "!");
            }else {
                UI.println("Unexpected error has occured. Please Try Again.");
            }
            
            
            
        } else if (type.equals("incomes")) {
            UI.println("\nSelect Income Category:");
            UI.println("1. Ongoing employment");
            UI.println("2. Student Allowance");
            UI.println("3. Scholarships");
            UI.println("4. Parents/Family");
            UI.println("5. Wellfare Support");
            UI.println("6. Holiday Work/Savings");
            UI.println("7. Others");
                
            choice = UI.askInt("Enter choice (1-7):");
            String category = "";
            
            if (choice == 1) {
                category = "Ongoing employment";
            } else if (choice == 2) {
                category = "Student Allowance";
            } else if (choice == 3) {
                category = "Scholarships";
            } else if (choice == 4){
                category = "Parents/Family";
            } else if (choice == 5){
                category = "Wellfare Support";
            } else if (choice == 6){
                category = "Holiday Work/Savings";
            } else if (choice == 7){
                category = "Others";
            }else {
                UI.println("Invalid option selected!");
                return;
            }
        
            amount = UI.askDouble("How much did you earn? $");
            tracker.addIncome(category, amount);
            UI.println("Income added to " + category + "!");
            }
        
    }
    
    public void showTransactions(){
        tracker.displayTransactions();
    }
    
    /**
     * 
     */
    public void showBalance(){
        tracker.displayBalance();
    }
    
    
    public void deleteTransaction(){
        tracker.displayTransactions();
        int choice = UI.askInt("Which transaction do you want to delete? (1-" + tracker.getTransactionCount() + ")");
      
        int firstConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    
        if (firstConfirm != JOptionPane.YES_OPTION){
            UI.println("Transaction deletion cancelled.");
            return;
        }
    
        int secondConfirm = JOptionPane.showConfirmDialog(null, "This action cannot be undone. Delete this transaction?", "Final Confirmation", JOptionPane.YES_NO_OPTION);
    
        if (secondConfirm != JOptionPane.YES_OPTION){
            UI.println("Transaction deletion cancelled.");
            return;
        }
    
        tracker.deleteTransaction(choice);

    }
    
}
