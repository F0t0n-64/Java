package pjv.alsa.cv01.thread;

import pjv.alsa.cv01.entity.Product;
import pjv.alsa.cv01.service.EshopService;
import pjv.alsa.cv01.util.Sleeper;
import java.util.Random;

public abstract class Customer {
    protected final String name;
    protected final EshopService eshopService;
    private final Random random = new Random();
    protected Thread thread;
    protected boolean shouldRun = true;
    protected int counter = 0;
    protected int successCounter = 0;

    public Customer(String name, EshopService eshopService) {
        this.name = name;
        this.eshopService = eshopService;
    }

    public void start() {
        thread = new Thread(() -> {
            Sleeper sleeper = new Sleeper(name);
            while(shouldRun) {
                sleeper.randomSleep();
                goToEshop();
            }
        });
        thread.start();
    }

    public void stop() {
        shouldRun = false;
        thread.interrupt();
    }

    protected abstract void goToEshop();

    protected int generateRandomProductId() {
        Integer[] productId = eshopService.getProducts().stream().map(Product::getId).toArray(Integer[]::new);
        return productId[random.nextInt(productId.length)];
    }

    public void printStatistics() {
        System.out.println("[" + name + "]" + "Went to e-shop " + counter + " times, " + successCounter + " successfully");
    }

}
