package superdupermarkt.product.types;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

/**
 * The {@code Wine} class extends the abstract {@code Product} class and represents a specific
 * product type: wine. It implements the behavior of wine in terms of quality improvement over time.
 */
public class Wine extends Product{

    /** The basic price of the wine. */
    public static final double BASIC_PRICE = 10.00;
    /** The maximum quality level that can be achieved over time. */
    private static final int MAXIMUM_QUALITY_LEVEL = 50;
    /** The number of growing quality. */
    private static final int QUALITY_INCREMENT = 1;
    /** Day interval when the quality grows. It is 9 because it would grow after the tenth day */
    private static final Duration QUALITY_INCREASE_INTERVAL = Duration.ofDays(9);

    @Getter
    private Instant dayOfIncreasingQuality;

    /**
     * Creates a new {@code Wine} object with the specified attributes.
     * The timestamp for the quality increase is calculated by adding the
     * {@link #QUALITY_INCREASE_INTERVAL} to the expiration date.
     *
     * @param label      The label of the wine.
     * @param quality    The initial quality level of the wine.
     * @param expireDate The expiration date.
     */
    public Wine(String label, int quality, Instant expireDate) {
        super(label, quality, expireDate, BASIC_PRICE);
        // set the expiry date plus 10 for the quality increase
        dayOfIncreasingQuality = expireDate.plus(QUALITY_INCREASE_INTERVAL);
    }

    /**
     * {@inheritDoc}
     * Checks if the current quality level of the product is greater than zero.
     *
     * @return {@code true} if the current quality level is greater than 0,
     * {@code false} otherwise.
     */
    @Override
    public boolean checkQualityLevel() {
        return getQuality() > 0;
    }

    /**
     * {@inheritDoc}
     * Updates the quality level of the wine if it has not yet reached the{@link #MAXIMUM_QUALITY_LEVEL}
     * and if the specified{@link #QUALITY_INCREASE_INTERVAL} has passed since the last quality increase.
     *
     * @param today The current date and time.
     */
    @Override
    public void updateQualityLevel(Instant today) {
        // Check for maximum quality level.
        if (getQuality() < MAXIMUM_QUALITY_LEVEL) {
            // Check if the ten days have passed.
            if (today.isAfter(dayOfIncreasingQuality)) {
                setQuality(getQuality() + QUALITY_INCREMENT);
                dayOfIncreasingQuality = dayOfIncreasingQuality.plus(QUALITY_INCREASE_INTERVAL);
            }
        }
    }

    /**
     * {@inheritDoc}
     * Updates the price of the product to the {@link #BASIC_PRICE}.
     */
    @Override
    public void updatePrice() {
        if (getPrice() != BASIC_PRICE) {
            setPrice(BASIC_PRICE);
        }
    }

    /**
     * {@inheritDoc}
     * Indicates whether this product is considered expired as of the given date.
     * For this product type, the concept of expiration does not typically exist.
     * Therefore, this method always returns {@code false}.
     *
     * @param today The current date and time.
     * @return {@code false},  wine in this context does not expire.
     */
    @Override
    public boolean isExpired(Instant today) {
        return false;
    }
}
