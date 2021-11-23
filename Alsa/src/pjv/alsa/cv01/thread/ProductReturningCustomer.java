package pjv.alsa.cv01.thread;

import pjv.alsa.cv01.service.EshopService;

public class ProductReturningCustomer extends Customer {

    public ProductReturningCustomer(String name, EshopService eshopService) {
        super(name, eshopService);
    }

    @Override
    protected void goToEshop() {
        int productId = generateRandomProductId();
        if(eshopService.returnProduct(productId)) {
            successCounter++;
            System.out.println("Returned product id: " + productId);
        } else
            System.out.println("Failed to return product id " + productId);
        counter++;
    }

}
