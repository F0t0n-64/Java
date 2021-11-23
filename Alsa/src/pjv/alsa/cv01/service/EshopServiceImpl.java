package pjv.alsa.cv01.service;
import pjv.alsa.cv01.entity.Product;
import pjv.alsa.cv01.persistence.Database;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EshopServiceImpl implements EshopService {

    private final Database database;

    public EshopServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Product> getProducts() {
        return database.getProducts();
    }

    @Override
    public List<Product> getProducts(Comparator<Product> productComparator) {
        List<Product> products = database.getProducts();
        products.sort(productComparator);
        return products;
    }

    @Override
    public void addProductToStorage(Product product) {
        database.saveProduct(product);
    }

    @Override
    public void addProductToStorage(Product... products) {
        Stream.of(products).forEach(this::addProductToStorage);
    }

    @Override
    public synchronized boolean sellProduct(int id) {
        Optional<Product> optionalProduct = database.getProductById(id);
        if(optionalProduct.isEmpty())
            return false;
        Product product = optionalProduct.get();
        while (product.getCount() == 0) {
            if(! product.hasSpecialGuarantee())
                return false;
            System.out.println("Waiting for product id " + id);
            try {
                wait();
            } catch (InterruptedException e) {
                return false;
            }
            product = database.getProductById(id).get();
        }
        Product updatedProduct = product.withDecreasedCount();
        database.saveProduct(updatedProduct);
        return true;
    }

    @Override
    public synchronized boolean returnProduct(int id) {
        Optional<Product> optionalProduct = database.getProductById(id);
        if(optionalProduct.isEmpty())
            return false;
        Product product = optionalProduct.get();
        if(!product.hasSpecialGuarantee())
            return false;
        Product updatedProduct = product.withIncreasedCount();
        database.saveProduct(updatedProduct);
        notifyAll();
        return true;
    }

}
