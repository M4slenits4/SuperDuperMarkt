package superdupermarkt.source.data;

import com.opencsv.CSVReaderHeaderAware;
import superdupermarkt.product.types.Cheese;
import superdupermarkt.product.types.Product;
import superdupermarkt.product.types.Wine;

import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to read product data from a CSV file.
 */
public class CSVProductReader {

    /**
     * A static list to hold all the products read from the CSV.
     */
    private static final List<Product> productList = new ArrayList<>();

    /**
     * A static map that associates the product type from the CSV with the correct {@link Product}.
     */
    private static final Map<String, Class<? extends Product>> PRODUCT_TYPES = new HashMap<>();

    static {
        PRODUCT_TYPES.put("Cheese", Cheese.class);
        PRODUCT_TYPES.put("Wine", Wine.class);
    }

    /**
     * Reads product data from the specified CSV file.
     * It parses each and creates the corresponding {@link Product} object.
     * The product type value is in the "ProductTyp" column.
     *
     * @param path The path to the CSV file to read.
     * @return A static {@link List} containing all the {@link Product} objects read from the file.
     */
    public static List<Product> readProductDataCSV(String path) {
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(path))) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                String productTyp = line.get("ProductTyp");
                Class<? extends Product> productClass = PRODUCT_TYPES.get(productTyp);

                if (productClass != null) {
                    if (line.get("Bezeichnung") == null || line.get("Qualitaet") == null || line.get("Verfallsdatum") == null) {
                        System.err.println("Mindestens eine erforderliche Spalte ist null in einer Zeile für ProductTyp: " + productTyp);
                        continue;
                    }
                    try {
                        Product product = productClass.getDeclaredConstructor(String.class, int.class, Instant.class)
                                .newInstance(line.get("Bezeichnung"), Integer.parseInt(line.get("Qualitaet")), Instant.parse(line.get("Verfallsdatum")));
                        productList.add(product);
                    } catch (Exception e) {
                        System.err.println("Fehler beim Erstellen von " + productTyp + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("Unbekannter Produkt Typ: " + productTyp);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
}