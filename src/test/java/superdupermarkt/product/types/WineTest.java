package superdupermarkt.product.types;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


/**
 * Junit tests for {@link WineTest} class.
 */
public class WineTest {

    private Wine wine;
    private Instant now;
    private Instant expiryDate;

    /**
     * Sets up the test fixture before performing each test method.
     */
    @BeforeEach
    public void setUp() {
        now = Instant.now();
        expiryDate = now.plus(55, ChronoUnit.DAYS);
        wine = new Wine("Chardonnay", 40, expiryDate);
    }

    /**
     * Tests the {@link Wine#checkQualityLevel()} method when the quality is above zero.
     * Expects the method to return {@code true}.
     */
    @Test
    public void checkQualityLevel_qualityAboveZero_returnsTrue() {
        assertTrue(wine.checkQualityLevel());
    }

    /**
     * Tests the {@link Wine#checkQualityLevel()} method when the quality is zero.
     * Expects the method to return {@code true}.
     */
    @Test
    public void checkQualityLevel_qualityZero_returnsTrue() {
        wine.setQuality(0);
        assertFalse(wine.checkQualityLevel());
    }

    /**
     * Tests the {@link Wine#checkQualityLevel()} method when the quality is under zero.
     * Expects the method to return {@code true}.
     */
    @Test
    public void checkQualityLevel_qualityBelowZero_returnsFalse() {
        wine.setQuality(-10);
        assertFalse(wine.checkQualityLevel());
    }

    /**
     * Tests the {@link Wine#updateQualityLevel(Instant)} method when the quality is below the maximum
     * and the increase interval has passed.
     */
    @Test
    public void updateQualityLevel_qualityBelowMaxAndIncreaseIntervalPassed_increasesQuality() {
        int wineQuality = wine.getQuality();
        Instant increaseDay = wine.getDayOfIncreasingQuality().plus(Duration.ofDays(1));
        wine.updateQualityLevel(increaseDay);
        assertEquals(wineQuality + 1, wine.getQuality());
    }

    /**
     * Tests the {@link Wine#updateQualityLevel(Instant)} method when the quality is at the maximum
     * and the increase interval has passed.
     */
    @Test
    public void updateQualityLevel_qualityAtMax_doesNotIncreaseQuality() {
        wine.setQuality(55);
        int wineQuality = wine.getQuality();
        Instant increaseDay = wine.getDayOfIncreasingQuality().plus(Duration.ofDays(1));
        wine.updateQualityLevel(increaseDay);
        assertEquals(wineQuality, wine.getQuality());
    }

    /**
     * Tests the {@link Wine#updateQualityLevel(Instant)} method when the quality is below the maximum
     * and the increase interval has not passed.
     */
    @Test
    public void updateQualityLevel_qualityBelowMaxAndIncreaseIntervalNotPassed_doesNotIncreaseQuality() {
        int wineQuality = wine.getQuality();
        Instant increaseDay = wine.getDayOfIncreasingQuality().minus(Duration.ofDays(1));
        wine.updateQualityLevel(increaseDay);
        assertEquals(wineQuality, wine.getQuality());
    }

    /**
     * Tests the {@link Wine#updatePrice()} method to ensure it sets the price to the {@link Wine#BASIC_PRICE}
     * if the current price is different.
     */
    @Test
    public void updatePrice_currentPriceNotBasicPrice_setsPriceToBasicPrice() {
        wine.setPrice(20.00);
        wine.updatePrice();
        assertEquals(Wine.BASIC_PRICE, wine.getPrice());
    }

    /**
     * Tests the {@link Wine#updatePrice()} method to ensure the price is the {@link Wine#BASIC_PRICE}.
     */
    @Test
    public void updatePrice_currentPriceIsBasicPrice_priceRemainsBasicPrice() {
        wine.setPrice(Wine.BASIC_PRICE);
        wine.updatePrice();
        assertEquals(Wine.BASIC_PRICE, wine.getPrice());
    }

    /**
     * Tests the {@link Wine#isExpired(Instant)} method.
     * According to the class description, the wine never expire.
     */
    @Test
    public void isExpired_alwaysReturnsFalse() {
        assertFalse(wine.isExpired(now));
        assertFalse(wine.isExpired(expiryDate.plus(42, ChronoUnit.DAYS)));
        assertFalse(wine.isExpired(expiryDate.minus(42, ChronoUnit.DAYS)));
    }

}
