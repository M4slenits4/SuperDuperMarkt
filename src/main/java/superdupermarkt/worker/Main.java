package superdupermarkt.worker;

import superdupermarkt.product.types.Product;
import superdupermarkt.source.data.CSVProductReader;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args){

        // Need to switch the path to your own
        String fileNameProductData = "src\\main\\java\\superdupermarkt\\source\\data\\ProductData.csv";

        Instant startTime = Instant.parse("2025-05-08T19:00:31.907111500Z");

        CSVProductReader.readProductTypesCSV();
        List<Product> productList = CSVProductReader.readProductDataCSV(fileNameProductData);
        System.out.println("Startwerte des Programmes");
        System.out.println("-------------------------------------------------------");
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
            System.out.println("-------------------------------------------------------");
        }
    }
}