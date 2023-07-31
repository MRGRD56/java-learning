//import sun.nio.ch.FileChannelImpl;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program48 {
    public static void main(String[] args) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/data48.dat")) {
            FileChannel fileChannel = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(100);
            IntBuffer intBuffer = buffer.asIntBuffer();

            for (int i = 1; i <= 10_000; i++) {
                if (!intBuffer.hasRemaining()) {
                    buffer.rewind();
                    buffer.limit(intBuffer.position() * Integer.BYTES);

                    fileChannel.write(buffer);

                    buffer.clear();
                    intBuffer.clear();
                }

                intBuffer.put(i);
            }

            buffer.rewind();
            buffer.limit(intBuffer.position() * Integer.BYTES);

            fileChannel.write(buffer);
        }

        try (InputStream fileInputStream = Files.newInputStream(Path.of("src/main/resources/data48.dat"))) {
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while (dataInputStream.available() > 0) {
                System.out.print(dataInputStream.readInt());
                System.out.print(" ");
            }
        }

        System.out.print("\n\n-------\n\n");

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/data48.dat")) {
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(8192);

            IntBuffer intBuffer = buffer.asIntBuffer();

            while (fileChannel.read(buffer) != -1) {
                intBuffer.limit(buffer.position() / Integer.BYTES);
                intBuffer.rewind();

                while (intBuffer.hasRemaining()) {
                    System.out.print(intBuffer.get());
                    System.out.print(' ');
                }

                buffer.clear();
            }
        }
    }
}
