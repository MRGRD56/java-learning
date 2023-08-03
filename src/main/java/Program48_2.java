import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program48_2 {
    public static void main(String[] args) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/data48_2.dat")) {
            FileChannel fileChannel = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(100);

            for (int i = 1; i <= 10_000; i++) {
                if (buffer.remaining() < 4) {
                    buffer.rewind();

                    fileChannel.write(buffer);

                    buffer.clear();
                }

                buffer.putInt(i);
            }

            buffer.rewind();

            fileChannel.write(buffer);
        }

        try (InputStream fileInputStream = Files.newInputStream(Path.of("src/main/resources/data48_2.dat"))) {
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while (dataInputStream.available() > 0) {
                System.out.print(dataInputStream.readInt());
                System.out.print(" ");
            }
        }

        System.out.print("\n\n-------\n\n");

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/data48_2.dat")) {
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(8192);

            while (fileChannel.read(buffer) != -1) {
                buffer.flip();

                while (buffer.remaining() >= 4) {
                    System.out.print(buffer.getInt());
                    System.out.print(' ');
                }

                buffer.clear();
            }
        }
    }
}
