package pjv.alsa.cv01.persistence;

import pjv.alsa.cv01.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDataBase implements Database {

    public static final String DATABASE_FILE = "alsa.db";

    @Override
    public List<Product> getProducts() {
        return loadProducts();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return loadProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @Override
    public void saveProduct(Product product) {
        List<Product> products = loadProducts();
        products.removeIf(productInDb -> productInDb.getId() == product.getId());
        products.add(product);
        persistProducts(products);
    }

    @Override
    public void removeProduct(Product product) {
        List<Product> products = loadProducts();
        products.removeIf(productInDb -> productInDb.getId() == product.getId());
        persistProducts(products);
    }

    private List<Product> loadProducts() {
        try (FileInputStream in = new FileInputStream(DATABASE_FILE);
             ObjectInputStream oIn = new ObjectInputStream(in)
        ) {
            List<Product> products = (List<Product>) oIn.readObject();
            return products != null ? products : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void persistProducts(List<Product> products) {
        try (FileOutputStream out = new FileOutputStream(DATABASE_FILE);
             ObjectOutputStream oOut = new ObjectOutputStream(out)
        ) {
            oOut.writeObject(products);
            oOut.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void uglyPersistProducts(List<Product> products) {
        FileOutputStream out = null;
        ObjectOutputStream oOut = null;
        try {
            out = new FileOutputStream(DATABASE_FILE);
            oOut = new ObjectOutputStream(out);
            oOut.writeObject(products);
            oOut.flush();
            oOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(out != null) {
                try {
                    out.close();
                    if(oOut != null)
                        oOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
