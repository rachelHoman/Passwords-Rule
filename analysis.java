import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class analysis {

    public static void main(String[] args) {
        String fileName1 = "myspace-withcount.txt";
        String fileName2 = "rockyou-withcount.txt";

        // Data structures to store passwords and their frequencies
        Map<String, Integer> passwordFrequencies1 = new HashMap<>();
        Map<String, Integer> passwordFrequencies2 = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName1))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println("Read line: " + line);
                String[] parts = line.trim().split("\\s+"); // Assuming space-separated format
                // String[] parts = line.trim().split(" ", 2);
                if (parts.length >= 2) {
                    int frequency = Integer.parseInt(parts[0]);
                    String password = parts[1];
                    // System.out.println("pwd: " + password);
                    passwordFrequencies1.put(password, frequency);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println("Read line: " + line);
                String[] parts = line.trim().split("\\s+"); // Assuming space-separated format
                // String[] parts = line.trim().split(" ", 2);
                if (parts.length >= 2) {
                    int frequency = Integer.parseInt(parts[0]);
                    String password = parts[1];
                    // System.out.println("pwd: " + password);
                    passwordFrequencies2.put(password, frequency);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(passwordFrequencies1.size());
        System.out.println(passwordFrequencies2.size());

        // Now perform analysis and generate visualizations based on passwordFrequencies map
        // Implement your analysis and visualization code here
    }
}
