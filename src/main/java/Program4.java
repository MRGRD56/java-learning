public final class Program4 {
    public static void main(String[] args) {
        Base sub = new Sub();
        sub.test();
    }
}

class Base {
    public static void test() {
        System.out.println("base");
    }
}

class Sub extends Base {
    public static void test() {
        System.out.println("sub");
    }
}