import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<T>();
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        // Заполнение пула объектами :
        for (int i = 0; i < size; ++i) {
            try {
                // Предполагается наличие конструктора по умолчанию:
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; ++i) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null; // Семафор предотвращает переход в зту точку
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false; // Отсутствует в списке
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false; // He был освобожден
    }

    public static class Fat {
        private volatile double d; // Предотвращает оптимизацию
        private static int counter = 0;
        private final int id = counter++;

        public Fat() {
            // Затратная, прервываемая операция:
            for (int i = 1; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;
            }
        }

        public void operation() {
            System.out.println(this);
        }

        public String toString() {
            return "Fat id: " + id;
        }
    }

    // Задача для получения ресурса из пула:
    private static class CheckoutTask<T> implements Runnable {
        private static int counter = 0;
        private final int id = counter++;
        private Pool<T> pool;

        public CheckoutTask(Pool<T> pool) {
            this.pool = pool;
        }

        public void run() {
            try {
                T item = pool.checkOut();
                System.out.println(this + "checked out " + item);
                TimeUnit.SECONDS.sleep(1);
                System.out.println(this + "checking in " + item);
                pool.checkIn(item);
            } catch (InterruptedException e) {
                // Приемлемый способ завершения
            }
        }

        public String toString() {
            return "CheckoutTask " + id + " ";
        }
    }

    public static class SemaphoreDemo {
        final static int SIZE = 25;

        public static void main(String[] args) throws Exception {
            final Pool<Fat> pool =
                    new Pool<Fat>(Fat.class, SIZE);
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < SIZE; i++) {
                exec.execute(new CheckoutTask<Fat>(pool));
            }
            System.out.println("All CheckoutTasks created");
            List<Fat> list = new ArrayList<Fat>();
            for (int i = 0; i < SIZE; i++) {
                Fat f = pool.checkOut();
                System.out.println(i + ": main() thread checked out ");
                f.operation();
                list.add(f);
            }
            Future<?> blocked = exec.submit(new Runnable() {
                public void run() {
                    try {
                        // Семафор предотвращает лишний вызов checkout.
                        // поэтому следующий вызов блокируется:
                        pool.checkOut();
                    } catch (InterruptedException e) {
                        System.out.println("checkOut() Interrupted");
                    }
                }
            });
            TimeUnit.SECONDS.sleep(2);
            blocked.cancel(true); // Выход из заблокированного вызова
            System.out.println("Checking in objects in " + list);
            for (Fat f : list) {
                pool.checkIn(f);
            }
            for (Fat f : list) {
                pool.checkIn(f); // Второй вызов checkIn игнорируется
            }
            exec.shutdown();
        }
    }
}