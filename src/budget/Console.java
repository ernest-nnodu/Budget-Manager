package budget;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public String readString() {
        String input = null;

        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
