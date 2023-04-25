import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HelloWorld {
    public static String greet() {
        var outputStream = new ByteArrayOutputStream();

        CharWriters.H.write(outputStream);
        CharWriters.E.write(outputStream);
        CharWriters.L.write(outputStream);
        CharWriters.L.write(outputStream);
        CharWriters.O.write(outputStream);
        CharWriters.SPACE.write(outputStream);
        CharWriters.W.write(outputStream);
        CharWriters.O.write(outputStream);
        CharWriters.R.write(outputStream);
        CharWriters.L.write(outputStream);
        CharWriters.D.write(outputStream);
        CharWriters.EXCLAMATION_MARK.write(outputStream);

        return outputStream.toString(StandardCharsets.UTF_8);
    }

    private interface CharWriter {
        void write(OutputStream output);
    }

    private enum CharWriters implements CharWriter {
        H(0x68),
        E(0x65),
        L(0x6c),
        O(0x6f),
        SPACE(0x20),
        W(0x77),
        R(0x72),
        D(0x64),
        EXCLAMATION_MARK(0x21);

        private final int charByte;

        CharWriters(int charByte) {
            this.charByte = charByte;
        }

        @Override
        public void write(OutputStream output) {
            try {
                output.write(charByte);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
