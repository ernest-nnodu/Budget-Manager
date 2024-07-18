package budget;

import java.util.Scanner;

public class Console {

    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.println(prompt);

        return scanner.nextLine();
    }
}
