package pjv.alsa.cv01.util;

import java.util.Random;

public class Sleeper {

    private final Random random = new Random();
    private final String name;

    public Sleeper(String name) {
        this.name = name;
    }

    public void randomSleep() {
        int time = (int) Math.abs(random.nextGaussian() * 500 + 1000);
        System.out.println("[" + name + "] Sleeping for " + time + " ms...");
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }

    }
}
