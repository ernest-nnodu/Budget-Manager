package budget;

import java.util.*;

public class BudgetManagerApp {

    private static final int BACK_OPTION = 5;
    private static final int LIST_BACK_OPTION = 6;
    private static final String FILENAME = "purchases.txt";
    private static final String INCOME_LABEL = "Income";

    private enum MenuOption {
        EXIT(0),
        ADD_INCOME(1),
        ADD_PURCHASE(2),
        LIST_PURCHASES(3),
        DISPLAY_BALANCE(4),
        SAVE_PURCHASES(5),
        LOAD_PURCHASES(6),
        ANALYZE_PURCHASES(7);

        private final int value;

        MenuOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static MenuOption fromInt(int option) {
            for (MenuOption menuOption : values()) {
                if (menuOption.value == option) {
                    return menuOption;
                }
            }
            return null;
        }
    }

    enum PurchaseTypeMenuOption {

    }

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

        System.out.println("\nBye!");
    }

    private void displayMainMenu() {
        System.out.println("""

                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit""");
    }

    private boolean processMenu() {
        int userOption = console.readMenuOption();
        MenuOption menuOption = MenuOption.fromInt(userOption);

        if (menuOption == null) {
            System.out.println("Incorrect input value! Please select from the provided menu");
            return true;
        }

        switch (menuOption) {
            case EXIT:
                return false;
            case ADD_INCOME:
                addIncome();
                break;
            case ADD_PURCHASE:
                addPurchase();
                break;
            case LIST_PURCHASES:
                listPurchases();
                break;
            case DISPLAY_BALANCE:
                displayBalance();
                break;
            case SAVE_PURCHASES:
                savePurchases();
                break;
            case LOAD_PURCHASES:
                loadPurchases();
                break;
            case ANALYZE_PURCHASES:
                analyzePurchases();
        }
        return true;
    }

    private void analyzePurchases() {
        boolean continueAnalyzing = true;

        while (continueAnalyzing) {
            printSortingMenu();
            int sortingType = console.readMenuOption();

            continueAnalyzing = handleSortingOption(sortingType);
        }
    }

    private boolean handleSortingOption(int sortingType) {
        switch (sortingType) {
            case 1:
                sortAllPurchases();
                break;
            case 2:
                sortPurchasesByType();
                break;
            case 3:
                sortPurchasesBySpecificType();
                break;
            case 4:
                return false; // Exit the loop and stop analyzing purchases
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true; // Continue analyzing purchases
    }

    private void sortPurchasesBySpecificType() {
        displayTypeOfPurchaseToSortMenu();
        int typeOfPurchase = console.readMenuOption();

        PurchaseCategory category = getPurchaseCategoryByType(typeOfPurchase);
        if (category == null) {
            System.out.println("\nInvalid option selected!");
            return;
        }

        List<Transaction> purchases = budgetManager.getPurchasesByCategory(category);
        if (purchases.isEmpty()) {
            displayEmptyPurchaseListMessage();
            return;
        }

        displaySortedPurchases(purchases, category);
        displayTotalExpense();
    }

    private PurchaseCategory getPurchaseCategoryByType(int typeOfPurchase) {
        return switch (typeOfPurchase) {
            case 1 -> PurchaseCategory.FOOD;
            case 2 -> PurchaseCategory.CLOTHES;
            case 3 -> PurchaseCategory.ENTERTAINMENT;
            case 4 -> PurchaseCategory.OTHER;
            default -> null;
        };
    }

    private void displaySortedPurchases(List<Transaction> purchases, PurchaseCategory category) {
        List<Transaction> sortedPurchases = new ArrayList<>(purchases);
        sortedPurchases.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());

        System.out.println("\n" + category.name() + ":");

        for (Transaction purchase : sortedPurchases) {
            System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getAmount()));
        }
    }

    private void displayTypeOfPurchaseToSortMenu() {
        System.out.println("""

                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
    }

    private void sortPurchasesByType() {
        Map<String, Double> purchasesByType = calculateExpensesByCategory();
        List<Map.Entry<String, Double>> sortedEntries = sortEntriesByAmountDescending(purchasesByType);

        displaySortedPurchasesByType(sortedEntries);
        displayTotalExpense();
    }

    private void displaySortedPurchasesByType(List<Map.Entry<String, Double>> sortedEntries) {
        System.out.println("\nTypes:");
        for (Map.Entry<String, Double> entry : sortedEntries) {
            System.out.println(entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
        }
    }

    private List<Map.Entry<String, Double>> sortEntriesByAmountDescending(Map<String, Double> purchasesByType) {
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(purchasesByType.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entryList;
    }

    private Map<String, Double> calculateExpensesByCategory() {
        Map<String, Double> expensesByCategory = new HashMap<>();
        for (PurchaseCategory category : PurchaseCategory.values()) {
            if (category != PurchaseCategory.ALL) {
                double total = budgetManager.calculateExpenseTotalByCategory(category);
                expensesByCategory.put(category.name().charAt(0) + category.name().substring(1).toLowerCase(),
                        total);
            }
        }
        return expensesByCategory;
    }

    private void sortAllPurchases() {
        List<Transaction> purchases = new ArrayList<>(budgetManager.getPurchases());

        if (purchases.isEmpty()) {
            displayEmptyPurchaseListMessage();
            return;
        }

        sortPurchasesByAmountDescending(purchases);
        displaySortedPurchases(purchases, "All");
        displayTotalExpense();
    }

    private void displayTotalExpense() {
        System.out.println("Total sum: $" + String.format("%.2f", budgetManager.calculateExpenseTotal()));
    }

    private void displaySortedPurchases(List<Transaction> purchases, String header) {
        System.out.println("\n" + header + ":");
        for (Transaction purchase : purchases) {
            System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getAmount()));
        }
    }

    private void sortPurchasesByAmountDescending(List<Transaction> purchases) {

        purchases.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
    }

    private void displayEmptyPurchaseListMessage() {
        System.out.println("\nThe purchase list is empty!");
    }

    private void printSortingMenu() {
        System.out.println("""

                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");
    }

    private void loadPurchases() {
        List<String> loadedData = fileHandler.load();
        budgetManager = new BudgetManager(); // Reset the budget manager

        processLoadedData(loadedData);

        System.out.println("\nPurchases were loaded!");
    }

    private void processLoadedData(List<String> loadedData) {
        for (String data : loadedData) {
            if (isSeparator(data)) {
                continue;
            }

            if (isIncomeData(data)) {
                processIncomeData(data);
            } else {
                processTransactionData(data);
            }
        }
    }

    private void processTransactionData(String data) {
        String[] transactionData = data.split(fileHandler.getDelimiter());
        String name = transactionData[0];
        PurchaseCategory category = PurchaseCategory.valueOf(transactionData[1]);
        double amount = Double.parseDouble(transactionData[3]);

        budgetManager.addPurchase(name, amount, category);
    }

    private void processIncomeData(String data) {
        double incomeAmount = parseIncomeAmount(data);
        budgetManager.addIncome(INCOME_LABEL, incomeAmount);
    }

    private double parseIncomeAmount(String data) {
        String[] splitData = data.split(fileHandler.getDelimiter());
        return Double.parseDouble(splitData[1]);
    }

    private boolean isIncomeData(String data) {
        return data.startsWith(INCOME_LABEL);
    }

    private boolean isSeparator(String data) {
        return data.equals(fileHandler.getSeparator());
    }

    private void savePurchases() {
        List<Transaction> purchases = fetchPurchases(PurchaseCategory.ALL);
        double income = fetchIncome();

        saveDataToFile(purchases, income);
        displaySaveConfirmation();
    }

    private void saveDataToFile(List<Transaction> purchases, double income) {
        fileHandler.save(BudgetManagerApp.FILENAME, purchases, income);
    }

    private void displaySaveConfirmation() {
        System.out.println("\nPurchases were saved!");
    }

    private double fetchIncome() {
        return budgetManager.getTotalIncome();
    }

    private void displayBalance() {
        System.out.println("\nBalance: $" + budgetManager.calculateBalance());
    }

    private void listPurchases() {
        while (true) {
            displayListOfPurchaseMenu();

            int purchaseType = console.readPurchaseType();
            if (purchaseType == LIST_BACK_OPTION) {
                break; // Exit the loop if the back option is selected
            }

            PurchaseCategory category = getPurchaseType(purchaseType);
            List<Transaction> purchases = fetchPurchases(category);

            if (purchases.isEmpty()) {
                System.out.println("\nThe purchase list is empty");
                continue; // Continue looping to allow another choice
            }

            displayPurchases(category, purchases);
            displayTotalSum(category);
        }
    }

    private void displayTotalSum(PurchaseCategory category) {
        double totalSum = (category == PurchaseCategory.ALL)
                ? budgetManager.calculateExpenseTotal()
                : budgetManager.calculateExpenseTotalByCategory(category);

        System.out.println("Total sum: $" + String.format("%.2f", totalSum));
    }

    private void displayPurchases(PurchaseCategory category, List<Transaction> purchases) {
        System.out.println("\n" + category.name());
        for (Transaction purchase : purchases) {
            System.out.println(purchase.getName() + " $" + String.format("%.2f", purchase.getAmount()));
        }
    }

    private List<Transaction> fetchPurchases(PurchaseCategory category) {
        if (category == PurchaseCategory.ALL) {
            return budgetManager.getPurchases();
        } else {
            return budgetManager.getPurchasesByCategory(category);
        }
    }

    private void displayListOfPurchaseMenu() {
        System.out.println("""

                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
    }

    private void addPurchase() {

        while (true) {
            displayPurchaseTypeMenu();

            int purchaseType = console.readPurchaseType();
            if (purchaseType == BACK_OPTION) {
                break; // Exit the loop if the back option is selected
            }

            PurchaseCategory category = getPurchaseType(purchaseType);
            if (category != null) {
                handlePurchase(category);
            } else {
                System.out.println("Invalid purchase type selected!");
            }
        }
    }

    private void handlePurchase(PurchaseCategory category) {
        String purchaseName = promptForPurchaseName();
        double purchaseAmount = promptForPurchaseAmount();

        budgetManager.addPurchase(purchaseName, purchaseAmount, category);
        System.out.println("Purchase was added!");
    }

    private double promptForPurchaseAmount() {
        String purchaseAmountPrompt = "Enter its price:";
        return console.readPurchaseAmount(purchaseAmountPrompt);
    }

    private String promptForPurchaseName() {
        String purchaseNamePrompt = "Enter purchase name:";
        return console.readPurchaseName(purchaseNamePrompt);
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
        System.out.println("""

                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
    }

    private void addIncome() {
        String incomePrompt = "Enter income";
        String incomeAddMessage = "\nIncome was added!";

        double incomeAmount = console.readIncome(incomePrompt);
        budgetManager.addIncome("income", incomeAmount);
        System.out.println(incomeAddMessage);
    }
}
