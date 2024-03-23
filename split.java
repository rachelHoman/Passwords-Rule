import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class split {
    public static void main(String[] args) {
        String inputFile = "rockyou-withcount.txt";
        String outputFile1 = "rockyou1.txt";
        String outputFile2 = "rockyou2.txt";

        try {
            // Read the input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            reader.close();
            String content = contentBuilder.toString();

            // Split the content into two halves
            int length = content.length();
            int midpoint = length / 2;
            String firstHalf = content.substring(0, midpoint);
            String secondHalf = content.substring(midpoint);

            // Write the first half to outputFile1
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(outputFile1));
            writer1.write(firstHalf);
            writer1.close();

            // Write the second half to outputFile2
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFile2));
            writer2.write(secondHalf);
            writer2.close();

            System.out.println("File split successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
