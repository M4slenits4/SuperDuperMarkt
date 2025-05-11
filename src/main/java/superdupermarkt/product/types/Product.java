package superdupermarkt.product.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

/**
 * The abstract class {@code Product} defines the basic properties and capabilities
 * of every product in the supermarket. It serves as a base class for more specific product types. *
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
     * @param quality       the quality of the product.
     */
    public void calculatePrice(double basicPrice, int quality) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(basicPrice + (0.10 * quality)));
        BigDecimal roundedPrice = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        setPrice(roundedPrice.doubleValue());
    }

    /**
     * Get out all needed product details of the product.
     */
    public String getProductDetails() {
        return "Bezeichnung: " + getLabel() + ", Preis: " + getPrice() + ", Qualität: " + getQuality();
    }

    /**
     * Checks the quality level of the product
     *
     * @return {@code true} if the quality level fits, otherwise {@code false}
     */
    public abstract boolean checkQualityLevel();

    /**
     * Update the quality level for the product. Depends on the product.
     *
     * @param toDay The current date and time.
     */
    public abstract void updateQualityLevel(Instant toDay);

    /**
     * Update the price if there are changes  for the product.
     */
    public abstract void updatePrice();

    /**
     * Checks for the expiry date of the product
     *
     * @param toDay The current date and time.
     * @return {@code true} if the product is expired, otherwise {@code false}
     */
    public abstract boolean isExpired(Instant toDay);
}
