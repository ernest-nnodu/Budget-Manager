package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetManager {

    // Fields
    private final List<Transaction> transactions;
    private double totalIncome;

    // Constructor
    public BudgetManager() {
        this.transactions = new ArrayList<>();
        this.totalIncome = 0;
    }

    // Getters
    public double getTotalIncome() {
        return this.totalIncome;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(this.transactions);
    }

    // Add a purchase to the transactions list
    public void addPurchase(String name, double amount, PurchaseCategory category) {
        Transaction purchase = new Transaction(name, TransactionType.EXPENSE, amount);
        purchase.setCategory(category);
        this.transactions.add(purchase);
    }

    // Add income to the transactions list and update totalIncome
    public void addIncome(String name, double amount) {
        Transaction income = new Transaction(name, TransactionType.INCOME, amount);
        this.transactions.add(income);
        this.totalIncome += amount;
    }

    // Get a list of all purchases (expenses)
    public List<Transaction> getPurchases() {
        return this.transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .collect(Collectors.toList());
    }

    // Get a list of purchases filtered by category
    public List<Transaction> getPurchasesByCategory(PurchaseCategory category) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE && transaction.getCategory() == category)
                .collect(Collectors.toList());
    }

    // Calculate the total expenses
    public double calculateExpenseTotal() {
        return this.transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Calculate total expenses by category
    public double calculateExpenseTotalByCategory(PurchaseCategory category) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE && transaction.getCategory() == category)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Calculate the current balance
    public double calculateBalance() {
        return this.totalIncome - calculateExpenseTotal();
    }
}
