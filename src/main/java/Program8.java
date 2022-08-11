import com.mrgrd56.common.StackImpl;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Program8 {
    public static void main(String[] args) {
        var chara = "a".charAt(0);
        var bytes = "hello world".getBytes(StandardCharsets.UTF_8);
        var stringFromBytes = new String(bytes, StandardCharsets.UTF_8);

        LinkedList<String> linkedList = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new ArrayDeque<>();
        Deque<String> deque = new ArrayDeque<>();

        stack.push("hello");
        stack.push("world");
        stack.push("damn");
        stack.pop();

        queue.offer("damn");
        queue.offer("hello");
        queue.offer("world");
        queue.poll();

        deque.offer("hello");
        deque.addFirst("damn");
        deque.push("world");
        deque.push("damn");
        deque.poll();
        deque.pop();

        var myStack = new StackImpl<String>();
        myStack.push("hello");
        myStack.push("world");
        myStack.push("damn");
        myStack.pop();
    }
}
