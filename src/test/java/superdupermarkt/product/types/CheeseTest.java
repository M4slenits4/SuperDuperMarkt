package superdupermarkt.product.types;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Junit tests for {@link Cheese} class.
 */
public class CheeseTest {

    private Cheese cheese;
    private Instant now;

    /**
     * Sets up the test fixture before performing each test method.
     */
    @BeforeEach
    public void setUp() {
        now = Instant.now();
        cheese = new Cheese("Cheddar", 80, now.plus(35, ChronoUnit.DAYS));
    }

    /**
     * Tests the {@link Cheese#checkQualityLevel()} method when the quality is above the minimum.
     * Expects the method to return {@code true}.
     */
    @Test
    public void checkQualityLevel_qualityAboveMinimum_returnsTrue() {
        assertTrue(cheese.checkQualityLevel());
    }

    /**
     * Tests the {@link Cheese#checkQualityLevel()} method when the quality is at the minimum.
     * Expects the method to return {@code true}.
     */
    @Test
    public void checkQualityLevel_qualityAtMinimum_returnsTrue() {
        cheese.setQuality(30);
        assertTrue(cheese.checkQualityLevel());
    }

    /**
     * Tests the {@link Cheese#checkQualityLevel()} method when the quality is below the minimum.
     * Expects the method to return {@code false}.
     */
    @Test
    public void checkQualityLevel_qualityBelowMinimum_returnsFalse() {
        cheese.setQuality(10);
        assertFalse(cheese.checkQualityLevel());
    }

    /**
     * Tests the {@link Cheese#updateQualityLevel(Instant)} method to ensure the daily quality decrease.
     */
    @Test
    public void updateQualityLevel_decreasesQualityDaily() {
        int initialQuality = cheese.getQuality();
        cheese.updateQualityLevel(now.plus(1, ChronoUnit.DAYS));
        assertEquals(initialQuality - 1, cheese.getQuality());
    }

    /**
     * Tests the {@link Cheese#updatePrice()} method to verify the price is updated.
     * Checks if the price increases or decreases on the basis of quality.
     */
    @Test
    public void updatePrice_updatesPriceBasedOnQuality() {
        double initialPrice = cheese.getPrice();
        cheese.setQuality(cheese.getQuality() + 10);
        cheese.updatePrice();
        assertTrue(cheese.getPrice() > initialPrice);

        cheese.setQuality(cheese.getQuality() - 20);
        cheese.updatePrice();
        assertTrue(cheese.getPrice() < initialPrice);
    }

    /**
     * Tests the {@link Cheese#isExpired(Instant)} method when the expiry date is in the future.
     * Expects the method to return {@code false}.
     */
    @Test
    public void isExpired_expireDateInFuture_returnsFalse() {
        assertFalse(cheese.isExpired(now));
        assertFalse(cheese.isExpired(now.minus(1, ChronoUnit.DAYS)));
    }

    /**
     * Tests the {@link Cheese#isExpired(Instant)} method when the expiry date is now.
     * Expects the method to return {@code true}.
     */
    @Test
    public void isExpired_expireDateNow_returnsFalse() {
        Cheese todayCheese = new Cheese("Cheddar", 60, now);
        assertFalse(todayCheese.isExpired(now));
    }

    /**
     * Tests the {@link Cheese#isExpired(Instant)} method when the expiry date is in the past.
     * Expects the method to return {@code true}.
     */
    @Test
    public void isExpired_expireDateInPast_returnsTrue() {
        Instant pastDate = now.minus(1, ChronoUnit.DAYS);
        Cheese expiredCheese = new Cheese("Cheddar", 70, pastDate);
        assertTrue(expiredCheese.isExpired(now));
    }

}
