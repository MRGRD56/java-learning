import com.mrgrd56.models.people.Gender;
import com.mrgrd56.models.people.Person;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Program23 {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        fetchPerson()
                .thenApply((person) -> {
                    System.out.println("Got the person!");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    person.setAge(person.getAge() + 1);

                    return person;
                })
                .thenAccept((person) -> {
                    System.out.println(person);
                });

        System.out.println("After running the future, I'm going to sleep...");

        Thread.sleep(5000);

        System.out.println("Ahhh, good morning...?");
    }

    private static CompletableFuture<Person> fetchPerson() {
        CompletableFuture<Person> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000, 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                future.completeExceptionally(e);
                return;
            }

            var person = new Person("Some Guy", random.nextInt(12, 40), Gender.MALE);
            future.complete(person);
        }).start();

        return future;
    }
}
