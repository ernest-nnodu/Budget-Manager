package budget;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    @DisplayName("Purchase transaction successfully created")
    public void testPurchaseConstructorsAndGetters() {

        Transaction purchase = new Transaction("item", TransactionType.EXPENSE, 10.75);

        assertEquals("item", purchase.getName());
        assertEquals(10.75, purchase.getAmount(), 0.001);
        assertEquals(TransactionType.EXPENSE, purchase.getType());
    }

    @Test
    @DisplayName("Purchase transaction successfully modified")
    public void testPurchaseConstructorsAndSetters() {

        Transaction purchase = new Transaction();
        purchase.setName("Apple");
        purchase.setAmount(43.10);
        purchase.setType(TransactionType.EXPENSE);

        assertEquals("Apple", purchase.getName());
        assertEquals(43.10, purchase.getAmount());
        assertEquals(TransactionType.EXPENSE, purchase.getType());
    }

    @Test
    @DisplayName("Income transaction successfully created")
    public void testIncomeConstructorsAndGetters() {

        Transaction income = new Transaction("income", TransactionType.INCOME, 1000);

        assertEquals("income", income.getName());
        assertEquals(1000, income.getAmount(), 0.001);
        assertEquals(TransactionType.INCOME, income.getType());
    }

    @Test
    @DisplayName("Income transaction successfully modified")
    public void testIncomeConstructorsAndSetters() {

        Transaction income = new Transaction();
        income.setName("Pay");
        income.setAmount(4310.57);
        income.setType(TransactionType.INCOME);

        assertEquals("Pay", income.getName());
        assertEquals(4310.57, income.getAmount());
        assertEquals(TransactionType.INCOME, income.getType());
    }
}