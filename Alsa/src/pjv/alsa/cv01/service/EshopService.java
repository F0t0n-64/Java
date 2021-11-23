package pjv.alsa.cv01.service;
import pjv.alsa.cv01.entity.Product;

import java.util.Comparator;
import java.util.List;

public interface EshopService {

    List<Product> getProducts();
    List<Product> getProducts(Comparator<Product> productComparator);

    void addProductToStorage(Product product);
    void addProductToStorage(Product... product);
    boolean sellProduct  (int id);
    boolean returnProduct(int id);
}
