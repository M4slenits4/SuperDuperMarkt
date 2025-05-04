package superdupermarkt;

import com.sun.source.tree.LabeledStatementTree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Define the abilities of the product
 */
@AllArgsConstructor
@Getter
@Setter
public abstract class Product {
    // finaler Grundpreis
    private final double basicPrice;
    // Bezeichnung
    private String label;
    // Qualität
    private int quality;
    // Verfallsdatum
    private Date expireDate;
    // Preis
    private double price;

    /**
     * Calculate the daily price of a product.
     *
     * @param basicPrice    basic price of the product.
     * @param quality       the quality of the product
     */
    public void calculateDailyPrice(double basicPrice, int quality) {
        setPrice(basicPrice + (0.10 * quality));
    }

    /**
     * Checks the quality level of the product
     *
     * @return {@code true} if the quality level fits, otherwise {@code false}
     */
    public abstract boolean checkQualityLevel();

    /**
     * Daily loss of the product quality
     */
    public abstract void setDailyQualityChanges();

    /**
     * Set a daily price if it is changing for this product
     */
    public abstract void setDailyPrice();

    /**
     *  Check for the expiry date and expire conditions of the product
     */
    public abstract void checkExpireDate();
}
