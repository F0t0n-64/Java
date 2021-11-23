package pjv.alsa.cv01.persistence;
import pjv.alsa.cv01.entity.Product;

import java.util.*;

public class InMemoryDatabase implements Database {

    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> getProductById(int id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    @Override
    public void saveProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product.getId());
    }
}

