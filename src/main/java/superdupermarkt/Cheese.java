package superdupermarkt;

import java.util.Date;

public class Cheese extends Product {
    private static final double BASIC_PRICE = 6.00;

    public Cheese(double basicPrice, String label, int quality, Date expireDate, double price) {
        super(basicPrice, label, quality, expireDate, price);
    }


    @Override
    public boolean checkQualityLevel() {
        return getQuality() >= 30;
    }

    @Override
    public void setDailyQualityChanges() {
        setQuality(getQuality() - 1);
    }

    @Override
    public void setDailyPrice() {
        calculateDailyPrice(getBasicPrice(), getQuality());
    }

    @Override
    public void checkExpireDate() {
        // ToDo adjust for the expire Conditions
    }
}
