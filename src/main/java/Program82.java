import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Program82 {
    public static class A {
        public static void main(String[] args) throws InterruptedException {
            Queue<Integer> initialQueue = new ArrayDeque<>();
            initialQueue.add(1);
            initialQueue.add(2);

            MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(initialQueue);
            System.out.println(queue.take());
            queue.add(3);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        }
    }

    public static class B {
        public static void main(String[] args) throws InterruptedException {
            Queue<Integer> initialQueue = new ArrayDeque<>();
            initialQueue.add(1);
            initialQueue.add(2);

            MyLimitedBlockingQueue<Integer> queue = new MyLimitedBlockingQueue<>(initialQueue, 5);
            System.out.println(queue.take());
            queue.add(3);
            System.out.println("ADDED 3");
            System.out.println(queue.take());
            queue.add(4);
            System.out.println("ADDED 4");
            queue.add(5);
            System.out.println("ADDED 5");
            queue.add(6);
            System.out.println("ADDED 6");
            queue.add(7);
            System.out.println("ADDED 7");
            queue.add(8);
            System.out.println("ADDED 8");
        }
    }

    private static class MyBlockingQueue<T> {
        private final Semaphore semaphore;
        private final Queue<T> queue;

        public MyBlockingQueue(Queue<T> queue) {
            this.queue = queue;
            this.semaphore = new Semaphore(queue.size());
        }

        public T take() throws InterruptedException {
            semaphore.acquire();
            synchronized (queue) {
                return queue.poll();
            }
        }

        public boolean add(T item) {
            semaphore.release();
            synchronized (queue) {
                return queue.add(item);
            }
        }
    }

    private static class MyLimitedBlockingQueue<T> {
        private final Queue<T> queue;
        private final int capacity;
        private final Semaphore itemsSemaphore;
        private final Semaphore spaceSemaphore;

        public MyLimitedBlockingQueue(Queue<T> queue, int capacity) {
            this.queue = queue;
            this.capacity = capacity;
            this.itemsSemaphore = new Semaphore(queue.size());
            this.spaceSemaphore = new Semaphore(capacity - queue.size());
        }

        public T take() throws InterruptedException {
            itemsSemaphore.acquire();
            synchronized (queue) {
                spaceSemaphore.release();
                return queue.poll();
            }
        }

        public boolean add(T item) throws InterruptedException {
            spaceSemaphore.acquire();
            synchronized (queue) {
                itemsSemaphore.release();
                return queue.add(item);
            }
        }
    }

    // GPT:

    public static class MyBlockingQueueExample {
        public static void main(String[] args) {
            Queue<Integer> initialQueue = new LinkedList<>();
            MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(initialQueue);

            // Поток, который будет пытаться извлечь элемент из очереди
            Thread consumer = new Thread(() -> {
                try {
                    System.out.println("Consumer is trying to take an item...");
                    int item = blockingQueue.take();
                    System.out.println("Consumer took: " + item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Поток, который будет добавлять элемент в очередь
            Thread producer = new Thread(() -> {
                try {
                    System.out.println("Producer is sleeping for 2 seconds...");
                    Thread.sleep(2000);
                    System.out.println("Producer is adding an item...");
                    blockingQueue.add(1);
                    System.out.println("Producer added an item.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            consumer.start();
            producer.start();
        }
    }

    public static class MyBlockingQueueExample2 {
        public static void main(String[] args) {
            Queue<Integer> initialQueue = new LinkedList<>();
            MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(initialQueue);

            // Добавляем 10 элементов в очередь
            for (int i = 0; i < 10; i++) {
                blockingQueue.add(i);
            }

            // Извлекаем 10 элементов из очереди
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Taken: " + blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Очередь теперь пуста. Но мы можем вызывать `take()` еще 10 раз, и он не будет блокироваться из-за накопленных разрешений в семафоре.
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Trying to take when queue is empty...");
                    System.out.println("Taken: " + blockingQueue.take()); // Этот вызов не должен проходить успешно, так как очередь пуста.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    private static class Pool<T> {
//        private final Collection<T> items;
//        private final Semaphore semaphore;
//        private final boolean[] acquired;
//
//        public Pool(Collection<T> items, int permits) {
//            this.items = Collections.synchronizedCollection(items);
//            this.semaphore = new Semaphore(permits);
//            this.acquired = new boolean[permits];
//        }
//
//        public T acquire() throws InterruptedException {
//            semaphore.acquire();
//
//            synchronized (this) {
////                items
//            }
//
//            throw new NotImplementedException();
//        }
//    }
}
