package budget;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetManagerTest {

   /* private BudgetManager budgetManager;

    @Before
    public void setUp() {
        budgetManager = new BudgetManager();
    }

    @Test
    @DisplayName("Budget Manager Initialised successfully")
    public void budgetManagerInitTest() {
        List<Purchase> purchases = budgetManager.getPurchases();

        assertEquals(0, purchases.size());
    }

    @Test
    @DisplayName("Purchases added successfully")
    public void addPurchasesTest() {
        budgetManager.addPurchase(new Purchase("item1", 23.50));
        budgetManager.addPurchase(new Purchase("item2", 67.0));
        budgetManager.addPurchase(new Purchase("item3", 509));

        List<Purchase> purchases = budgetManager.getPurchases();

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
    public void calculateTotalAmountTest() {
        budgetManager.addPurchase(new Purchase("item1", 23.50));
        budgetManager.addPurchase(new Purchase("item2", 67.0));
        budgetManager.addPurchase(new Purchase("item3", 509));

        assertEquals(599.50, budgetManager.calculateTotal());
    }*/
}