import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicProgram {
    public static void main(String[] args) {
        AtomicBoolean bool = new AtomicBoolean(false);

        if (bool.compareAndSet(false, true)) {
            System.out.println("now it's " + bool);
        }

        if (bool.compareAndSet(false, true)) {
            System.out.println("now it's " + bool);
        }
    }
}
