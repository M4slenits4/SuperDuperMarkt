package worker;

import superdupermarkt.Cheese;
import superdupermarkt.Product;
import superdupermarkt.Wine;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Cheese("Cheddar", 90, Instant.now()));
        productList.add(new Cheese("Tilsiter", 240, Instant.now()));
        productList.add(new Cheese("Emmentaler", 190, Instant.now()));

        productList.add(new Wine("Gutedel", 30, Instant.now()));
        productList.add(new Wine("Chardonney", 30, Instant.now()));
        productList.add(new Wine("Spätburgunder", 30, Instant.now()));

        Instant startTime = Instant.now();

        System.out.println("Startwerte des Programmes");
        productList.forEach(Product::getProductDetails);

        for (int anzahlTage = 0; anzahlTage < 150; anzahlTage++) {

            System.out.println("Tag: " + (anzahlTage + 1 ) + " Datum: " + startTime);
            System.out.println(" ");
            for (Product product : productList) {
                product.updateDailyPrice();
                product.updateQualityLevel(startTime);
                product.handleExpiration(startTime);
                product.getProductDetails();
                if (!product.checkQualityLevel()) {
                    System.out.println("Produkt " + product.getLabel() + " muss entsorgt werden !");
                }
            }
            startTime = startTime.plus(Duration.ofDays(1));
            System.out.println("-------------------------------------------------------");
        }
    }
}