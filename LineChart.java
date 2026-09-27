import ecs100.*;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The LineChart class displays how the user's balance changes over time as income and expense transactions are added.
 *
 * It reads the transaction history from Tracker and calculates the running balance after each transaction. 
 * 
 * Each balance is then plotted on the graph against the transaction date.
 *
 * @author Shrujan Mapari
 * @version 2026
 */
public class LineChart
{
    // Gives the LineChart access to the transaction history stored in Tracker
    private Tracker tracker;

    /**
     * Creates a LineChart connected to the program's Tracker.
     *
     * This allows the chart to use the same transaction data as the rest of the Budget Tracker.
     *
     * @param tracker the Tracker containing the user's transactions
     */
    public LineChart(Tracker tracker){
        this.tracker = tracker;
    }

    /**
     * Draws a line chart showing how the user's balance changes throughout the month.
     *
     * The method goes through the transactions in order and creates a running balance by adding income and subtracting expenses.
     *
     * Each balance is converted into a position on the graph and connected to the previous point to show the change over time.
     */
    public void drawLineChart(){
        // Creates a copy so the original transaction history is not changed
        ArrayList<Transactions> transactions = new ArrayList<>(tracker.getTransactions());
        
        // Converts the stored DD/MM/YYYY dates into real dates for sorting
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        
        // Sorts transactions from earliest date to latest date
        transactions.sort(Comparator.comparing(transaction -> LocalDate.parse(transaction.getDate(), format)));
        
        // Displays a message instead of an empty graph when there is no data
        if (transactions.size() == 0){
            UI.drawString("Add transactions to display balance history.", 50, 650);
            return;
        }

        // Running balance starts at $0 before any transactions are processed
        double balance = 0;

        // Line chart boundaries
        double chartTop = 390;
        double chartBottom = 700;
        double chartHeight = chartBottom - chartTop;

        // Maximum monthly balance
        double maxBalance = 4000;

        double startX = 70;
        double startY = chartBottom;

        // Stores the previous point so it can be connected to the next balance point on the graph
        double previousX = startX;
        double previousY = startY;

        UI.setColor(Color.BLACK);

        // Title
        UI.drawString("BALANCE OVER TIME", 70, 370);

        // Y axis
        UI.drawLine(70, chartTop, 70, chartBottom);

        // X axis
        UI.drawLine(70, chartBottom, 1100, chartBottom);

        // Y axis labels
        UI.drawString("$4,000", 10, 395);
        UI.drawString("$3,000", 10, 472);
        UI.drawString("$2,000", 10, 550);
        UI.drawString("$1,000", 10, 627);
        UI.drawString("$0", 30, 700);
        
        // Goes through each transaction in order to calculate how the balance changes over time
        for (int i = 0; i < transactions.size(); i++){

            Transactions transaction = transactions.get(i);
            
            // Income increases the running balance while expenses decrease it
            if (transaction.getType().equals("Income")){
                balance = balance + transaction.getAmount();
            }
            else if (transaction.getType().equals("Expense")){
                balance = balance - transaction.getAmount();
            }

            // Moves each transaction point further across the X axis
            double x = 120 + (i * 70);

            // Converts the current balance into a vertical position.
            // A larger balance is placed higher on the graph.
            double y = chartBottom - (balance / maxBalance) * chartHeight;

            // Prevent values above $4,000 going outside chart
            if (y < chartTop){
                y = chartTop;
            }

            // Connects the new balance to the previous balance
            UI.drawLine(previousX, previousY, x, y);

            // Marks the transaction's balance as a point
            UI.fillOval(x - 3, y - 3, 6, 6);

            // Displays the transaction date underneath the point
            UI.drawString(transaction.getDate(), x - 15, 720);

            // Saves this point so the next transaction can connect to it
            previousX = x;
            previousY = y;
        }
    }
}
