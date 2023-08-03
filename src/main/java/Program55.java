public class Program55 {
    public static void main(String[] args) {
        String str = String.format("TEMPLA %s TE" + "%nСрок исполнения: <code>%s</code>", "one", "two");

        System.out.println(str);

        System.out.printf("TEMPLA %s TE" + "%n%nСрок исполнения: <code>%s</code>%n", "one", "two");
    }
}
