package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BudgetManagerApp {

    private BudgetManager budgetManager;
    private final Console console;
    private final FileHandler fileHandler;

    public BudgetManagerApp() {
        budgetManager = new BudgetManager();
        console = new Console();
        fileHandler = new FileHandler();
    }

    public void start() {
        boolean exitNotSelected = true;

        do {
            displayMainMenu();
            exitNotSelected = processMenu();
        } while (exitNotSelected);

        System.out.println();
        System.out.println("Bye!");
    }

    private void displayMainMenu() {
        System.out.println();
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("0) Exit");
    }

    private boolean processMenu() {
        int userOption = console.readMenuOption();

        switch (userOption) {
            case 0:
                return false;
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                listPurchases();
                break;
            case 4:
                displayBalance();
                break;
            case 5:
                savePurchases();
                break;
            case 6:
                loadPurchases();
        }
        return true;
    }

    private void loadPurchases() {

        java.util.Locale.setDefault(Locale.US);
        List<String> loadedData = fileHandler.load();
        Double incomeAmount;
        List<Transaction> purchases = new ArrayList<>();
        budgetManager = new BudgetManager();

        for (String data : loadedData) {
            if (data.equals(fileHandler.getSeparator())) {
                continue;
            } else if (data.startsWith("Income")) {
                incomeAmount = Double.parseDouble(data.split(fileHandler.getDelimiter())[1]);
                budgetManager.addIncome("income", incomeAmount);
            } else {
                String[] transactionData = data.split(fileHandler.getDelimiter());
                String name = transactionData[0];
                double amount = Double.parseDouble(transactionData[3]);
                budgetManager.addPurchase(name, amount, PurchaseCategory.valueOf(transactionData[1]));
            }
        }

        System.out.println();
        System.out.println("Purchases were loaded!");
    }

    private void savePurchases() {
        String filename = "purchases.txt";
        List<Transaction> purchases = budgetManager.getPurchases();
        double income = budgetManager.getTotalIncome();

        fileHandler.save(filename, purchases, income);

        System.out.println();
        System.out.println("Purchases were saved!");
    }

    private void displayBalance() {
        System.out.println();
        System.out.println("Balance: $" + budgetManager.calculateBalance());
    }

    private void listPurchases() {

        while (true) {
            displayListOfPurchaseMenu();

            int purchaseType = console.readPurchaseType();
            if (purchaseType == 6) {
                return;
            }

            PurchaseCategory category = getPurchaseType(purchaseType);

            List<Transaction> purchases;
            if (category == PurchaseCategory.ALL) {
                purchases = budgetManager.getPurchases();
            } else {
                purchases = budgetManager.getPurchasesByCategory(category);
            }

            if (purchases.isEmpty()) {
                System.out.println();
                System.out.println("The purchase list is empty");
                return;
            }

            System.out.println();
            System.out.println(category.name());

            for (Transaction purchase : purchases) {
                System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getAmount()));
            }

            if (category == PurchaseCategory.ALL) {
                System.out.println("Total sum: $" + budgetManager.calculateExpenseTotal());
            } else {
                System.out.println("Total sum: $" + budgetManager.calculateExpenseTotalByCategory(category));
            }
        }
    }

    private void displayListOfPurchaseMenu() {
        System.out.println();
        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }

    private void addPurchase() {

        while (true) {
            displayPurchaseTypeMenu();

            int purchaseType = console.readPurchaseType();

            if (purchaseType == 5) {
                return;
            }

            PurchaseCategory category = getPurchaseType(purchaseType);

            System.out.println();
            String purchaseNamePrompt = "Enter purchase name:";
            String purchaseAmountPrompt = "Enter its price:";
            String purchaseAddMessage = "Purchase was added!";

            String purchaseName = console.readPurchaseName(purchaseNamePrompt);
            double purchaseAmount = console.readPurchaseAmount(purchaseAmountPrompt);
            budgetManager.addPurchase(purchaseName, purchaseAmount, category);

            System.out.println(purchaseAddMessage);
        }
    }

    private PurchaseCategory getPurchaseType(int purchaseType) {
        return switch (purchaseType) {
            case 1 -> PurchaseCategory.FOOD;
            case 2 -> PurchaseCategory.CLOTHES;
            case 3 -> PurchaseCategory.ENTERTAINMENT;
            case 4 -> PurchaseCategory.OTHER;
            default -> PurchaseCategory.ALL;
        };
    }

    private void displayPurchaseTypeMenu() {
        System.out.println();
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }

    private void addIncome() {
        System.out.println();
        String incomePrompt = "Enter income";
        String incomeAddMessage = "Income was added!";

        double incomeAmount = console.readIncome(incomePrompt);
        budgetManager.addIncome("income", incomeAmount);
        System.out.println(incomeAddMessage);
    }
}
