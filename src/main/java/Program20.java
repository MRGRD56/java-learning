import com.mrgrd56.models.people.Gender;
import com.mrgrd56.models.people.Person;
import com.mrgrd56.models.people.comparator.OlderPersonComparator;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class Program20 {
    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>(new OlderPersonComparator());
        queue.offer(new Person("John", 12, Gender.MALE));
        queue.offer(new Person("Ann", 11, Gender.FEMALE));
        queue.offer(new Person("Granny", 86, Gender.FEMALE));
        queue.offer(new Person("Ivan", 30, Gender.MALE));

        Person person;
        while ((person = queue.poll()) != null) {
            System.out.println(person);
        }
    }

    private static void textPriorityQueue() {
        PriorityQueue<Person> queue = new PriorityQueue<>(new OlderPersonComparator());

        Person person;
        while ((person = queue.poll()) != null) {
            System.out.println(person);
        }
    }

    private static void testTreeSet() {
        TreeSet<Person> set = new TreeSet<>(new OlderPersonComparator());

        Person person;
        while ((person = set.pollFirst()) != null) {
            System.out.println(person);
        }
    }
}
