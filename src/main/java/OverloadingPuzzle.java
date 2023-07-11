public class OverloadingPuzzle {

    public static void print(Object obj) {
        System.out.println("Object");
    }

    public static void print(char[] chars) {
        System.out.println("char array");
    }

    public static void print(int integer) {
        System.out.println("int");
    }

    public static void main(String[] args) {
        char[] myCharArray = {'H', 'e', 'l', 'l', 'o'};
        print(null);
        print(myCharArray);
        print(5);
    }
}
