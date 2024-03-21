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
        int[] specialCharCount = new int[11]; // Increase size to accommodate more special characters
        int[] numericCount = new int[11]; // Array to store count of numerical characters
        int[] lowercaseCount = new int[11]; // Array to store count of lowercase characters
        int[] uppercaseCount = new int[11]; // Array to store count of uppercase characters
        int[] lengthCount = new int[11]; // Array to store count of password lengths (up to 10 characters)
        int[] whitespaceCount = new int[11]; // Array to store count of whitespace characters
        int[] whitespaceStartCount = new int[11]; // Array to store count of whitespace characters at the beginning of passwords
        int[] whitespaceEndCount = new int[11]; // Array to store count of whitespace characters at the end of passwords

        // Count special characters, numerical characters, lowercase characters, uppercase characters, password lengths, and whitespace characters considering password frequencies
        for (String password : passwordFrequency.keySet()) {
            int frequency = passwordFrequency.get(password);
            int count = countSpecialCharacters(password) * frequency;
            int numeric = countNumericCharacters(password) * frequency;
            int lowercase = countLowercaseCharacters(password) * frequency;
            int uppercase = countUppercaseCharacters(password) * frequency;
            int length = password.length();
            int whitespace = countWhitespaceCharacters(password) * frequency;
            int whitespaceStart = countWhitespaceAtStart(password) * frequency;
            int whitespaceEnd = countWhitespaceAtEnd(password) * frequency;
            
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

            // Update length count array
            if (length >= 0 && length < lengthCount.length) {
                lengthCount[length]++;
            } else {
                lengthCount[lengthCount.length - 1]++;
            }

            // Update whitespace count array
            if (whitespace >= 0 && whitespace < whitespaceCount.length) {
                whitespaceCount[whitespace]++;
            } else {
                whitespaceCount[whitespaceCount.length - 1]++;
            }

            // Update whitespace start count array
            if (whitespaceStart >= 0 && whitespaceStart < whitespaceStartCount.length) {
                whitespaceStartCount[whitespaceStart]++;
            } else {
                whitespaceStartCount[whitespaceStartCount.length - 1]++;
            }

            // Update whitespace end count array
            if (whitespaceEnd >= 0 && whitespaceEnd < whitespaceEndCount.length) {
                whitespaceEndCount[whitespaceEnd]++;
            } else {
                whitespaceEndCount[whitespaceEndCount.length - 1]++;
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
        
        System.out.println("\nLength of Password   Number of Passwords");
        for (int i = 0; i < lengthCount.length; i++) {
            System.out.printf("%d                     %d%n", i, lengthCount[i]);
        }

        System.out.println("\nNumber of White Space Characters    Number of Passwords");
        for (int i = 0; i < whitespaceCount.length; i++) {
            System.out.printf("%d                     %d%n", i, whitespaceCount[i]);
        }
        
        System.out.println("\nNumber of WS Char at Start    Number of WSp Char at End    Number of Passwords");
        for (int i = 0; i < whitespaceEndCount.length; i++) {
            System.out.printf("%d                              %d                              %d%n", i, whitespaceStartCount[i], whitespaceEndCount[i]);
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

    private static int countWhitespaceCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isWhitespace(ch)) {
                count++;
            }
        }
        return count;
    }

    private static int countWhitespaceAtStart(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isWhitespace(ch)) {
                count++;
            } else {
                break; // Stop counting when a non-whitespace character is encountered
            }
        }
        return count;
    }

    private static int countWhitespaceAtEnd(String password) {
        int count = 0;
        for (int i = password.length() - 1; i >= 0; i--) {
            char ch = password.charAt(i);
            if (Character.isWhitespace(ch)) {
                count++;
            } else {
                break; // Stop counting when a non-whitespace character is encountered
            }
        }
        return count;
    }
}
