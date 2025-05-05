package worker;

import superdupermarkt.Cheese;
import superdupermarkt.Product;
import superdupermarkt.Wine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Cheese("Cheddar", 30, new Date()));
        productList.add(new Cheese("Tilsiter", 30, new Date()));
        productList.add(new Cheese("Emmentaler", 30, new Date()));

        productList.add(new Wine("Gutedel", 30, new Date()));
        productList.add(new Wine("Chardonney", 30, new Date()));
        productList.add(new Wine("Spätburgunder", 30, new Date()));

        for (int tage = 0; tage < 150; tage++) {

            System.out.println(tage);
            productList.forEach(e -> {
                System.out.println(e.getLabel());
            });
        }
    }
}