package de.superdupermarkt.worker;


import de.superdupermarkt.source.CSVProductReader;
import de.superdupermarkt.products.Product;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {

    private static final String LINE_SEPARATOR = "-------------------------------------------------------";

    public static void main(String[] args){

        Instant startTime = Instant.parse("2025-05-08T19:00:31.907111500Z");

        // read in product types and product list
        CSVProductReader.readProductTypesCSV();
        List<Product> productList = CSVProductReader.readProductDataCSV(CSVProductReader.FILE_PATH_PRODUCT_DATA);
        if (productList == null || productList.isEmpty()) {
            System.out.println("Fehler beim einlesen oder ProductData.csv ist leer !");
        }

        // start
        System.out.println("Startwerte des Programmes");
        System.out.println(LINE_SEPARATOR);
            productList.forEach(f -> System.out.println(f.getProductDetails()));

        for (int anzahlTage = 0; anzahlTage < 150; anzahlTage++) {

            System.out.println(" ");
            System.out.println("Tag: " + (anzahlTage + 1) + " Datum: " + startTime);

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
            System.out.println(LINE_SEPARATOR);
        }
    }
}