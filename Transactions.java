
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
    /**
     * Constructor for objects of class Transactions
     */
    public Transactions(String type, String category, double amount){
        this.type = type;
        this.category = category;
        this.amount = amount;
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

    public String toString(){
        return type + " - " + category + " - $" + amount;
    }
}
