package budget;

public class BudgetManagerApp {

    /*private final BudgetManager budgetManager;
    private final Console console;

    public BudgetManagerApp() {
        budgetManager = new BudgetManager();
        console = new Console();
    }

    public void start() {
        readPurchases();
        processPurchases();
    }

    private void readPurchases() {
        boolean purchasesAvailable = true;

        do {
            String input = console.readString();

            if (input == null) {
                purchasesAvailable = false;
                continue;
            }

            String purchaseName = input.substring(0, input.lastIndexOf("$"));
            double purchaseAmount = Double.parseDouble(input.substring(input.lastIndexOf("$") + 1));
            Purchase purchase = new Purchase(purchaseName, purchaseAmount);
            budgetManager.addPurchase(purchase);
        } while(purchasesAvailable);
    }

    private void processPurchases() {
        budgetManager.displayPurchases();
        System.out.println("\nTotal: $" + budgetManager.calculateTotal());
    }*/
}
