package pjv.alsa.cv01;
import pjv.alsa.cv01.persistence.Database;
import pjv.alsa.cv01.persistence.InMemoryDatabase;
import pjv.alsa.cv01.service.EshopService;
import pjv.alsa.cv01.service.EshopServiceImpl;
import pjv.alsa.cv01.thread.BuyingCustomer;
import pjv.alsa.cv01.thread.Customer;
import pjv.alsa.cv01.thread.ProductReturningCustomer;


import java.util.Arrays;
import java.util.List;

import static pjv.alsa.cv01.SampleData.*;
import static pjv.alsa.cv01.util.ProductUtils.printProducts;

public class Application {

    private static EshopService createEshop() {
        Database database = new InMemoryDatabase();
//        Database database = new FileDataBase();
        return new EshopServiceImpl(database);
    }

    public static void main(String[] args) {
        EshopService eshopService = createEshop();

        eshopService.addProductToStorage(lenovoE500, hpBusinnesPlus, samsungMediaPlus);
        printProducts(eshopService.getProducts());

        List<Customer> customers = Arrays.asList(
                new BuyingCustomer("B1", eshopService),
                new ProductReturningCustomer("R1", eshopService),
                new BuyingCustomer("B2",eshopService),
                new ProductReturningCustomer("R2", eshopService),
                new BuyingCustomer("B3",eshopService),
                new ProductReturningCustomer("R3", eshopService)
        );

        customers.forEach(Customer::start);
        sleep(10000);
        customers.forEach(Customer::stop);
        sleep(3000);

        printProducts(eshopService.getProducts());
        customers.forEach(Customer::printStatistics);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }

    }
}
