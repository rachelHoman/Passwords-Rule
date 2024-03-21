import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        String filename = "myspace-withcount.txt";

        // Array to store count of passwords with different number of special characters
        int[] specialCharCount = new int[11]; // Assuming passwords have at most 5 special characters

        // Read passwords from file and analyze
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line to get the count and password
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    String password = parts[parts.length - 1]; // Assuming the password is the last part
                    int count = countSpecialCharacters(password);
                    if (count >= 0 && count < specialCharCount.length) {
                        specialCharCount[count]++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display results
        System.out.println("Number of Special Characters   Number of Passwords");
        for (int i = 0; i < specialCharCount.length; i++) {
            System.out.printf("%d                              %d%n", i, specialCharCount[i]);
        }
    }

    // Method to count special characters in a password
    private static int countSpecialCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                count++;
            }
        }
        return count;
    }
}