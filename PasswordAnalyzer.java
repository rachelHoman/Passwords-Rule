import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        String filename = "myspace-withcount.txt";

        // Map to store password frequencies
        Map<String, Integer> passwordFrequency = new HashMap<>();

        // Read passwords from file and populate the map
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line to get the count and password
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    String password = parts[parts.length - 1]; // Assuming the password is the last part
                    passwordFrequency.put(password, passwordFrequency.getOrDefault(password, 0) + Integer.parseInt(parts[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Array to store count of passwords with different number of special characters
        int[] specialCharCount = new int[11]; 
        int[] numericCount = new int[11]; 

        // Count special characters and numerical values considering password frequencies
        for (String password : passwordFrequency.keySet()) {
            int frequency = passwordFrequency.get(password);
            int specialChar = countSpecialCharacters(password) * frequency;
            int numeric = countNumericValues(password) * frequency;
            if (specialChar >= 0 && specialChar < specialCharCount.length) {
                specialCharCount[specialChar]++;
            } else {
                specialCharCount[specialCharCount.length - 1]++; // Increment the last index for counts exceeding the array size
            }
            if (numeric >= 0 && numeric < numericCount.length) {
                numericCount[numeric]++;
            } else {
                numericCount[numericCount.length - 1]++; // Increment the last index for counts exceeding the array size
            }
        }

        // Display results for special characters
        System.out.println("Number of Special Characters   Number of Passwords");
        for (int i = 0; i < specialCharCount.length; i++) {
            System.out.printf("%d                              %d%n", i, specialCharCount[i]);
        }
        
        // Display results for numerical values
        System.out.println("\nNumber of Numerical Values   Number of Passwords");
        for (int i = 0; i < numericCount.length; i++) {
            System.out.printf("%d                              %d%n", i, numericCount[i]);
        }
    }

    // Method to count special characters in a password
    private static int countSpecialCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                count++; // Count special characters
            }
        }
        return count;
    }

    // Method to count numerical values in a password
    private static int countNumericValues(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                count++; // Count numerical values
            }
        }
        return count;
    }
}
