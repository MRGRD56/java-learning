import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Random;

public class Program22 {
    public static void main(String[] args) {
        for (Integer number : new Iterable<Integer>() {
            @NotNull
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<>() {
                    private final Random random = new Random();

                    @Override
                    public boolean hasNext() {
                        return true;
                    }

                    @Override
                    public Integer next() {
                        return random.nextInt();
                    }
                };
            }
        }) {
            System.out.print(number + " ");
        }
    }
}
