import java.util.concurrent.CyclicBarrier;

public class Game {

    public static void main(String[] args) {
        int players = 4;
        CyclicBarrier barrier = new CyclicBarrier(players, () -> System.out.println("Все игроки готовы. Старт игры!"));

        for (int i = 1; i <= players; i++) {
            new Thread(new Player(i, barrier)).start();
        }
    }
}

class Player implements Runnable {
    private int id;
    private CyclicBarrier barrier;

    public Player(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Игрок " + id + " готовится...");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("Игрок " + id + " готов");
            
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}