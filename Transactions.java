/**
 * The Transactions class represents one individual income or expense transaction in the College Budget Tracker.
 *
 * Each transaction stores its type, category, amount, description and date. 
 * 
 * These details are used for displaying transaction history, editing transactions, saving/loading data and creating the line chart.
 *
 * @author Shrujan Mapari
 * @version 2026
 */
public class Transactions
{

    // Stores whether the transaction is an Income or Expense
    private String type;
    
    // Stores the category selected for the transaction
    private String category;
    
    // Stores the amount of money involved in the transaction
    private double amount;
    
    // Stores extra information when the user selects "Others"
    private String description;
    
    // Stores the date when the transaction occurred
    private String date;
    
    /**
     * Creates a new transaction and stores all the information entered by the user.
     *
     * @param type whether the transaction is Income or Expense
     * @param category the selected income or expense category
     * @param amount the amount of the transaction
     * @param description additional information for the Others category
     * @param date the date of the transaction
     */
    public Transactions(String type, String category, double amount, String description, String date){
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    /**
     * @return the transaction type
     */
    public String getType(){
        return type;
    }

    /**
     * @return the transaction category
     */
    public String getCategory(){
        return category;
    }

    /**
     * @return the transaction amount
     */
    public double getAmount(){
        return amount;
    }

    /**
     * @return the transaction description
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * @return the transaction date
     */
    public String getDate(){
        return date;
    }
    
    /**
     * Converts the transaction into a readable format for the transaction history.
     *
     * If the user selected "Others", the additional description is included so the user can see what the transaction was for.
     *
     * @return the transaction information as a String
     */
    public String toString(){
        if (category.equals("Others") && !description.equals(""))
        {
            return type + " - Others (" + description + ") - $" + amount;
        }
    
        return type + " - " + category + " - $" + amount;
    }
    
    /**
     * Changes the amount of an existing transaction.
     * 
     * This is used when editing transaction information.
     *
     * @param amount the new transaction amount
     */
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    /**
     * Changes the category of an existing transaction.
     *
     * @param category the new transaction category
     */
    public void setCategory(String category){
        this.category = category;
    }
    
    /**
     * Changes the description of an existing transaction.
     *
     * @param description the new description
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Changes the date of an existing transaction.
     *
     * @param date the new transaction date
     */
    public void setDate(String date){
        this.date = date;
    }
}
