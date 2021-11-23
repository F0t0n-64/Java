package pjv.alsa.cv01;

import pjv.alsa.cv01.entity.Product;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import static pjv.alsa.cv01.SampleData.*;
import static pjv.alsa.cv01.util.ProductUtils.printProducts;

public class StreamAPIExample {
    public static void main(String[] args) {
        functions();
        streams();
    }

    private static final List<Product> products = Arrays.asList(lenovoE500, hpBusinnesPlus, samsungMediaPlus);

    private static void functions() {
        System.out.println("---------functions-------");
        Function<Integer, Integer> add5 = a -> a + 5;
        System.out.println(add5.apply(3));
        Function<Integer, Integer> x2 = a -> a * 2;
        System.out.println(x2.apply(5));
        Function<Integer, Integer> composed = x2.compose(add5);
        System.out.println(composed.apply(3));
        Function<Integer, Function<Integer, Integer>> add = a -> b -> a + b;
        System.out.println(add.apply(2).apply(3));
    }

    private static void streams() {
        System.out.println("---------streams---------");
        streamForEach();
        streamMap();
        streamToMap();
        streamFilter();
        streamSort();
        streamReduce();
        stringJoiner();
    }

    private static void streamForEach() {
        System.out.println("forEach");
        products.forEach(System.out::println);
    }

    private static void streamMap() {
        printProducts("StreamMap", products.stream()
                .map(Product::withIncreasedCount)
                .collect(Collectors.toList()));
    }

    private static void streamToMap() {
        System.out.println("streamToMap");
        System.out.println(products.stream()
                .collect(Collectors.toMap(Product::getName, product -> product)));

    }

    private static void streamFilter() {
        printProducts("streamFilter" ,products.stream()
                .filter(product -> product.getPrice() >= 10000)
                .collect(Collectors.toList()));
    }

    private static void streamSort() {
        printProducts("streamSort" ,products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList()));
    }

    private static void streamReduce() {
        System.out.println("streamReduce");
        System.out.println(products.stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum));
        System.out.println();
    }

    private static void stringJoiner() {
        System.out.println("stringJoiner");
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        products.stream().map(Product::getName).forEach(stringJoiner::add);

        System.out.println(stringJoiner);
    }
}
