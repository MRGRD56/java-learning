import java.util.Map;

public class Program49 {
    public static void main(String[] args) {
        Map<String, Object> map = Map.ofEntries(
                Map.entry("name", "John"),
                Map.entry("age", 18),
                Map.entry("address", Map.ofEntries(
                        Map.entry("street", "Unnamed"),
                        Map.entry("house", 214)
                ))
        );

        System.out.println(map);
    }
}
