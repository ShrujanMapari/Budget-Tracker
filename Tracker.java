import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
/**
 * Tracker is the support class that stores and manages the user's financial information.
 *
 * It keeps track of income, expenses and individual transactions.
 * 
 * It is also responsible for calculating totals and balances, saving/loading data, deleting transactions and rebuilding totals when transaction information changes.
 *
 * @author Shrujan Mapari
 * @version 2026
 */
public class Tracker
{ 

    // Stores the total amount recorded for each income category
    private HashMap<String, Double> income;
    
    // Stores the total amount recorded for each expense category
    private HashMap<String, Double> expenses;
    
    // Stores each individual transaction so its details and history are preserved
    private ArrayList<Transactions> transactions;
    
    
    /**
     * Creates the data structures used by the Tracker.
     *
     * The income and expense HashMaps are set up with all available categories starting at $0. 
     * The ArrayList is created to store each individual transaction entered by the user.
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
        expenses.put("Subscriptions", 0.0);
        expenses.put("Debt Repayment", 0.0);
        expenses.put("Gym", 0.0);
        expenses.put("Trips/Holidays", 0.0);
        expenses.put("Photocopying/Printing", 0.0);
        expenses.put("Others", 0.0);

        
        
        
    }
    
    /**
     * Adds an income transaction to the selected income category.
     *
     * The method first checks that the category exists and that the amount is valid. 
     * 
     * It then updates the category total and creates a Transactions object so the individual income is also stored in the transaction history.
     *
     * @param category the income category selected by the user
     * @param amount the amount of income received
     * @param description additional information when Others is selected
     * @param date the date the income was received
     */
    public void addIncome(String category, double amount, String description, String date) {
        if (income.containsKey(category)) {
            
            if (amount <= 0){
                UI.println("Income must be greater than $0.");
                return;
            }

            if (amount > 4000){
               UI.println("Income cannot exceed $4,000 in a single transaction.");
               return;
            }
            
            income.put(category, income.get(category) + amount);
            transactions.add(new Transactions("Income", category, amount, description, date));
            UI.println("Income added to " + category + "!");
            
        }else {
            UI.println("Category not found!");
        }
        
        
    }
    
    /**
     * Adds an expense transaction to the selected expense category.
     *
     * The method checks that the category exists and that there is enough income available before adding the expense. 
     * 
     * If successful, the expense category total is updated and the individual transaction is stored in the transaction history.
     *
     * @return true if the expense was successfully added, otherwise false
     */
    public boolean addExpenses(String category, double amount, String description, String date){
        // Prevents the user from adding an expense that exceeds the available income
        if (!expenses.containsKey(category)) {
            UI.println("Category not found!");
            return false;
        }else {
            if (amount > getTotalIncomes()){
                UI.println("Insufficient balance!");
                return false;
            }
            
            expenses.put(category, expenses.get(category) + amount);
            transactions.add(new Transactions("Expense", category, amount, description, date));
            return true;
        }
    }
    
    /**
     * Displays a numbered history of all transactions.
     *
     * Numbering the transactions allows the user to identify which transaction they want to edit or delete.
     */
    public void displayTransactions(){
        UI.println("\n--- Transaction History ---");
    
        for (int i = 0; i < transactions.size(); i++){
            UI.println((i + 1) + ". " + transactions.get(i));
        }
    }
    
    /**
     * Returns the number of transactions currently stored.
     *
     * This is used when checking whether transactions exist and when validating transaction selections.
     *
     * @return the number of stored transactions
     */
    public int getTransactionCount(){
        return transactions.size();
    }
    
    /**
     * Gives other classes access to the transaction history.
     *
     * The LineChart uses this information to calculate and display how the user's balance changes after each transaction.
     *
     * @return the ArrayList containing all transactions
     */
    public ArrayList<Transactions> getTransactions(){
        return transactions;
    }
    
