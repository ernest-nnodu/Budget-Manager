package budget;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    @Test
    @DisplayName("Object successfully created")
    public void testConstructorsAndGetters() {

        Purchase purchase = new Purchase("item", 10.75);

        assertEquals("item", purchase.getName());
        assertEquals(10.75, purchase.getAmount(), 0.001);
    }

    @Test
    @DisplayName("Object successfully modified")
    public void testConstructorsAndSetters() {

        Purchase purchase = new Purchase("item", 10.75);
        purchase.setName("Apple");
        purchase.setAmount(43.10);

        assertEquals("Apple", purchase.getName());
        assertEquals(43.10, purchase.getAmount());
    }
}