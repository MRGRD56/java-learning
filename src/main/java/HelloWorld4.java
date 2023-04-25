import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

public class HelloWorld4 {
    public static void main(String[] args) {
        List<Integer> people = List.of(new Person("ewr", 234)).stream()
                .map(Person::age)
                .toList();

        new Person("", 2);
    }

    private record Person(String name, int age) { }
}
