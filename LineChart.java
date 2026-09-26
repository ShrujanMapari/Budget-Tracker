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

        // Line chart boundaries
        double chartTop = 390;
        double chartBottom = 700;
        double chartHeight = chartBottom - chartTop;

        // Maximum monthly balance
        double maxBalance = 4000;

        double startX = 70;
        double startY = chartBottom;

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

        for (int i = 0; i < transactions.size(); i++){

            Transactions transaction = transactions.get(i);

            if (transaction.getType().equals("Income")){
                balance = balance + transaction.getAmount();
            }
            else if (transaction.getType().equals("Expense")){
                balance = balance - transaction.getAmount();
            }

            double x = 120 + (i * 70);

            // Position balance between $0 and $4,000
            double y = chartBottom -
                       (balance / maxBalance) * chartHeight;

            // Prevent values above $4,000 going outside chart
            if (y < chartTop){
                y = chartTop;
            }

            UI.drawLine(previousX, previousY, x, y);

            UI.fillOval(x - 3, y - 3, 6, 6);

            UI.drawString(transaction.getDate(), x - 15, 720);

            previousX = x;
            previousY = y;
        }
    }
}

