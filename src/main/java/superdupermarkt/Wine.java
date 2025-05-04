package superdupermarkt;

import java.util.Date;

public class Wine extends Product{
    private static final double BASIC_PRICE = 10.00;

    public Wine(double basicPrice, String label, int quality, Date expireDate, double price) {
        super(basicPrice, label, quality, expireDate, price);
    }

    @Override
    public boolean checkQualityLevel() {
        return getQuality() > 0;
    }

    @Override
    public void setDailyQualityChanges() {
        //ToDo add code for growing in Quality
    }

    @Override
    public void setDailyPrice() {
        System.out.println("Wein hat keinen ändernden Tagespreis !");
    }

    @Override
    public void checkExpireDate() {
        System.out.println("Wein hat keinen Verfallsdatum !");
    }
}
