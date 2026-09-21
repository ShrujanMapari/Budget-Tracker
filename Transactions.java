
/**
 * Write a description of class Transactions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Transactions
{
    // instance variables - replace the example below with your own

    private String type;
    private String category;
    private double amount;
    private String description;
    private String date;
    
    /**
     * Constructor for objects of class Transactions
     */
    public Transactions(String type, String category, double amount, String description, String date){
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public String getType(){
        return type;
    }

    public String getCategory(){
        return category;
    }

    public double getAmount(){
        return amount;
    }

    public String getDescription(){
        return description;
    }
    
    public String getDate(){
        return date;
    }
    
    public String toString(){
        if (category.equals("Others") && !description.equals(""))
        {
            return type + " - Others (" + description + ") - $" + amount;
        }
    
        return type + " - " + category + " - $" + amount;
    }
}

