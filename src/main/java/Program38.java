import java.util.List;

class Fruit { }

class Apple extends Fruit { }

class Jonathan extends Apple { }

class Orange extends Fruit { }

//: generics/GenericHolder.java
class GenericHolder<T> {
    private T obj;

    public void set(T obj) { this.obj = obj; }

    public T get() { return obj; }

    public static void main(String[] args) {
        GenericHolder<String> holder =
                new GenericHolder<String>();
        holder.set("Item");
        String s = holder.get();
    }
}

//: generics/Holder.java
class Holder<T> {
    private T value;

    public Holder() { }

    public Holder(T val) { value = val; }

    public void set(T val) { value = val; }

    public T get() { return value; }

    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<Apple>(new Apple());
        Apple d = apple.get();
        apple.set(d);
// Holder<Fruit> Fruit = apple; // Повышение невозможно
        Holder<? extends Fruit> fruit = apple; // OK
        Fruit p = fruit.get();
        d = (Apple) fruit.get(); // Возвращает 'Object'
        try {
            Orange c = (Orange) fruit.get(); // Предупреждения нет
        } catch (Exception e) {
            System.out.println(e);
        }
// fruit.set(new apple()); // Вызов set() невозможен
// fruit.set(new Fruit()); // Вызов set() невозможен
        System.out.println(fruit.equals(d)); // OK
    }
}

class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
// apples.add(new Fruit()); // Error
    }
}