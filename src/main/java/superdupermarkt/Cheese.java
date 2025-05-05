package superdupermarkt;

import java.time.Instant;

public class Cheese extends Product {
    private static final double BASIC_PRICE = 6.00;
    private static final int MINIMUM_QUALITY_LEVEL = 30;
    private static final int DAILY_QUALITY_DECREMENT = 1;

    public Cheese(String label, int quality, Instant expireDate) {
        super(label, quality, expireDate, BASIC_PRICE);
        updateDailyPrice();
    }

    @Override
    public boolean checkQualityLevel() {
        return getQuality() >= MINIMUM_QUALITY_LEVEL;
    }

    @Override
    public void updateQualityLevel(Instant today) {
            setQuality(getQuality() - DAILY_QUALITY_DECREMENT);
    }

    @Override
    public void updateDailyPrice() {
        calculatePrice(BASIC_PRICE, getQuality());
    }

    @Override
    public void handleExpiration(Instant today) {
        //ToDo adding more handling
        if (getExpireDate().isAfter(today)) {
            System.out.println("Abgelaufen !");
            setQuality(0);
        }
    }
}
