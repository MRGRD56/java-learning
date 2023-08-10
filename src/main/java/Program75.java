import dev.b37.mgutils.concurrent.TaskInvoker;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program75 {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {
        TaskInvoker<Void> writerInvoker = new TaskInvoker<>(executor);
        TaskInvoker<Void> readerInvoker = new TaskInvoker<>(executor);

        try (PipedWriter writer = new PipedWriter();
             BufferedReader reader = new BufferedReader(new PipedReader(writer))) {
            readerInvoker.submit(() -> {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[LINE] " + line);
                }
            });

            for (int i = 0; i < 2; i++) {
                int finalI = i;
                writerInvoker.submit(() -> {
                    for (int j = finalI; j < words.length; j += 2) {
                        writer.write(finalI + words[j]);
                        if (j != 0 && j % 9 == 0) {
                            writer.write('\n');
                        } else {
                            writer.write(' ');
                        }

                        Thread.sleep(100 * (finalI + 1));
                    }
                });
            }

            CompletableFuture.runAsync(writerInvoker::completeAllVoid).whenComplete((v, e) -> {
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            readerInvoker.completeAllVoid();
            executor.shutdown();
        }
    }

    private static final String[] words = Arrays.stream("""
            If the code does not work, please request a new verification code and try these troubleshooting steps:
            Use an incognito browser tab or a different browser
            Clear your browser cache and cookies, and disable any browser add-ons or extensions
            If you’re using the Twitch app, please make sure it’s updated to the current version
            """.split("[\\n\\s.,\\-!?]")).filter(StringUtils::isNotBlank).toArray(String[]::new);
}
