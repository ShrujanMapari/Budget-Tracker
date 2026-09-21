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
        
        
        HashMap<String, Double> expenses = tracker.getExpenses();
    
        double total = tracker.getTotalExpenses();
    
        if (total == 0){
            UI.setColor(Color.BLACK);
            UI.drawString("Expense", 70, 310);
            UI.drawString("Please add expense to display the chart.", 50, 360);
            return;
        }
    
        double startAngle = 0;
        int colourNumber = 0;
    
        for (String category : expenses.keySet()){
            double amount = expenses.get(category);
    
            if (amount > 0){
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillArc(50, 330, 180, 180, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(expenses, total, 260, 360);
    }
    
    public void drawIncomeChart(){
        
        
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
                double angle = (amount / total) * 360;
    
                UI.setColor(colours[colourNumber % colours.length]);
    
                UI.fillArc(50, 60, 180, 180, startAngle, angle);
    
                startAngle = startAngle + angle;
                colourNumber++;
            }
        }
        drawLegend(income, total, 260, 90);
    }
    
    
    public void drawLegend(HashMap<String, Double> data, double total, double x, double y){
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
    
    public void drawCharts(){
        UI.clearGraphics();
    
        drawIncomeChart();
        drawExpenseChart();
    }
}