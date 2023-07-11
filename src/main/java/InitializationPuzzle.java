public class InitializationPuzzle {

    static {
//        System.out.println("Static block 1 - " + str);
    }

    private static String str = "Hello, World!";

    static {
        System.out.println("Static block 2 - " + str);
    }

    public static void main(String[] args) {
        System.out.println("Main method - " + str);
    }
}