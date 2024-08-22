package budget;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    // Constructor
    public Console() {
        this.scanner = new Scanner(System.in);
    }

    // Read a menu option and ensure it's an integer
    public int readMenuOption() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid option number.");
            }
        }
    }

    // Read income with a prompt and ensure it's a valid double
    public double readIncome(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid income amount.");
            }
        }
    }

    // Read purchase name with a prompt
    public String readPurchaseName(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    // Read purchase amount with a prompt and ensure it's a valid double
    public double readPurchaseAmount(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

    // Read purchase type and ensure it's an integer
    public int readPurchaseType() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid purchase type number.");
            }
        }
    }
}