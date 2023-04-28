import com.mrgrd56.models.people.Gender;
import com.mrgrd56.models.people.Person;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Program23 {
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        logThread("very beginning");

        fetchPerson()
                .thenApplyAsync((person) -> {
                    logThread("got the person");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    person.setAge(person.getAge() + 1);

                    logThread("Finished processing the person");

                    return person;
                })
                .thenAcceptAsync((person) -> {
                    logThread("Printing the person");

                    System.out.println(person);
                });

        logThread("After running the future, I'm going to sleep...");

        Thread.sleep(5000);

        logThread("Ahhh, good morning...? Uh, no......");

        Thread.sleep(5000);

        logThread("---Is it already 11 PM?");
    }

    private static CompletableFuture<Person> fetchPerson() {
        CompletableFuture<Person> future = new CompletableFuture<>();

        logThread("fetchPerson beginning");

        new Thread(() -> {
            logThread("fetchPerson thread beginning");

            try {
                Thread.sleep(random.nextInt(1000, 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                future.completeExceptionally(e);
                return;
            }

            var person = new Person("Some Guy", random.nextInt(12, 40), Gender.MALE);
            logThread("before future.complete");
            future.complete(person);
            logThread("after future.complete");
        }, "person-fetching").start();

        return future;
    }

    private static void logThread(Object message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}
