package superdupermarkt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

/**
 * Define the abilities of the product
 */
@AllArgsConstructor
@Getter
@Setter
public abstract class Product {
    // Bezeichnung
    private String label;
    // Qualität
    private int quality;
    // Verfallsdatum
    private Instant expireDate;
    // Preis
    private double price;

    /**
     * Calculate and round the daily price of a product.
     *
     * @param basicPrice    basic price of the product.
     * @param quality       the quality of the product
     */
    public void calculatePrice(double basicPrice, int quality) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(basicPrice + (0.10 * quality)));
        BigDecimal roundedPrice = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        setPrice(roundedPrice.doubleValue());
    }

    /**
     * Get out all needed product details of the product.
     */
    public void getProductDetails() {
        System.out.println("Bezeichnung: " + getLabel() + ", Preis: " + getPrice() + ", Qualität: " +  getQuality());
    }

    /**
     * Checks the quality level of the product
     *
     * @return {@code true} if the quality level fits, otherwise {@code false}
     */
    public abstract boolean checkQualityLevel();

    /**
     * Update the quality level for the product. Depends on the product.
     */
    public abstract void updateQualityLevel(Instant today);

    /**
     * Update daily price if it is changing for this product
     */
    public abstract void updateDailyPrice();

    /**
     *  Check for the expiry date and expire conditions of the product
     */
    public abstract void handleExpiration(Instant today);
}
