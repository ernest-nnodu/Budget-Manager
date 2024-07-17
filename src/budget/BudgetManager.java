package budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager {

    //define fields
    List<Purchase> purchases;

    //define constructor
    public BudgetManager() {
        purchases = new ArrayList<>();
    }

    //define method to add purchase item to the list
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    //define method to display purchase list
    public void displayPurchases() {
        purchases.forEach(e ->
                System.out.println(e.getName() + " $" + e.getAmount()));
    }

    //define method to calculate total amount of purchase list
    public double calculateTotal() {
        double total = 0;

        for (Purchase purchase : purchases) {
            total += purchase.getAmount();
        }
        return total;
    }
}
