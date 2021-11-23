package pjv.alsa.cv01.thread;

import pjv.alsa.cv01.service.EshopService;

public class BuyingCustomer extends Customer {

    public BuyingCustomer(String name, EshopService eshopService) {
        super(name, eshopService);
    }

    @Override
    protected void goToEshop() {
        int productId = generateRandomProductId();
        if(eshopService.sellProduct(productId)) {
            successCounter++;
            System.out.println("[" + name + "] Bought product id: " + productId);
        } else
            System.out.println("[" + name + "] Failed to buy product id " + productId);
        counter++;
    }

}
