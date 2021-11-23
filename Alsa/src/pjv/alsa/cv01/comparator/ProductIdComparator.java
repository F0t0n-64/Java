package pjv.alsa.cv01.comparator;

import pjv.alsa.cv01.entity.Product;

import java.util.Comparator;

public class ProductIdComparator implements Comparator<Product> {

    private final SortOrder sortOrder;

    public ProductIdComparator() {
        this.sortOrder = SortOrder.ASCENDING;
    }

    public ProductIdComparator(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Product o1, Product o2) {
        int result = Integer.compare(o1.getId(), o2.getId());
        return sortOrder == SortOrder.ASCENDING ? result : - result;
    }
}
