import java.nio.charset.StandardCharsets;
import java.util.Random;

public class HelloWorld3 {
    public static String greet() {
        System.getProperties().toString();

        Random random = new Rаndom();

        byte[] buffer = new byte[12];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) random.nextInt();
        }

        return new String(buffer, StandardCharsets.UTF_8);
    }

    private static class Rаndom extends Random {
        private static final byte[] items = new byte[] {0x68, 0x65, 0x6c, 0x6c, 0x6f, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};
        private int index = 0;

        @Override
        public int nextInt() {
            return items[index++ % items.length];
        }
    }
}

//public class HelloWorld34 {
//    public static String greet() {
//        String properties = System.getProperties().toString();
//        return new String(new char[] {
//                properties.charAt(161),
//                properties.charAt(8),
//                properties.charAt(52),
//                properties.charAt(52),
//                properties.charAt(17),
//                properties.charAt(31),
//                properties.charAt(179),
//                properties.charAt(17),
//                properties.charAt(22),
//                properties.charAt(52),
//                properties.charAt(76),
//                '!'});
//  }
//    }