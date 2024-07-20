package budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager {

    //define fields
    List<Transaction> transactions;
    private double totalIncome;

    //define constructor
    public BudgetManager() {
        transactions = new ArrayList<>();
        totalIncome = 0;
    }

    public double getTotalIncome() {
        return this.totalIncome;
    }

    //define method to add expense to transaction list
    public void addPurchase(String name, double amount) {
        Transaction purchase = new Transaction(name, TransactionType.EXPENSE, amount);
        transactions.add(purchase);
    }

    //define method to add income to transaction list
    public void addIncome(String name, double amount) {
        Transaction income = new Transaction(name, TransactionType.INCOME, amount);
        transactions.add(income);
        totalIncome += amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    //define method to display purchase list
    public List<Transaction> getPurchases() {
        List<Transaction> purchases = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                purchases.add(transaction);
            }
        }
        return purchases;
    }

    //define method to calculate total amount of purchase list
    public double calculateExpenseTotal() {
        double total = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double calculateBalance() {
        return totalIncome - calculateExpenseTotal();
    }
}
