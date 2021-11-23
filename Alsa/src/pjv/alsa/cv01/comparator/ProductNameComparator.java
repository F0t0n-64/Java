package pjv.alsa.cv01.comparator;
import pjv.alsa.cv01.entity.Product;
import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {

    private final SortOrder sortOrder;

    public ProductNameComparator() {
        this.sortOrder = SortOrder.ASCENDING;
    }

    public ProductNameComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Product o1, Product o2) {
        int result = o1.getName().compareTo(o2.getName());
        return sortOrder == SortOrder.ASCENDING ? result : -result;
    }
}
