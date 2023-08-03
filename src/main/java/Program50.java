import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program50 {
    public static void main(String[] args) throws Exception {
        Files.deleteIfExists(Path.of("src/main/resources/program50_le.dat"));

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/program50_le.dat", "rw")) {
            final int bufferSize = 512;

            ByteBuffer buffer = ByteBuffer.allocate(bufferSize).order(ByteOrder.LITTLE_ENDIAN);
            byte[] forwardBuffer = new byte[bufferSize];

            int i = 1;
            while (i <= 10_000) {
                while (buffer.remaining() >= Integer.BYTES && i <= 10_000) {
                    buffer.putInt(i++);
                }

                buffer.flip();

                buffer.get(forwardBuffer, 0, buffer.limit());
                randomAccessFile.write(forwardBuffer);

                buffer.clear();
            }

            System.out.println(randomAccessFile.length() + " bytes written");
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/program50_le.dat", "r")) {
            final int bufferSize = 512;

            byte[] forwardBuffer = new byte[bufferSize];
            ByteBuffer buffer = ByteBuffer.wrap(forwardBuffer).order(ByteOrder.LITTLE_ENDIAN);

            int i = (int) randomAccessFile.length();
            int bytesToRead = bufferSize;
            while (true) {
                i -= bufferSize;
                if (i < 0) {
                    bytesToRead = bufferSize + i;
                    i = 0;
                }

                if (bytesToRead <= 0) {
                    break;
                }

                randomAccessFile.seek(i);
                int bytesRead = randomAccessFile.read(forwardBuffer, 0, bytesToRead);
                buffer.limit(bytesRead);
                buffer.rewind();

                while (buffer.remaining() >= 4) {
                    System.out.print(buffer.getInt());
                    System.out.print(' ');
                }

                System.out.println('\n');

                buffer.clear();
            }
        }
    }
}
