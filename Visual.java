import javax.swing.*;
import java.awt.*;

public class Visual {
    public static void main(String[] args) {
        // Create a frame for the chart
        JFrame frame = new JFrame("Number of Special Characters in Passwords");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel to hold the chart
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBarChart(g);
            }
        };

        // Add the chart panel to the frame
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to draw the bar chart
    private static void drawBarChart(Graphics g) {
        // Sample data for the number of special characters
        
        int[] specialCharCount = {31458645, 842441, 197855, 56764, 102376, 5765, 7958, 2053, 1752, 744, 2509};
        String[] labels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10+"};

        // Calculate chart dimensions
        int width = 700;
        int height = 500;
        int barWidth = width / specialCharCount.length;
        int maxValue = getMaxValue(specialCharCount);

        // Draw the bars
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        for (int i = 0; i < specialCharCount.length; i++) {
            int barHeight = (int) ((double) specialCharCount[i] / maxValue * height);
            int x = i * barWidth + 50;
            int y = height - barHeight + 50;
            g2d.fillRect(x, y, barWidth - 10, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawString(labels[i], x + 5, height + 70);
        }
    }

    // Method to get the maximum value in an array
    private static int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}