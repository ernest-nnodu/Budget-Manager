package budget;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FileHandler {

    private final String delimiter = ",";
    private final String separator = "-----";

    public String getDelimiter() {
        return delimiter;
    }

    public String getSeparator() {
        return separator;
    }

    public void save(String filename, List<Transaction> transactions, double totalIncome) {
        File fileToSave = new File(filename);

        try(PrintWriter writer = new PrintWriter(fileToSave)) {
            transactions.forEach(e -> writer.println(e.getName() + delimiter
            + e.getCategory() + delimiter + e.getType() + delimiter + e.getAmount()));
            writer.println(separator);
            writer.println("Income," + totalIncome);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public List<String> load() {
        List<String> data = new ArrayList<>();
        File transactionFile = new File("purchases.txt");

        try(Scanner scanner = new Scanner(transactionFile)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine());
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return data;
    }
}
