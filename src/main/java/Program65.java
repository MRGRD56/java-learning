import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Program65 {
    private static final ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
    private static final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

    public static void main(String[] args) throws Exception {
        System.out.println("arrayBlockingQueue.put");
        arrayBlockingQueue.put(10);
        System.out.println("arrayBlockingQueue.take");
        arrayBlockingQueue.take();

        System.out.println("synchronousQueue.put");
        synchronousQueue.put(20);
        System.out.println("synchronousQueue.take");
        synchronousQueue.take();
    }
}
