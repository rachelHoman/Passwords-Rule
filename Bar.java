import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Bar extends JPanel {

    // Sample data for the bar chart
    private final int[] specialCharCount = {41260, 106, 67, 32, 26, 19, 10, 4, 8, 0, 2};
    private final String[] labels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10+"};
    private final String xAxisName = "Number of Whitespace Characters";
    private final String yAxisName = "Percentage of passwords (%)";
    private final String chartTitle = "MySpace: Whitespace in Passwords";
    private final int total = 41545;

    // Array to store the calculated percentages
    private final int[] percentages = new int[specialCharCount.length];
    // private final double[] percentages = new double[specialCharCount.length];

    public Bar() {
        setPreferredSize(new Dimension(600, 400));
        calculatePercentages();
    }

    // Calculate percentages based on the total count
    private void calculatePercentages() {
        for (int i = 0; i < specialCharCount.length; i++) {
            percentages[i] = (int) ((double) specialCharCount[i] / total * 100);
            System.out.println(percentages[i]);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the title
        drawTitle(g);

        // Draw the bars
        drawBars(g);

        // Draw the axes and labels
        drawAxes(g);
    }

    private void drawTitle(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(chartTitle);
        int titleX = (getWidth() - titleWidth) / 2;
        g.drawString(chartTitle, titleX, 30);
    }

    private void drawBars(Graphics g) {
        int barWidth = (getWidth() - 100) / percentages.length;
    
        for (int i = 0; i < percentages.length; i++) {
            int barHeight = (int) ((double) percentages[i] / 100 * (getHeight() - 100));
            int x = i * barWidth + 50;
            int y = getHeight() - barHeight - 50;
            g.setColor(Color.BLUE);
            g.fillRect(x, y, barWidth, barHeight);
        }
    }
    

    private void drawAxes(Graphics g) {
        // Draw x-axis name
        g.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        int xAxisNameWidth = fm.stringWidth(xAxisName);
        int xAxisNameX = (getWidth() - xAxisNameWidth) / 2;
        int xAxisNameY = getHeight() - 10;
        // g.setColor(Color.BLUE);
        g.setColor(Color.BLACK);
        g.drawString(xAxisName, xAxisNameX, xAxisNameY);
        g.setColor(Color.BLUE);

        // Draw y-axis name
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(-Math.PI / 2);
        int yAxisNameWidth = fm.stringWidth(yAxisName);
        int yAxisNameX = -(getHeight() + yAxisNameWidth) / 2;
        int yAxisNameY = 20;
        g.setColor(Color.BLACK);
        g2d.drawString(yAxisName, yAxisNameX, yAxisNameY);
        g2d.rotate(Math.PI / 2);
        g.setColor(Color.BLUE);

        // Draw x-axis labels
        for (int i = 0; i < labels.length; i++) {
            int labelX = i * (getWidth() - 100) / labels.length + 50;
            int labelY = getHeight() - 30;
            g.drawString(labels[i], labelX, labelY);
        }

        // Draw y-axis labels
        for (int i = 0; i <= 100; i += 10) {
            int labelX = 30;
            int labelY = getHeight() - (i * (getHeight() - 100) / 100) - 50;
            g.drawString(Integer.toString(i), labelX, labelY);
        }

        // Draw x-axis
        g.drawLine(50, getHeight() - 50, getWidth() - 50, getHeight() - 50);

        // Draw y-axis
        g.drawLine(50, getHeight() - 50, 50, 50);
    }

    private int getMaxValue(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bar Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Bar bar = new Bar();
            frame.getContentPane().add(bar);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);

            // Save the graph as a PNG file
            BufferedImage image = new BufferedImage(bar.getWidth(), bar.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            bar.paint(g2d);
            g2d.dispose();

            try {
                File outputFile = new File("myspace_wsp.png");
                ImageIO.write(image, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}