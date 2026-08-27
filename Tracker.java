import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
/**
 * Support Class which keeps track of the users finances.
 *
 *
 * Shrujan Mapari
 * 29/07/2026
 */
public class Tracker
{
    // instance variables - replace the example below with your own
    

    private HashMap<String, Integer> income;
    
    private HashMap<String, Integer> expenses;
    
    
    
    /**
     * Constructor for objects of class Tracker
     */
    public Tracker()
    {
        //initialise hashMaps
        this.income = new HashMap<>();
        this.expenses = new HashMap<>();
        
        //types of income
        income.put("Ongoing employment", 0);
        income.put("Student Allowance", 0);
        income.put("Scholarships", 0);
        income.put("Parents/Family", 0);
        income.put("Wellfare Support", 0);
        income.put("Holiday Work/Savings", 0);
        
        //types of expenses
        expenses.put("Grocery", 0);
        expenses.put("Rent", 0);
        expenses.put("Cloths", 0);
        expenses.put("Textbooks", 0);
        expenses.put("Eating Out", 0);
        expenses.put("Vehicle Repairs/Petrol Prices", 0);
        expenses.put("Internet", 0);
        expenses.put("Subcriptions", 0);
        expenses.put("Debt Repayment", 0);
        expenses.put("Gym", 0);
        expenses.put("Trips/Holidays", 0);
        expenses.put("Photocopying/Printing", 0);
        
        
    }
    
    /**
     * 
     */
    public void addIncome(String category, int amount) {
        if (income.containsKey(category)) {
            income.put(category, income.get(category) + amount);
        } else {
            UI.println("Category not found!");
        }
    }
    
    public boolean addExpenses(String category, int amount){
        if (!expenses.containsKey(category)) {
            UI.println("Category not found!");
        }
        
        if (amount > balance()) {
            UI.println("Transaction declined! Not enough funds.");
            UI.println("Current Balance: $" + balance() + " | Expense Amount: $" + amount);
            return false;
        } else {
            expenses.put(category, expenses.get(category) + amount);
            return true;
        }
    }
    
    
    public void displayIncome(){
        UI.println("Income from Ongoing employment: " + income.get("Ongoing employment"));
        UI.println("Income from Student Allowance: " + income.get("Student Allowance"));
        UI.println("Income from Scholarships: " + income.get("Scholarships"));
        UI.println("Income from Parents/Family: " + income.get("Parents/Family"));
        UI.println("Income from Wellfare Support: " + income.get("Wellfare Support"));
        UI.println("Income from Holiday Work/Saving: " + income.get("Holiday Work/Saving"));
    }
    
    
    public void displayExpense(){
        UI.println("Expense from Grocery: " + expenses.get("Grocery"));
        UI.println("Expenses from Rent: " + expenses.get("Rent"));
        UI.println("Expense from Clothes: " + expenses.get("Clothes"));
        UI.println("Expenses from Textbooks: " + expenses.get("Textbooks"));
        UI.println("Expense from Eating Out: " + expenses.get("Eating Out"));
        UI.println("Expenses from Vehicle Repairs/Petrol Prices: " + expenses.get("Vehicle Repairs/Petrol Prices"));
        UI.println("Expense from Internet: " + expenses.get("Internet"));
        UI.println("Expenses from Subscription: " + expenses.get("Subscription"));
        UI.println("Expense from Debt Repayment: " + expenses.get("Debt Repayment"));
        UI.println("Expenses from Gym: " + expenses.get("Gym"));
        UI.println("Expense from Trips/Holidays: " + expenses.get("Trips/Holidays"));
        UI.println("Expenses from Photocopying/Prinitng: " + expenses.get("Photocopying/Printing"));
    }
    

    public int getTotalExpenses(){
        int total = 0;

        for (int amount : expenses.values())
        {
            total = total + amount;
        }

        return total;
    }
    
    public int getTotalIncomes(){
        int total = 0;

        for (int amount : income.values())
        {
            total = total + amount;
        }

        return total;
    }
    
    public int balance(){
        return getTotalIncomes() - getTotalExpenses();
    }
    
    public void displayBalance(){
        UI.println("Total Income: $" + getTotalIncomes());
        UI.println("Total Expenses: $" + getTotalExpenses());
        UI.println("Bank Balance: $" + balance());
    }

}
    ///**
     //* An example of a method - replace this comment with your own
     //*
     //* @param  y  a sample parameter for a method
     //* @return    the sum of x and y
     //*/
    //public int sampleMethod(int y)
    //{
        // put your code here
        
    //}
//private() {
        
       // for (String category : income.keySet()) {
         //   if (income.get(category) < 0) {
           //     income.put(category, 0);
            //}
        //}
    
        
        //for (String category : expenses.keySet()) {
          //  if (expenses.get(category) < 0) {
               // expenses.put(category, 0);
            //}
        //}
    //}