    /**
     * Deletes a transaction selected by its displayed number.
     *
     * Before removing the transaction from the ArrayList, its amount is removed from the appropriate income or expense HashMap.
     * 
     * This keeps the category totals consistent with the remaining transaction history.
     *
     * @param choice the transaction number selected by the user
     */
    public void deleteTransaction(int choice){
        // Converts the displayed transaction number to an ArrayList index
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
    
    /**
     * Calculates the user's total income by adding the values stored in all income categories.
     *
     * @return the total income
     */
    public double getTotalIncomes()
    {
        double total = 0.0;
    
        for (double amount : income.values())
        {
            total = total + amount;
        }
    
        return total;
    }
    
    /**
     * Calculates the user's total expenses by adding the values stored in all expense categories.
     *
     * @return the total expenses
     */
    public double getTotalExpenses(){     
        double total = 0.0;

        for (double amount : expenses.values())
        {
            total = total + amount;
        }

        return total;
    }
    
    /**
     * Calculates the user's current balance.
     *
     * The balance is calculated by subtracting total expenses from total income.
     *
     * @return the user's current balance
     */
    public double balance(){
        return getTotalIncomes() - getTotalExpenses();
    }
    
    /**
     * Displays a summary of the user's finances.
     *
     * It shows the total income, total expenses and the remaining balance so the user can see their current financial position.
     */
    public void displayBalance(){
        UI.println("Total Income: $" + getTotalIncomes());
        UI.println("Total Expenses: $" + getTotalExpenses());
        UI.println("Bank Balance: $" + balance());
    }
    
    /**
     * Provides access to the income categories and their totals.
     *
     * This is mainly used by PieChart to create the income chart.
     *
     * @return the income HashMap
     */

    public HashMap<String, Double> getIncome(){
        return income;
    }

    /**
     * Provides access to the expense categories and their totals.
     *
     * This is mainly used by PieChart to create the expense chart.
     *
     * @return the expenses HashMap
     */
    public HashMap<String, Double> getExpenses(){
        return expenses;
    }
    
    /**
     * Saves every individual transaction to a text file.
     *
     * Each transaction is stored with its type, category, amount, description and date separated by | characters. 
     * 
     * Saving individual transactions instead of only category totals means the complete transaction history can be recreated when the data is loaded.
     */

    public void saveData(){
        
        try{
            PrintWriter writer = new PrintWriter("budgetData.txt");
            // Writes each transaction and all of its information on one line
            for (Transactions transaction : transactions){
    
                writer.println(transaction.getType() + "|" + transaction.getCategory() + "|" + transaction.getAmount() + "|" + transaction.getDescription() + "|" + transaction.getDate()
                );
            }
    
            writer.close();
    
            UI.println("Data saved successfully.");
        }
        catch (IOException e){
            UI.println("Error saving data.");
        }
    }
    
    /**
     * Loads previously saved transactions from the text file.
     *
     * Existing data is cleared first to prevent duplicate transactions.
     * 
     * Each line of the file is separated into its transaction details and used to recreate a Transactions object. 
     * 
     * The income and expense HashMaps are also rebuilt so totals and graphs match the loaded data.
     *
     * @return true if the data was loaded successfully, otherwise false
     */
    public boolean loadData(){

        try{
            File file = new File("budgetData.txt");
    
            if (!file.exists()){
                UI.println("No saved data found.");
                return false;
            }
    
            // Clear old transaction data
            transactions.clear();
    
            // Reset income totals
            for (String category : income.keySet()){
                income.put(category, 0.0);
            }
    
            // Reset expense totals
            for (String category : expenses.keySet()){
                expenses.put(category, 0.0);
            }
    
            Scanner scanner = new Scanner(file);
    
            while (scanner.hasNextLine()){
    
                String line = scanner.nextLine();
                
                // Separates the saved line back into the five transaction fields
                String[] parts = line.split("\\|", -1);
    
                String type = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String description = parts[3];
                String date = parts[4];
                
                // Recreates the original transaction from the saved information
                Transactions transaction = new Transactions(type, category, amount, description, date);
    
                transactions.add(transaction);
                
                // Rebuilds the category totals while the transactions are being loaded
                if (type.equals("Income")){
                    income.put(category, income.get(category) + amount);
                }
                else if (type.equals("Expense")){
                    expenses.put(category, expenses.get(category) + amount);
                }
            }
    
            scanner.close();
    
            UI.println("Data loaded successfully.");
            return true;
        }
        catch (Exception e){
            UI.println("Error loading data.");
            return false;
        }
    }
    
    /**
     * Recalculates all income and expense category totals from the
     * transaction history.
     *
     * The existing HashMap totals are reset to zero before every
     * transaction is added back into the appropriate category.
     * This is needed after editing a transaction so that the totals,
     * balance and graphs reflect the updated transaction information.
     */
    public void rebuildTotals(){
    
        // Reset all income categories
        for (String category : income.keySet()){
            income.put(category, 0.0);
        }
    
        // Reset all expense categories
        for (String category : expenses.keySet()){
            expenses.put(category, 0.0);
        }
    
        // Recalculate totals from transaction history
        for (Transactions transaction : transactions){
    
            String category = transaction.getCategory();
            double amount = transaction.getAmount();
    
            if (transaction.getType().equals("Income")){
                income.put(
                    category,
                    income.get(category) + amount
                );
            }
            else if (transaction.getType().equals("Expense")){
                expenses.put(
                    category,
                    expenses.get(category) + amount
                );
            }
        }
    }
    
    /**
     * Changes the amount of an existing transaction.
     *
     * The transaction number selected by the user is converted to its ArrayList index. 
     * 
     * The existing Transactions object is modified using its setter rather than deleting and recreating the object.
     *
     * The totals are then rebuilt so all financial information remains consistent with the edited transaction.
     *
     * @param choice the number of the transaction to edit
     * @param newAmount the new amount for the transaction
     */
    public void editTransaction(int choice, double newAmount){
        int index = choice - 1;
    
        if (index >= 0 && index < transactions.size()){
    
            Transactions transaction = transactions.get(index);
    
            // Change the existing object
            transaction.setAmount(newAmount);
            
            // Recalculates the HashMaps using the edited transaction history
            rebuildTotals();
    
            UI.println("Transaction edited.");
        }
        else{
            UI.println("Invalid transaction number.");
        }
    }
    
    
}
