package budget;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetManagerTest {

   private BudgetManager budgetManager;

    @Before
    public void setUp() {
        budgetManager = new BudgetManager();
    }

    @Test
    @DisplayName("Budget Manager Initialised successfully")
    public void budgetManagerInitTest() {
        List<Transaction> purchases = budgetManager.getPurchases();

        assertEquals(0, purchases.size());
    }

    @Test
    @DisplayName("Purchases added successfully")
    public void addPurchasesTest() {
        budgetManager.addPurchase("item1", 23.50, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item2", 67.0, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item3", 509, PurchaseCategory.OTHER);

        List<Transaction> purchases = budgetManager.getPurchases();

        assertEquals(3, purchases.size());
        assertEquals("item1", purchases.get(0).getName());
        assertEquals(23.50, purchases.get(0).getAmount());
        assertEquals("item2", purchases.get(1).getName());
        assertEquals(67, purchases.get(1).getAmount());
        assertEquals("item3", purchases.get(2).getName());
        assertEquals(509, purchases.get(2).getAmount());
    }

    @Test
    @DisplayName("Calculate total amount of purchases")
    public void calculateExpenseTotalTest() {
        budgetManager.addPurchase("item1", 23.50, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item2", 67.0, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item3", 509, PurchaseCategory.OTHER);

        assertEquals(599.50, budgetManager.calculateExpenseTotal());
    }

    @Test
    @DisplayName("Total income accurate")
    public void totalIncomeTest() {
        budgetManager.addIncome("Income", 1000);
        budgetManager.addIncome("Income", 100.78);

        assertEquals(1100.78, budgetManager.getTotalIncome());
    }

    @Test
    @DisplayName("Calculate balance accurately")
    public void calculateBalanceTest() {
        budgetManager.addIncome("Income", 1000);
        budgetManager.addPurchase("item1", 23.50, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item2", 67.0, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item3", 509, PurchaseCategory.OTHER);

        assertEquals(400.50, budgetManager.calculateBalance());
    }

    public void calculateExpenseTotalByCategoryTest() {

        budgetManager.addPurchase("item1", 23.50, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item2", 67.0, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item3", 509, PurchaseCategory.OTHER);
        budgetManager.addPurchase("item4", 309, PurchaseCategory.FOOD);
        budgetManager.addPurchase("item5", 709, PurchaseCategory.FOOD);

        assertEquals(599.50, budgetManager.calculateExpenseTotalByCategory(PurchaseCategory.OTHER));
    }
}