import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        var string = "qweqqq";
        var character = string.charAt(2);

        var map = new HashMap<>();
        map.put(null, 123);
        map.put("hello", "world");
        map.put("name", 42);
        var val = map.get(null);
        var val2 = map.get("hello");
    }
}
