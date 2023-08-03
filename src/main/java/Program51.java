import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Program51 {
    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[3 * Integer.BYTES]).order(ByteOrder.LITTLE_ENDIAN);

        buffer.putInt(1);
        buffer.putInt(2);
        buffer.asIntBuffer().put(3);

        System.out.println(Arrays.toString(buffer.array()));
    }
}
