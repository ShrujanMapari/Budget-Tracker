import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
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
    

    private HashMap<String, Double> income;
    
    private HashMap<String, Double> expenses;
    
    private ArrayList<Transactions> transactions;
    
    
    /**
     * Constructor for objects of class Tracker
     */
    public Tracker()
    {
        
        
        
        //initialise hashMaps
        this.income = new HashMap<>();
        this.expenses = new HashMap<>();
        
        //initialise array
        transactions = new ArrayList<Transactions>();
        
        //types of income
        income.put("Ongoing employment", 0.0);
        income.put("Student Allowance", 0.0);
        income.put("Scholarships", 0.0);
        income.put("Parents/Family", 0.0);
        income.put("Wellfare Support", 0.0);
        income.put("Holiday Work/Savings", 0.0);
        income.put("Others", 0.0);
        
        //types of expenses
        expenses.put("Grocery", 0.0);
        expenses.put("Rent", 0.0);
        expenses.put("Clothes", 0.0);
        expenses.put("Textbooks", 0.0);
        expenses.put("Eating Out", 0.0);
        expenses.put("Vehicle Repairs/Petrol Prices", 0.0);
        expenses.put("Internet", 0.0);
        expenses.put("Subcriptions", 0.0);
        expenses.put("Debt Repayment", 0.0);
        expenses.put("Gym", 0.0);
        expenses.put("Trips/Holidays", 0.0);
        expenses.put("Photocopying/Printing", 0.0);
        expenses.put("Others", 0.0);

        
        
        
    }
    
    /**
     * 
     */
    public void addIncome(String category, double amount) {
        if (income.containsKey(category)) {
            income.put(category, income.get(category) + amount);
            transactions.add(new Transactions("Income", category, amount));
        } else {
            UI.println("Category not found!");
        }
    }
    
    public boolean addExpenses(String category, double amount){
        if (!expenses.containsKey(category)) {
            UI.println("Category not found!");
        }
        
        if (amount > balance()) {
            UI.println("Transaction declined! Not enough funds.");
            UI.println("Current Balance: $" + balance() + " | Expense Amount: $" + amount);
            return false;
        
        }else {
            expenses.put(category, expenses.get(category) + amount);
            transactions.add(new Transactions("Expenses", category, amount));
            return true;
        }
    }
    
    public void displayTransactions(){
        UI.println("\n--- Transaction History ---");
    
        for (int i = 0; i < transactions.size(); i++){
            UI.println((i + 1) + ". " + transactions.get(i));
        }
    }
    
    public int getTransactionCount(){
        return transactions.size();
    }
    
    public void deleteTransaction(int choice){
        int index = choice - 1;

        if (index >= 0 && index < transactions.size()){
            Transactions transaction = transactions.get(index);

            if (transaction.getType().equals("Income")){
                String category = transaction.getCategory();
                double amount = transaction.getAmount();

                income.put(category, income.get(category) - amount);
            }else if (transaction.getType().equals("Expense")){
                String category = transaction.getCategory();
                double amount = transaction.getAmount();

                expenses.put(category, expenses.get(category) - amount);
            }

            transactions.remove(index);

            UI.println("Transaction deleted.");
        }else{
            UI.println("Invalid transaction number.");
        }
    }
    

    public double getTotalIncomes()
    {
        double total = 0.0;
    
        for (double amount : income.values())
        {
            total = total + amount;
        }
    
        return total;
    }
    
    
    public double getTotalExpenses(){     
        double total = 0.0;

        for (double amount : expenses.values())
        {
            total = total + amount;
        }

        return total;
    }
    
    public double balance(){
        return getTotalIncomes() - getTotalExpenses();
    }
    
    public void displayBalance(){
        UI.println("Total Income: $" + getTotalIncomes());
        UI.println("Total Expenses: $" + getTotalExpenses());
        UI.println("Bank Balance: $" + balance());
    }
    

    
}
