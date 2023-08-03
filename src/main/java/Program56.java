import dev.b37.mgutils.concurrent.TaskInvoker;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program56 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        Files.deleteIfExists(Path.of("src/main/resources/p56.txt"));

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/p56.txt", "rw")) {
            for (int i = 0; i < 500; i++) {
                randomAccessFile.writeInt(i);
            }
        }

        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/p56.txt", "rw")) {
            for (int i = 0; i < 2; i++) {
                int threadId = i;
                taskInvoker.submit(() -> {
                    for (int j = 0; j < 2; j++) {
                        synchronized (randomAccessFile) {
                            try (FileLock ignored = randomAccessFile.getChannel().lock()) {
                                System.out.printf("[Thread-%d] Write-%d Begin\n", threadId, j);

                                FileChannel fileChannel = randomAccessFile.getChannel();
                                MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
                                IntBuffer intBuffer = buffer.asIntBuffer();

                                while (intBuffer.hasRemaining()) {
                                    intBuffer.mark();
                                    int number = intBuffer.get();
                                    intBuffer.reset();
                                    intBuffer.put(number * 3);
                                }

                                buffer.force();

                                System.out.printf("[Thread-%d] Write-%d End\n", threadId, j);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                taskInvoker.submit(() -> {
                    for (int j = 0; j < 2; j++) {
                        synchronized (randomAccessFile) {
                            try (FileLock ignored = randomAccessFile.getChannel().lock()) {
                                System.out.printf("[Thread-%d] Read-%d Begin\n", threadId, j);

                                FileChannel fileChannel = randomAccessFile.getChannel();
                                MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
                                IntBuffer intBuffer = buffer.asIntBuffer();

                                while (intBuffer.hasRemaining()) {
                                    System.out.print(intBuffer.get());
                                    System.out.print(", ");
                                }

                                System.out.printf("[Thread-%d] Read-%d End\n", threadId, j);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            taskInvoker.completeAllVoid();
        }
    }
}
