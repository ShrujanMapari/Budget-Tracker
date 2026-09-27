import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Driver Class.
 * 
 * BudgetManager is the driver class for the Budget Tracker.
 * It manages the user interface and allows the user to interact with the program through buttons and input questions
 * 
 * It connects the Tracker, PieChart and LineChart classes so that financial date and graphs are updated when the user makes changes.
 *
 * @author Shrujan Mapari
 * @version 2026
 */
public class BudgetManager
{
    
    private static Tracker tracker;
    
    private PieChart pieChart;
    
    private LineChart lineChart;

    /**
     * Sets up the Budget Tracker when the program starts
     * 
     * It creates the Tracker and graphs objects, sets the window size 
     * Adds all the buttons needed for the user to access the different features of the program
     */
    public BudgetManager(){
        // Sets up the ECS100 user interface
        UI.initialise();
        
        // Sets a suitable window size so the user does not need to resize it
        UI.setWindowSize(1200, 860);
        
        // Creates the objects used to store data and display graphs
        tracker = new Tracker();
        pieChart = new PieChart(tracker);
        lineChart = new LineChart(tracker);
        
        // Buttonsso the user can access the program's main features
        UI.addButton("Instructions", this::instructions); // To help user navigate the program
        
        
        UI.addButton("Add Income", () -> this.transactions("incomes"));
        UI.addButton("Add Expense", () -> this.transactions("expenses"));        
        
        UI.addButton("Show Balance", this::showBalance);
        
        UI.addButton("Show Transactions", this::showTransactions);
        UI.addButton("Edit Transaction", this::editTransaction);
        UI.addButton("Delete Transaction", this::deleteTransaction);
        
        UI.addButton("Save", this::saveData);
        UI.addButton("Load", this::loadData);
        
        UI.addButton("Quit", UI::quit);      

        
    }
    
    /**
     * Displays instructions explaining how to use the Budget Tracker
     * 
     * This helps new users understand what each main feature does before entering their financial information.
     */
    public void instructions(){
        JOptionPane.showMessageDialog(null, "Welcome to Budget Tracker!\n\n" + 
        "This program helps you manage your income and expenses.\n\n" + 
        "1. Add Income - record money you receive \n" + 
        "2. Add Expense - record money you spend\n" + 
        "3. Show Balance - view your current balance\n" + 
        "4. Delete Transaction - remove a transaction\n" + 
        "5. Pie Charts - see where your income and expense come from/go\n" +
        "6. Line Charts - see how much you spend and earned on a given date\n\n" +
        "Start by adding some income before adding expenses.");       
        
    }
    
