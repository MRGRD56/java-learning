import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Program36 {
    public static void main(String[] args) {
        Hello hello = new Hello<>(new Hello(null));
    }

    private static class Hello<T extends Comparable<Hello<T>> & Cloneable & Serializable> implements Comparable<Hello<T>>, Cloneable, Serializable {
        public Hello(T item) {
            Comparable<Hello<T>> serializable = item;
        }

        @Override
        public int compareTo(@NotNull Program36.Hello<T> o) {
            return 0;
        }
    }
}
