import ecs100.*;
import java.util.HashMap;
import java.awt.Color;
/**
 * Write a description of class Transactions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PieChart
{
    private Tracker tracker;

    private Color[] colours = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.PINK};

    public PieChart(Tracker tracker)
    {
        this.tracker = tracker;
    }
    
    public void drawExpenseChart(){
        UI.clearGraphics();
        
        HashMap<String, Double> expenses = tracker.getExpenses();
    
        double total = tracker.getTotalExpenses();
    
        if (total == 0){
            UI.println("There are no expenses to display.");
            return;
        }
    
        double startAngle = 0;
        int colourNumber = 0;
    
        for (String category : expenses.keySet()){
            double amount = expenses.get(category);
    
            if (amount > 0){
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillArc(100, 100, 300, 300, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(expenses, total);
    }
    
    public void drawIncomeChart(){
        UI.clearGraphics();
        
        HashMap<String, Double> income = tracker.getIncome();
    
        double total = tracker.getTotalIncomes();
    
        if (total == 0){
            UI.println("There is no income to display.");
            return;
        }
    
        double startAngle = 0;
        int colourNumber = 0;
    
        for (String category : income.keySet()){
            double amount = income.get(category);
    
            if (amount > 0){
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillArc(100, 100, 300, 300, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(income, total);
    }
    
    
    public void drawLegend(HashMap<String, Double> data, double total){
        double x = 450;
        double y = 120;
        int colourNumber = 0;
    
        for (String category : data.keySet()){
            double amount = data.get(category);
    
            if (amount > 0){
                double percentage = (amount / total) * 100;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillRect(x, y, 15, 15);
    
                UI.setColor(Color.BLACK);
    
                UI.drawString(category + " - $" + amount + " - " + String.format("%.1f", percentage) + "%", x + 25, y + 12);
    
                y = y + 30;
                colourNumber++;
            }
        }
    }
}