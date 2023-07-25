import dev.b37.mgutils.common.Reference;

import java.util.ArrayList;
import java.util.List;

public class Program37_2 {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        List<Jonathan> jonathans = new ArrayList<Jonathan>();
        List<? extends Fruit> fruits = new ArrayList<Orange>();
        List<Fruit> fruitsActual = new ArrayList<Fruit>();

        writeTo(fruitsActual);
    }

    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());

//        Apple apple = apples.get(0);
// apples.add(new Fruit()); // Error
    }

    private static class Fruit { }

    private static class Apple extends Fruit { }

    private static class Jonathan extends Apple { }

    private static class Orange extends Fruit { }
}
