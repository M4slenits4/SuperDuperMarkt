package superdupermarkt.product.types;

import java.time.Instant;

/**
 * The class {@code Cheese} extends the abstract class {@code Product} and represents the
 * Cheese product type. It implements the behavior of cheese, in particular the daily loss of quality
 * and checking the best-before date.
 */
public class Cheese extends Product {
    /** The basic price of the cheese. */
    private static final double BASIC_PRICE = 6.00;
    /** The minimum quality level of the cheese. */
    private static final int MINIMUM_QUALITY_LEVEL = 30;
    /** Number of daily quality loss */
    private static final int DAILY_QUALITY_DECREMENT = 1;

    /**
     * Creates a new {@code Cheese} object with the specified attributes.
     *
     * @param label      The label of the cheese.
     * @param quality    The initial quality level of the cheese.
     * @param expireDate The expiration date.
     */
    public Cheese(String label, int quality, Instant expireDate) {
        super(label, quality, expireDate, BASIC_PRICE);
        updatePrice();
    }

    /**
     * {@inheritDoc}
     * Checks if the current quality level of the cheese meets or exceeds the defined {@link #MINIMUM_QUALITY_LEVEL}.
     *
     * @return {@code true} if the current quality level is greater than or equal to
     * {@link #MINIMUM_QUALITY_LEVEL}, {@code false} otherwise.
     */
    @Override
    public boolean checkQualityLevel() {
        return getQuality() >= MINIMUM_QUALITY_LEVEL;
    }

    /**
     * {@inheritDoc}
     * Updates the quality level of the cheese by decreasing it by the
     * {@link #DAILY_QUALITY_DECREMENT}each time this method is called.
     *
     * @param today The current date and time.
     */
    @Override
    public void updateQualityLevel(Instant today) {
            setQuality(getQuality() - DAILY_QUALITY_DECREMENT);
    }

    /**
     * {@inheritDoc}
     * Updates the price of the cheese based on its current quality level.
     * The price is calculated using the {@link #calculatePrice(double, int)} method.
     */
    @Override
    public void updatePrice() {
        calculatePrice(BASIC_PRICE, getQuality());
    }

    /**
     * {@inheritDoc}
     * Checks if the cheese is expired based on its expiration date.
     *
     * @param today The current date and time.
     * @return {@code true} if the cheese's expiration date is before the {@code today}'s
     * date, indicating that it is expired; {@code false} otherwise.
     */
    @Override
    public boolean isExpired(Instant today) {
        return getExpireDate().isBefore(today);
    }
}
