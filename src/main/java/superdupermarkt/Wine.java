package superdupermarkt;

import java.time.Duration;
import java.time.Instant;

public class Wine extends Product{
    private static final double BASIC_PRICE = 10.00;
    private static final int MAXIMUM_QUALITY_LEVEL = 50;
    private static final int QUALITY_INCREMENT = 1;
    private static final Duration QUALITY_INCREASE_INTERVAL = Duration.ofDays(10);

    private Instant dayOfIncreasingQuality;

    public Wine(String label, int quality, Instant expireDate) {
        super(label, quality, expireDate, BASIC_PRICE);
        dayOfIncreasingQuality = expireDate.plus(QUALITY_INCREASE_INTERVAL);
    }

    @Override
    public boolean checkQualityLevel() {
        return getQuality() > 0;
    }

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

    @Override
    public void updateDailyPrice() {
        setPrice(BASIC_PRICE);
    }

    @Override
    public void handleExpiration(Instant today) {

    }
}
