import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordAnalyzer {
    public static void main(String[] args) {
        String filename = "myspace-withcount.txt";
        // String filename = "rockyou1.txt";
        // String filename = "rockyou2.txt";
        // String filename = "test.txt";

        int total = 0;
        // Map to store password frequencies
        Map<String, Integer> passwordFrequency = new HashMap<>();
        // Array to store count of passwords with different number of special characters
        int[] specialCharCount = new int[11]; // Increase size to accommodate more special characters
        int[] numericCount = new int[11]; // Array to store count of numerical characters
        int[] lowercaseCount = new int[11]; // Array to store count of lowercase characters
        int[] uppercaseCount = new int[11]; // Array to store count of uppercase characters
        int[] lengthCount = new int[11]; // Array to store count of password lengths (up to 10 characters)
        int[] whitespaceCount = new int[11]; // Array to store count of whitespace characters
        // int[] whitespaceStartCount = new int[11]; // Array to store count of whitespace characters at the beginning of passwords
        // int[] whitespaceEndCount = new int[11]; // Array to store count of whitespace characters at the end of passwords
        // Count passwords that start or end with a space
        // int startWithSpaceCount = 0;
        int endWithSpaceCount = 0;

        // Read passwords from file and populate the map
        // try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        //     String line;
        //     while ((line = br.readLine()) != null) {
        //         line = line.trim();
        //         if (!line.isEmpty()) { // Skip empty lines
        //             String[] parts = line.split("\\s+", 2); // Split by the first occurrence of whitespace
        //             if (parts.length == 2) {
        //                 String frequencyStr = parts[0];
        //                 String password = parts[1];
        //                 int frequency = Integer.parseInt(frequencyStr);
        //                 passwordFrequency.put(password, frequency);
        //             }
        //         }
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove leading whitespaces using stripLeading()
                line = line.stripLeading();
                if (!line.isEmpty()) { // Skip empty lines
                    String[] parts = line.split("\\s+", 2); // Split by the first occurrence of whitespace
                    if (parts.length == 2 && !parts[0].isEmpty()) {
                        String frequencyStr = parts[0];
                        String password = parts[1];
                        int frequency = Integer.parseInt(frequencyStr);
                        passwordFrequency.put(password, frequency);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        // System.out.println(passwordFrequency);
        System.out.println(passwordFrequency.size());

        // Count special characters, numerical characters, lowercase characters, 
        // uppercase characters, password lengths, and whitespace characters considering password frequencies
        for (String password : passwordFrequency.keySet()) {
            int frequency = passwordFrequency.get(password);
            total += frequency;
            int count = countSpecialCharacters(password);
            int numeric = countNumericCharacters(password);
            int lowercase = countLowercaseCharacters(password);
            int uppercase = countUppercaseCharacters(password);
            int length = password.length();
            int whitespace = countWhitespaceCharacters(password);
            // int whitespaceStart = countWhitespaceAtStart(password);
            // int whitespaceEnd = countWhitespaceAtEnd(password);

            // if (password.startsWith(" ")) {
            //     startWithSpaceCount++;
            // }
            if (password.endsWith(" ")) {
                endWithSpaceCount+=frequency;
            }
            
            // // Update special character count array
            // if (count >= 0 && count < specialCharCount.length) {
            //     specialCharCount[count]+=frequency;
            // }else {
            //     specialCharCount[specialCharCount.length - frequency]+=frequency;
            // }
            // Update special character count array
            if (count >= 0 && count < specialCharCount.length) {
                specialCharCount[count] += frequency;
            } else {
                specialCharCount[specialCharCount.length - 1] += frequency;
            }

            // // Update numeric count array
            // if (numeric >= 0 && numeric < numericCount.length) {
            //     numericCount[numeric]+=frequency;
            // } 
            // else {
            //     numericCount[numericCount.length - 1]+=frequency;
            // }
            // Update numeric count array
            if (numeric >= 0 && numeric < numericCount.length) {
                numericCount[numeric] += frequency;
            } else {
                numericCount[numericCount.length - 1] += frequency;
            }

            // // Update lowercase count array
            // if (lowercase >= 0 && lowercase < lowercaseCount.length) {
            //     lowercaseCount[lowercase]+=frequency;
            // } 
            // else {
            //     lowercaseCount[lowercaseCount.length - 1]+=frequency;
            // }
            // Update lowercase count array
            if (lowercase >= 0 && lowercase < lowercaseCount.length) {
                lowercaseCount[lowercase] += frequency;
            } else {
                lowercaseCount[lowercaseCount.length - 1] += frequency;
            }

            // // Update uppercase count array
            // if (uppercase >= 0 && uppercase < uppercaseCount.length) {
            //     uppercaseCount[uppercase]+=frequency;
            // } 
            // else {
            //     uppercaseCount[uppercaseCount.length - 1]+=frequency;
            // }
            // Update uppercase count array
            if (uppercase >= 0 && uppercase < uppercaseCount.length) {
                uppercaseCount[uppercase] += frequency;
            } else {
                uppercaseCount[uppercaseCount.length - 1] += frequency;
            }

            // // Update length count array
            // if (length >= 0 && length < lengthCount.length) {
            //     lengthCount[length]+=frequency;
            // }else {
            //     lengthCount[lengthCount.length - 1]+=frequency;
            // }
            // Update length count array
            if (length >= 0 && length < lengthCount.length) {
                lengthCount[length] += frequency;
            } else {
                lengthCount[lengthCount.length - 1] += frequency;
            }

            // Update whitespace count array
            if (whitespace >= 0 && whitespace < whitespaceCount.length) {
                whitespaceCount[whitespace]+=frequency;
            }

            // // Update whitespace start count array
            // if (whitespaceStart >= 0 && whitespaceStart < whitespaceStartCount.length) {
            //     whitespaceStartCount[whitespaceStart]++;
            // }

            // // Update whitespace end count array
            // if (whitespaceEnd >= 0 && whitespaceEnd < whitespaceEndCount.length) {
            //     whitespaceEndCount[whitespaceEnd]++;
            // }
        }

        // Display results
        System.out.println("Number of Special Characters   Number of Passwords");
        for (int i = 0; i < specialCharCount.length; i++) {
            if (i == 10) {
                System.out.printf("%s                              %d%n", "10+", specialCharCount[i]);
            }
            else {
                System.out.printf("%d                              %d%n", i, specialCharCount[i]);
            }
        }

        System.out.println("\nNumber of Numeric Characters   Number of Passwords");
        for (int i = 0; i < numericCount.length; i++) {
            if (i == 10) {
                System.out.printf("%s                              %d%n", "10+", numericCount[i]);
            }
            else {
                System.out.printf("%d                              %d%n", i, numericCount[i]);
            }
        }

        System.out.println("\nNumber of Characters   Number of Uppercase Characters   Number of Lowercase Characters");
        for (int i = 0; i < lowercaseCount.length; i++) {
            if (i == 10) {
                System.out.printf("%s                              %d                              %d%n", "10+", uppercaseCount[i], lowercaseCount[i]);
            }
            else {
                System.out.printf("%d                              %d                              %d%n", i, uppercaseCount[i], lowercaseCount[i]);
            }
        }
        
        System.out.println("\nLength of Password   Number of Passwords");
        for (int i = 0; i < lengthCount.length; i++) {
            if (i == 10) {
                System.out.printf("%s                     %d%n", "10+", lengthCount[i]);
            }
            else {
                System.out.printf("%d                     %d%n", i, lengthCount[i]);
            }
        }

        System.out.println("\nNumber of White Space Characters    Number of Passwords");
        for (int i = 0; i < whitespaceCount.length; i++) {
            if (i == 10) {
                System.out.printf("%s                     %d%n", "10+", whitespaceCount[i]);
            }
            else {
                System.out.printf("%d                     %d%n", i, whitespaceCount[i]);
            }
        }

        // System.out.println("Number of passwords that start with a space: " + startWithSpaceCount);
        System.out.println("Number of passwords that end with a space: " + endWithSpaceCount);
        
        // System.out.println("\nNumber of WS Char at Start    Number of WSp Char at End    Number of Passwords");
        // for (int i = 0; i < whitespaceEndCount.length; i++) {
        //     if (i == 10) {
        //         System.out.printf("%s                              %d                              %d%n", "10+", whitespaceStartCount[i], whitespaceEndCount[i]);
        //     }
        //     else {
        //         System.out.printf("%d                              %d                              %d%n", i, whitespaceStartCount[i], whitespaceEndCount[i]);
        //     }
        // }
        System.out.println("total: " + total);
    }

    // Method to count number of special characters in a password
    private static int countSpecialCharacters(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != ' ') {
                count++;
            }
        }
        return count;
    }

    // Method to count number of numeric characters in a password
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
