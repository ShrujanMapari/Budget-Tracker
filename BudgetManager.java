import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
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

        //User currentUser = new User(username, password);
    }
    
    //public void newPassword(){
        
    //}    
    
    /**
     * 
     */
    public void transactions(String type) {
        int amount;
        int choice;
        if (type.equals("expenses")) {
            UI.println("\nSelect Expense Category:");
            UI.println("1. Grocery");
            UI.println("2. Rent");
            UI.println("3. Cloths");
            UI.println("4. Textbooks");
            UI.println("5. Eating Out");
            UI.println("6. Vehicle Repairs/Petrol Prices");
            UI.println("7. Internet");
            UI.println("8. Subcriptions");
            UI.println("9. Debt Repayment");
            UI.println("10. Gym");
            UI.println("11. Trips/Holidays");
            UI.println("12.Photocopying/Printing");
            
            choice = UI.askInt("Enter choice (1-2):");
            String category = "";
    
            if (choice == 1) {
                category = "Grocery";
            } else if (choice == 2) {
                category = "Rent";
            } else if (choice == 3) {
                category = "Cloths";
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
            }else if (choice == 5) {
                category = "Debt Repayment";
            } else if (choice == 6) {
                category = "Gym";
            }else if (choice == 7) {
                category = "Trips/Holidays";
            } else if (choice == 8) {
                category = "Photocopying/Printing";
            }else {
                UI.println("Invalid option selected!");
                return;
            }
    
            amount = UI.askInt("How much did it cost? $");
            boolean success = tracker.addExpenses(category, amount);
            if (success) {
                UI.println("Expense added to " + category + "!");
            }else if (type.equals("incomes")) {
                UI.println("\nSelect Income Category:");
                UI.println("1. Salary");
                UI.println("2. Freelance/Part Time");
                UI.println("3. Investment");
        
                choice = UI.askInt("Enter choice (1-3):");
                category = "";
            
                if (choice == 1) {
                     category = "Salary";
                } else if (choice == 2) {
                     category = "Freelance/Part Time";
                } else if (choice == 3) {
                     category = "Investment";
                } else {
                     UI.println("Invalid option selected!");
                     return;
                }
        
                amount = UI.askInt("How much did you earn? $");
                tracker.addIncome(category, amount);
                UI.println("Income added to " + category + "!");
            }
        }
    }
    /**
     * 
     */
    public void showBalance(){
        tracker.displayBalance();
    }
    
}

    //**
     //* Handles Mouse click
     //*/
    //public void doMouse(String action){
        
    //}

    ///**
     //* An example of a method - replace this comment with your own
     //*
     //* @param  y  a sample parameter for a method
     //* @return    the sum of x and y
     //*/
    //public int sampleMethod(int y)
    //{
        // put your code here
      //  return x + y;
    //}
