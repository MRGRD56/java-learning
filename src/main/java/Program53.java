import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program53 {
    public static void main(String[] args) throws Exception {
        try (OutputStream fileOutputStream = new BufferedOutputStream(Files.newOutputStream(Path.of("src/main/resources/data53.dat")))) {
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            for (int i = 0; i < 128; i++) {
                dataOutputStream.write(0);
            }

            for (int i = 1; i <= 512; i++) {
                dataOutputStream.writeInt(i);
            }
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/data53.dat", "rw")) {
            FileChannel fileChannel = randomAccessFile.getChannel();

            MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 128, randomAccessFile.length() - 128);
            IntBuffer intBuffer = mappedBuffer.asIntBuffer();

            while (intBuffer.hasRemaining()) {
                System.out.print(intBuffer.get());
                System.out.print(' ');
            }

            intBuffer.rewind();

            while (intBuffer.hasRemaining()) {
                intBuffer.mark();
                int number = intBuffer.get();
                intBuffer.reset();
                intBuffer.put(number * 2);
            }

            mappedBuffer.force();
        }

        System.out.print("\n\n----------\n\n");

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/data53.dat", "r")) {
            FileChannel fileChannel = randomAccessFile.getChannel();

            MappedByteBuffer mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 128, randomAccessFile.length() - 128);
            IntBuffer intBuffer = mappedBuffer.asIntBuffer();

            while (intBuffer.hasRemaining()) {
                System.out.print(intBuffer.get());
                System.out.print(' ');
            }
        }
    }
}
