package superdupermarkt.source.data;

import com.opencsv.CSVReaderHeaderAware;
import superdupermarkt.product.types.Product;

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

    /** A static list to hold all the products read from the CSV.*/
    private static final List<Product> PRODUCT_LIST = new ArrayList<>();

    /** The file path to the CSV file containing the definitions of product types. */
    private static final String FILE_NAME_PRODUCT_TYPES = "src\\main\\java\\superdupermarkt\\source\\data\\ProductTypes.csv";

    /** A static map that associates the product type from the CSV with the correct {@link Product}.*/
    private static final Map<String, Class<? extends Product>> PRODUCT_TYPES = new HashMap<>();

    /**
     * Reads product type information from a CSV file.
     * Each row in the CSV two columns: "Typ" and "Klassenname" representing the fully qualified name.
     * For each row, this method load the Java class specified in the"Klassenname" column.
     * If the class is successfully loaded, its {@code Class} object is stored in the {@link #PRODUCT_TYPES} map.
     */
    public static void readProductTypesCSV() {
        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(FILE_NAME_PRODUCT_TYPES))) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                PRODUCT_TYPES.put(line.get("Typ"), (Class<? extends Product>) Class.forName(line.get("Klassenname")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                        PRODUCT_LIST.add(product);
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
        return PRODUCT_LIST;
    }
}