import java.util.*;

public class Program18 {
    public static void main(String[] args) {
        Set<Number> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(2);

        Set<? extends Number> set2 = set;

        int number = (int) set2.iterator().next();

        set.retainAll(List.of(5, 2));

        Object nul = null;

//        List<Integer> integers = ((Set<Integer>) set2).stream().toList();
//
//        integers.set(1, 21223);
//
//        Collections.shuffle(integers);
    }
}
