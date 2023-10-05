import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCounter {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'text' to enter text manually or 'file' to provide a file path: ");
        String inputType = scanner.nextLine();

        String text = "";

        if (inputType.equalsIgnoreCase("text")) {
            System.out.println("Enter the text: ");
            text = scanner.nextLine();
        } else if (inputType.equalsIgnoreCase("file")) {
            System.out.println("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading file. Please check the file path and try again.");
                return;
            }
        } else {
            System.out.println("Invalid input type. Please enter 'text' or 'file'.");
            return;
        }

        String[] words = text.split("[\\s\\p{Punct}]+"); // Split using spaces or punctuation as delimiters
        int wordCount = words.length;

        System.out.println("Total word count: " + wordCount);
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}