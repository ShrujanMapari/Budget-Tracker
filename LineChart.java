import ecs100.*;
import java.util.ArrayList;
import java.awt.Color;

public class LineChart
{
    private Tracker tracker;

    public LineChart(Tracker tracker){
        this.tracker = tracker;
    }

    public void drawLineChart(){

        ArrayList<Transactions> transactions = tracker.getTransactions();

        if (transactions.size() == 0){
            UI.drawString("Add transactions to display balance history.", 50, 650);
            return;
        }

        double balance = 0;

        double startX = 50;
        double startY = 700;

        double previousX = startX;
        double previousY = startY;

        UI.setColor(Color.BLACK);

        UI.drawLine(50, 550, 50, 700);   // Y axis
        UI.drawLine(50, 700, 600, 700);  // X axis

        for (int i = 0; i < transactions.size(); i++){

            Transactions transaction = transactions.get(i);

            if (transaction.getType().equals("Income")){
                balance = balance + transaction.getAmount();
            }
            else if (transaction.getType().equals("Expense")){
                balance = balance - transaction.getAmount();
            }

            double x = 100 + (i * 70);

            // temporary scale: $100 = 5 pixels
            double y = 700 - (balance / 20);

            UI.drawLine(previousX, previousY, x, y);

            UI.fillOval(x - 3, y - 3, 6, 6);

            UI.drawString(transaction.getDate(), x - 15, 720);

            previousX = x;
            previousY = y;
        }
    }
}
