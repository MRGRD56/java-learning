import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ByteStream {

    public static void main(String[] args) throws Exception {
        var bs = new ByteStream();
        bs.flip();
        bs.write((byte) 1);
        bs.write((byte) 2);
        bs.write((byte) 3);
        bs.flip();
        System.out.println(bs.read());
        System.out.println(bs.read());
        System.out.println(bs.read());
        System.out.println(bs.read());
    }

    private Mode mode = null;

    private PipedOutputStream outputStream;
    private PipedInputStream inputStream;

    public ByteStream() throws IOException {
        this.outputStream = new PipedOutputStream();
        this.inputStream = new PipedInputStream(outputStream, Integer.MAX_VALUE - 8);
    }

    /**
     * Flip stream between write and read mode.
     * Before the first call of this method, the stream is neither in read nor write mode.
     * The first call of this method switches the stream to write mode.
     * After a switch to write mode, the stream is empty and bytes may be written.
     * After a switch to read mode, written bytes may be read.
     * @throws IOException if an I/O error occurs
     */
    public void flip() throws IOException {
        if (mode == Mode.WRITE) {
            mode = Mode.READ;
        } else {
            mode = Mode.WRITE;
        }
    }

    /**
     * Write byte to stream.
     * @param b byte
     * @throws IOException if stream is not in write mode or an I/O error occurs
     */
    public void write(byte b) throws IOException {
        if (mode != Mode.WRITE) {
            throw new IOException("Stream is not in write mode");
        }

        outputStream.write(b);
    }

    /**
     * Read byte from stream.
     * @return -1 if stream is empty, byte value (0 to 255) otherwise
     * @throws IOException if stream is not in read mode or an I/O error occurs
     */
    public int read() throws IOException {
        if (mode != Mode.READ) {
            throw new IOException("Stream is not in read mode");
        }

        if (inputStream.available() == 0) {
            return -1;
        }

        return inputStream.read();
    }

    private enum Mode {
        READ,
        WRITE
    }
}
