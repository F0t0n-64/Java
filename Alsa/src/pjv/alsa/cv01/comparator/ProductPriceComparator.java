package pjv.alsa.cv01.comparator;

import pjv.alsa.cv01.entity.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    private final SortOrder sortOrder;

    public ProductPriceComparator() {
        this.sortOrder = SortOrder.ASCENDING;
    }

    public ProductPriceComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Product o1, Product o2) {
        int result = Double.compare(o1.getPrice(), o2.getPrice());
        return sortOrder == SortOrder.ASCENDING ? result : - result;
    }
}
