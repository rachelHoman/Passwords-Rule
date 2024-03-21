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
        int[] specialCharCount = new int[21]; // Increase size to accommodate more special characters
        int[] numericCount = new int[11]; // Array to store count of numerical characters
        int[] lowercaseCount = new int[11]; // Array to store count of lowercase characters
        int[] uppercaseCount = new int[11]; // Array to store count of uppercase characters

        // Count special characters, numerical characters, lowercase characters, and uppercase characters considering password frequencies
        for (String password : passwordFrequency.keySet()) {
            int frequency = passwordFrequency.get(password);
            int count = countSpecialCharacters(password) * frequency;
            int numeric = countNumericCharacters(password) * frequency;
            int lowercase = countLowercaseCharacters(password) * frequency;
            int uppercase = countUppercaseCharacters(password) * frequency;

            // Update special character count array
            if (count >= 0 && count < specialCharCount.length) {
                specialCharCount[count]++;
            } else {
                specialCharCount[specialCharCount.length - 1]++;
            }

            // Update numeric count array
            if (numeric >= 0 && numeric < numericCount.length) {
                numericCount[numeric]++;
            } else {
                numericCount[numericCount.length - 1]++;
            }

            // Update lowercase count array
            if (lowercase >= 0 && lowercase < lowercaseCount.length) {
                lowercaseCount[lowercase]++;
            } else {
                lowercaseCount[lowercaseCount.length - 1]++;
            }

            // Update uppercase count array
            if (uppercase >= 0 && uppercase < uppercaseCount.length) {
                uppercaseCount[uppercase]++;
            } else {
                uppercaseCount[uppercaseCount.length - 1]++;
            }
        }

        // Display results
        System.out.println("Number of Special Characters   Number of Passwords");
        for (int i = 0; i < specialCharCount.length; i++) {
            System.out.printf("%d                              %d%n", i, specialCharCount[i]);
        }

        System.out.println("\nNumber of Numeric Characters   Number of Passwords");
        for (int i = 0; i < numericCount.length; i++) {
            System.out.printf("%d                              %d%n", i, numericCount[i]);
        }

        System.out.println("\nNumber of Lowercase Characters   Number of Uppercase Characters   Number of Passwords");
        for (int i = 0; i < lowercaseCount.length; i++) {
            System.out.printf("%d                              %d                              %d%n", i, uppercaseCount[i], lowercaseCount[i]);
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

    // Method to count numeric characters in a password
    private static int countNumericCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                count++;
            }
        }
        return count;
    }

    // Method to count lowercase characters in a password
    private static int countLowercaseCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                count++;
            }
        }
        return count;
    }

    // Method to count uppercase characters in a password
    private static int countUppercaseCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                count++;
            }
        }
        return count;
    }
}