    /**
     * Allows the user to add either an income or expense transaction.
     *
     * The type parameter determines whether income or expense categories are displayed. 
     * The method asks the user for the category, date and amount, validates their choices, and sends the information to Tracker.
     *
     * After a successful transaction, the graphs are redrawn so they immediately display the updated financial information.
     *
     * @param type determines whether an income or expense is being added
    */
    public void transactions(String type){
    
        UI.clearText();
    
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to continue adding " + type + "?", "Confirm", JOptionPane.YES_NO_OPTION);
    
        if (answer != JOptionPane.YES_OPTION){
            UI.println("Transaction cancelled.");
            return;
        }
    
        int choice;
        String category = "";
        String description = "";
        String date;
        double amount;
    
    
        // ---------------- EXPENSE ----------------
    
        if (type.equals("expenses")){
    
            // Keeps asking for an expense category until the user enters a valid option.
            // This allows the user to recover from an incorrect choice without restarting the transaction.
            boolean validChoice = false;
    
            // Keeps asking until the user enters a valid category
            while (!validChoice){
    
                UI.println("\nChoose an expense category:");
                UI.println("1. Grocery");
                UI.println("2. Rent");
                UI.println("3. Clothes");
                UI.println("4. Textbooks");
                UI.println("5. Eating Out");
                UI.println("6. Vehicle Repairs/Petrol Prices");
                UI.println("7. Internet");
                UI.println("8. Subscriptions");
                UI.println("9. Debt Repayment");
                UI.println("10. Gym");
                UI.println("11. Trips/Holidays");
                UI.println("12. Photocopying/Printing");
                UI.println("13. Others");
    
                choice = UI.askInt("Enter choice (1-13):");
    
                // Checks the user's choice and assigns the matching expense category
                if (choice == 1){
                    category = "Grocery";
                    validChoice = true;
                }
                else if (choice == 2){
                    category = "Rent";
                    validChoice = true;
                }
                else if (choice == 3){
                    category = "Clothes";
                    validChoice = true;
                }
                else if (choice == 4){
                    category = "Textbooks";
                    validChoice = true;
                }
                else if (choice == 5){
                    category = "Eating Out";
                    validChoice = true;
                }
                else if (choice == 6){
                    category = "Vehicle Repairs/Petrol Prices";
                    validChoice = true;
                }
                else if (choice == 7){
                    category = "Internet";
                    validChoice = true;
                }
                else if (choice == 8){
                    category = "Subscriptions";
                    validChoice = true;
                }
                else if (choice == 9){
                    category = "Debt Repayment";
                    validChoice = true;
                }
                else if (choice == 10){
                    category = "Gym";
                    validChoice = true;
                }
                else if (choice == 11){
                    category = "Trips/Holidays";
                    validChoice = true;
                }
                else if (choice == 12){
                    category = "Photocopying/Printing";
                    validChoice = true;
                }
                else if (choice == 13){
                    category = "Others";
                    validChoice = true;
                }
                else{
                    // An invalid choice keeps the user in the loop so they can try again
                    UI.println("Invalid option. Please choose a number from 1-13.");
                }
            }
    
            // Asks for extra information when the transaction does not fit an existing category
            if (category.equals("Others")){
                description = UI.askString("What do you mean by Others?");
            }
    
            // Continues to the next steps only after a valid category has been selected
            date = askDate();
    
            amount = UI.askDouble("How much did it cost? $");
    
            boolean added = tracker.addExpenses(category, amount, description, date);
    
            if (added){
                UI.println("Expense added to " + category + "!");
                pieChart.drawCharts();
                lineChart.drawLineChart();
            }
        }
    
    
        // ---------------- INCOME ----------------
    
        else if (type.equals("incomes")){
    
            // Keeps asking for an income category until the user enters a valid option.
            // This prevents an incorrect choice from ending the whole transaction.
            boolean validChoice = false;
    
            // Keeps asking until the user enters a valid category
            while (!validChoice){
    
                // Displays the available income categories
                UI.println("\nChoose an income category:");
                UI.println("1. Ongoing employment");
                UI.println("2. Student Allowance");
                UI.println("3. Scholarships");
                UI.println("4. Parents/Family");
                UI.println("5. Wellfare Support");
                UI.println("6. Holiday Work/Savings");
                UI.println("7. Others");
    
                choice = UI.askInt("Enter choice (1-7):");
    
                if (choice == 1){
                    category = "Ongoing employment";
                    validChoice = true;
                }
                else if (choice == 2){
                    category = "Student Allowance";
                    validChoice = true;
                }
                else if (choice == 3){
                    category = "Scholarships";
                    validChoice = true;
                }
                else if (choice == 4){
                    category = "Parents/Family";
                    validChoice = true;
                }
                else if (choice == 5){
                    category = "Wellfare Support";
                    validChoice = true;
                }
                else if (choice == 6){
                    category = "Holiday Work/Savings";
                    validChoice = true;
                }
                else if (choice == 7){
                    category = "Others";
                    validChoice = true;
                }
                else{
                    // Allows the user to correct their choice instead of restarting
                    UI.println("Invalid option. Please choose a number from 1-7.");
                }
            }
    
            // Only asks this after a valid category has been selected
            if (category.equals("Others")){
                description = UI.askString("What do you mean by Others?");
            }
    
            date = askDate();
    
            amount = UI.askDouble("How much income did you receive? $");
    
            tracker.addIncome(category, amount, description, date);
    
            pieChart.drawCharts();
            lineChart.drawLineChart();
        }
    }
    
