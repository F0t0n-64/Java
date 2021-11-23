package pjv.alsa.cv01.util;
import pjv.alsa.cv01.entity.Product;
import java.util.List;

public class ProductUtils {

    public static void printProducts(List<Product> products) {
        printProducts("products", products);
    }

    public static void printProducts(String title, List<Product> products) {
        System.out.println("--------------------");
        System.out.println(title + ":");
        System.out.println("--------------------");
        for(Product product : products)
            System.out.println(product.toString());
        System.out.println("--------------------");
    }
}
