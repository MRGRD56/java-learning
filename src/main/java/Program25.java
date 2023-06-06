import java.util.ArrayList;
import java.util.List;

public class Program25 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        strings.add("Hello");
        ((List) strings).add(42);

        Object s = strings.get(1);

        Object __null = null;
    }
}