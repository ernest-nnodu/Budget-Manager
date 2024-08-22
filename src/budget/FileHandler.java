package budget;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "-----";
    private static final String DEFAULT_FILENAME = "purchases.txt";

    public String getDelimiter() {
        return DELIMITER;
    }

    public String getSeparator() {
        return SEPARATOR;
    }

    // Save transactions and total income to the file
    public void save(String filename, List<Transaction> transactions, double totalIncome) {
        File fileToSave = new File(filename);

        try (PrintWriter writer = new PrintWriter(fileToSave)) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.getName() + DELIMITER +
                        transaction.getCategory() + DELIMITER +
                        transaction.getType() + DELIMITER +
                        transaction.getAmount());
            }
            writer.println(SEPARATOR);
            writer.println("Income" + DELIMITER + totalIncome);
        } catch (IOException exception) {
            System.out.println("Failed to save data to file: " + exception.getMessage());
            // Optionally, log the exception or take additional recovery steps
        }
    }

    // Load transaction data from the file
    public List<String> load(String filename) {
        List<String> data = new ArrayList<>();
        File transactionFile = new File(filename);

        if (!transactionFile.exists()) {
            System.out.println("File not found: " + filename);
            return data;
        }

        try (Scanner scanner = new Scanner(transactionFile)) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (IOException exception) {
            System.out.println("Failed to load data from file: " + exception.getMessage());
            // Optionally, log the exception or take additional recovery steps
        }

        return data;
    }

    // Overloaded method to load using the default filename
    public List<String> load() {
        return load(DEFAULT_FILENAME);
    }
}