package pjv.alsa.cv01.persistence;
import pjv.alsa.cv01.entity.Product;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Database {

    List<Product> getProducts();
    Optional<Product> getProductById(int id);
    void saveProduct  (Product product);
    void removeProduct(Product product);

    public static final Database DUMMY_DATABASE = new Database() {
        @Override
        public List<Product> getProducts() {
            return Collections.emptyList();
        }

        @Override
        public Optional<Product> getProductById(int id) {
            return Optional.empty();
        }

        @Override
        public void saveProduct(Product product) {

        }

        @Override
        public void removeProduct(Product product) {

        }
    };
}
