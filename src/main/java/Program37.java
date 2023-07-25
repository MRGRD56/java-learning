import dev.b37.mgutils.common.Reference;

public class Program37 {
    public static void main(String[] args) {
        Reference<? extends Fruit> fruitRef = new Reference<Apple>(new Apple());
        Fruit fruit = fruitRef.get();
        ((Reference) fruitRef).set(new Fruit());

        Fruit fruit2 = fruitRef.get();

        System.out.println(fruit);
        System.out.println(fruit2);
    }

    private static class Fruit { }
    private static class Apple extends Fruit { }
    private static class Jonathan extends Apple { }
    private static class Orange extends Fruit { }
}