    /**
     * Displays all transactions currently stored in the Tracker.
     *
     * The text area is cleared first so the transaction history is easier for the user to read.
     */
    public void showTransactions(){
        UI.clearText();
        tracker.displayTransactions();
    }
    
    /**
     * Displays the user's total income, total expenses and current balance.
     *
     * This gives the user a quick summary of their financial situation for the month.
     */
    
    public void showBalance(){
        UI.clearText();
        tracker.displayBalance();
    }
    
    
    /**
     * Allows the user to remove an existing transaction.
     *
     * The transaction history is displayed so the user can select the transaction they want to delete. 
     * Two confirmation messages are used to reduce the chance of accidentally deleting financial information.
     *
     * After deletion, the graphs are redrawn to show the updated data.
     */
    public void deleteTransaction(){
        UI.clearText();
        tracker.displayTransactions();
        int choice = UI.askInt("Which transaction do you want to delete? (1-" + tracker.getTransactionCount() + ")");
        
        // Uses two confirmations because deleting a transaction cannot be undone
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
        pieChart.drawCharts();
        lineChart.drawLineChart();
    }
    
    /**
     * Asks the user for the date of a transaction and validates it.
     *
     * The date must use DD/MM/YYYY format, must be within the current month and cannot be in the future. 
     * 
     * If an invalid date is entered, an error message is displayed and the user can try again.
     *
     * @return the valid transaction date entered by the user
     */
    public String askDate(){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    
        // Repeats until the user enters a valid date
        while (true){
    
            String date = UI.askString("Enter date (DD/MM/YYYY):");
    
            // Attempts to convert the user's text into a real date
            try{
                LocalDate enteredDate = LocalDate.parse(date, format);
    
                LocalDate today = LocalDate.now();
    
                // Check if date is in the future
                if (enteredDate.isAfter(today)){
                    UI.println("Date cannot be in the future.");
                }
    
                // Check if date is not in the current month
                else if (enteredDate.getMonthValue() != today.getMonthValue() || enteredDate.getYear() != today.getYear()){
                    UI.println("Date must be within the current month.");
                }
    
                else{
                    return date;
                }
            }
            catch (DateTimeParseException e){
                UI.println("Invalid date. Please use DD/MM/YYYY.");
            }
        }
    }
    
    /**
     * Allows the user to change the amount of an existing transaction.
     *
     * The transaction history is shown first so the user can select the correct transaction. 
     * 
     * The new amount is passed to Tracker, which updates the transaction and recalculates the financial totals.
     *
     * The graphs are then redrawn to display the edited information.
     */
    public void editTransaction(){

        UI.clearText();
    
        tracker.displayTransactions();
    
        if (tracker.getTransactionCount() == 0){
            UI.println("There are no transactions to edit.");
            return;
        }
    
        int choice = UI.askInt("Enter the number of the transaction you want to edit:");
    
        double newAmount = UI.askDouble("Enter the new amount:");
    
        tracker.editTransaction(choice, newAmount);
        
        // Updates the graphs and pie charts, removing the deleted transaction
        pieChart.drawCharts();
        lineChart.drawLineChart();
    }
    
    /**
     * Saves the current transaction data so it is not lost when the program is closed.
     *
     * The actual file handling is performed by the Tracker class.
     */
    public void saveData(){
        UI.clearText();
    
        tracker.saveData();
    }
    
    /**
     * Loads previously saved transaction data into the program.
     *
     * Tracker handles reading and rebuilding the financial data.
     * 
     * If loading is successful, the graphs are redrawn so the loaded information is immediately visible to the user.
     */
    public void loadData(){
        UI.clearText();
    
        boolean success = tracker.loadData();
    
        if (success){
            pieChart.drawCharts();
            lineChart.drawLineChart();
        }
    }
    
    /**
     * Starts the College Budget Tracker.
     *
     * Creating a BudgetManager object initialises the user interface and makes the program ready for the user.
     */
    public static void main(String[] args){
        new BudgetManager();
    }
}
