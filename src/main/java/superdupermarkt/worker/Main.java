package superdupermarkt.worker;

import superdupermarkt.product.types.Cheese;
import superdupermarkt.product.types.Product;
import superdupermarkt.product.types.Wine;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Cheese("Cheddar", 28, Instant.now().plus(Duration.ofDays(100))));
        productList.add(new Cheese("Tilsiter", 240, Instant.now().plus(Duration.ofDays(80))));
        productList.add(new Cheese("Emmentaler", 190, Instant.now().plus(Duration.ofDays(40))));

        productList.add(new Wine("Gutedel", -10, Instant.now().plus(Duration.ofDays(50))));
        productList.add(new Wine("Chardonney", 30, Instant.now().plus(Duration.ofDays(10))));
        productList.add(new Wine("Spätburgunder", 30, Instant.now().plus(Duration.ofDays(100))));

        Instant startTime = Instant.now();

        System.out.println("Startwerte des Programmes");
        productList.forEach(Product::getProductDetails);

        for (int anzahlTage = 0; anzahlTage <= 150; anzahlTage++) {

            System.out.println("Tag: " + (anzahlTage) + " Datum: " + startTime);
            System.out.println(" ");

            StringBuilder productInfos;
            for (Product product : productList) {
                product.updatePrice();
                product.updateQualityLevel(startTime);
                productInfos = new StringBuilder(product.getProductDetails());
                // Check the quality level and expiration date of the product.
                if (!product.checkQualityLevel() || product.isExpired(startTime)) {
                    productInfos.append(" ! Muss ausgeräumt werden !");
                }
                System.out.println(productInfos);
            }
            startTime = startTime.plus(Duration.ofDays(1));
            System.out.println("-------------------------------------------------------");
        }
    }
}