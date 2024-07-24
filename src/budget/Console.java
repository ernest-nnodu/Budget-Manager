package budget;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public int readMenuOption() {
        return Integer.parseInt(scanner.nextLine());
    }

    public double readIncome(String prompt) {
        System.out.println(prompt);

        return Double.parseDouble(scanner.nextLine());
    }

    public String readPurchaseName(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public double readPurchaseAmount(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    public int readPurchaseType() {
        return Integer.parseInt(scanner.nextLine());
    }
}
