import java.nio.CharBuffer;

public class Program52 {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.wrap("Hello World!".toCharArray());

        codeBuffer(charBuffer);

        System.out.println(charBuffer);

        codeBuffer(charBuffer);

        System.out.println(charBuffer);
    }

    private static void codeBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();

            char char1 = buffer.get();
            char char2 = buffer.get();

            buffer.reset();

            buffer.put(char2);
            buffer.put(char1);
        }

        buffer.rewind();
    }
}
