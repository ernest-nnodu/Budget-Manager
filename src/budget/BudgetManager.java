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
    public void addPurchase(String name, double amount, PurchaseCategory category) {
        Transaction purchase = new Transaction(name, TransactionType.EXPENSE, amount);
        purchase.setCategory(category);
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

        return transactions.stream()
                .filter(e -> e.getType() == TransactionType.EXPENSE)
                .toList();
    }

    public List<Transaction> getPurchasesByCategory(PurchaseCategory category) {

        return transactions.stream()
                .filter(e -> e.getCategory() == category)
                .toList();
    }

    //define method to calculate total amount of purchase list
    public double calculateExpenseTotal() {

        return transactions.stream()
                .filter(e -> e.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateExpenseTotalByCategory(PurchaseCategory category) {

        return transactions.stream()
                .filter(e -> e.getType() == TransactionType.EXPENSE && e.getCategory() == category)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double calculateBalance() {
        return totalIncome - calculateExpenseTotal();
    }
}
