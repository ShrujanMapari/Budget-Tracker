import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
/**
 * The PieChart class displays the user's income and expenses as pie charts.
 *
 * It gets financial data from the Tracker and calculates how much each category contributes to the total. 
 *
 * Different colours are used to represent the categories and a legend shows the amount and percentage for each category.
 *
 * @author Shrujan Mapari
 * @version 2026
 */
public class PieChart
{
    // Gives the PieChart access to the financial data stored in Tracker
    private Tracker tracker;
    
    // Colours used to distinguish different categories on the pie charts
    private Color[] colours = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.PINK};

    /**
     * Creates a PieChart connected to the same Tracker used by the rest of the program.
     *
     * @param tracker the Tracker containing the user's financial data
     */
    public PieChart(Tracker tracker)
    {
        this.tracker = tracker;
    }
    
    /**
     * Draws a pie chart showing how the user's expenses are divided between the different expense categories.
     *
     * Each category's amount is compared with the total expenses to calculate the size of its section. 
     * 
     * Categories with no expenses are not displayed.
     */
    public void drawExpenseChart(){
        
        // Gets the expense categories and calculates the total expenses
        HashMap<String, Double> expenses = tracker.getExpenses();
    
        double total = tracker.getTotalExpenses();
    
        if (total == 0){
            UI.setColor(Color.BLACK);
            UI.drawString("Expense", 600, 40);
            UI.drawString("Please add expense to display the chart.", 580, 200);
            return;
        }
    
        double startAngle = 0;
        int colourNumber = 0;
    
        for (String category : expenses.keySet()){
            double amount = expenses.get(category);
            
            // Prevents the chart calculation when there are no expenses to display
            if (amount > 0){
                // Converts the category's proportion of the total into an angle out of the 360 degrees in a circle
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
                
                // Draws this category's section starting where the previous section ended
                UI.fillArc(580, 60, 180, 180, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(expenses, total, 790, 90);
    }
    
    /**
     * Draws a pie chart showing how the user's income is divided between the different income categories.
     *
     * Each income category is converted into a section of the pie based on its percentage of the user's total income.
     */
    public void drawIncomeChart(){
        // Gets the income categories and calculates the total income
        
        HashMap<String, Double> income = tracker.getIncome();
    
        double total = tracker.getTotalIncomes();
    
        if (total == 0){
            UI.setColor(Color.BLACK);
            UI.drawString("INCOME", 70, 40);
            UI.drawString("Please add income to display the chart.", 50, 200);
            return;
        }
    
        double startAngle = 0;
        int colourNumber = 0;
        
            
        
        for (String category : income.keySet()){
            double amount = income.get(category);
    
            if (amount > 0){
                // Calculates the angle needed to represent this category
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillArc(50, 60, 180, 180, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(income, total, 260, 90);
    }
    
    /**
     * Draws a legend beside either the income or expense pie chart.
     *
     * The legend uses the same colours as the pie sections and displays the category name, dollar amount and percentage of the total.
     * 
     * Using one method for both charts avoids repeating the same code.
     *
     * @param data the income or expense HashMap being displayed
     * @param total the total income or expenses
     * @param x the horizontal starting position of the legend
     * @param y the vertical starting position of the legend
     */
    public void drawLegend(HashMap<String, Double> data, double total, double x, double y){
        int colourNumber = 0;
    
        for (String category : data.keySet()){
            double amount = data.get(category);
    
            if (amount > 0){
                // Calculates what percentage of the total belongs to this category
                double percentage = (amount / total) * 100;
    
                UI.setColor(colours[colourNumber % colours.length]);
                UI.fillRect(x, y, 15, 15);
    
                UI.setColor(Color.BLACK);
    
                UI.drawString(category + " - $" + amount + " - " + String.format("%.1f", percentage) + "%", x + 25, y + 12);
                
                // Moves the next legend item down so the entries do not overlap
                y = y + 30;
                colourNumber++;
            }
        }
    }
    
    /**
     * Refreshes both pie charts.
     *
     * The old graphics are cleared first so previous chart sections
     * are not left behind when the financial data changes. Both charts
     * are then redrawn using the latest data from Tracker.
     */
    public void drawCharts(){
        UI.clearGraphics();
    
        drawIncomeChart();
        drawExpenseChart();
    }
}
