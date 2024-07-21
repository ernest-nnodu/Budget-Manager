package budget;

import java.util.List;

public class BudgetManagerApp {

    private final BudgetManager budgetManager;
    private final Console console;

    public BudgetManagerApp() {
        budgetManager = new BudgetManager();
        console = new Console();
    }

    public void start() {
        boolean exitNotSelected = true;

        do {
            displayMenu();
            exitNotSelected = processMenu();
        } while (exitNotSelected);

        System.out.println();
        System.out.println("Bye!");
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
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
        }
        return true;
    }

    private void displayBalance() {
        System.out.println();
        System.out.println("Balance: $" + budgetManager.calculateBalance());
    }

    private void listPurchases() {

        System.out.println();

        List<Transaction> purchases = budgetManager.getPurchases();
        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty");
            return;
        }

        for (Transaction purchase : purchases) {
            System.out.println(purchase.getName() + " $" + purchase.getAmount());
        }
        System.out.println("Total sum: $" + budgetManager.calculateExpenseTotal());
    }

    private void addPurchase() {
        System.out.println();
        String purchaseNamePrompt = "Enter purchase name:";
        String purchaseAmountPrompt = "Enter its price:";
        String purchaseAddMessage = "Purchase was added!";

        String purchaseName = console.readPurchaseName(purchaseNamePrompt);
        double purchaseAmount = console.readPurchaseAmount(purchaseAmountPrompt);
        budgetManager.addPurchase(purchaseName, purchaseAmount);

        System.out.println(purchaseAddMessage);
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
