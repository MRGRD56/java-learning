import java.lang.reflect.Field;

public class Program31 {
    public static void main(String[] args) throws Exception {
        Hello hello = new Program31().new Hello();

        Class<?> worldClass = hello.getClass();
        System.out.println(worldClass);

        for (Field field : worldClass.getDeclaredFields()) {
            System.out.println(field);
        }
    }

    public class Hello {
        class World { }
    }
}
