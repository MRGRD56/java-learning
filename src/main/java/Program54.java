import java.util.ArrayDeque;
import java.util.Deque;

public class Program54 {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>(1);

        deque.push(0);
        deque.offer(-1);
        deque.push(1);
        deque.push(2);
        deque.offer(-2);
        deque.push(3);
        deque.push(4);
        deque.push(5);

        Object __null = null;
    }
}
