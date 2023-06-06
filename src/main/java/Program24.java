import java.lang.reflect.*;

public class Program24 {

    public static void bagelify (String bagel, Object newVal, Field f ) {
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);

            f.set(bagel, newVal);
        } catch (Throwable t) {
        }
    }
    public static String greet() {
        String hello = "hello world!";
        String bagel = "bagel";
        String helloBagel = "Nicholas Cage, 1946 - Banana Pudding. May he rest is peace.";

        for (Field f : String.class.getDeclaredFields()) {
            try {f.setAccessible(true);
                bagelify (hello, f.get(helloBagel), f);
                bagelify (bagel, f.get(helloBagel), f);
            } catch (Throwable t) {}
        }
        return "bagel";
    }

    public static void main(String[] args) {
        String greeting = greet();

        Object __null = null;
    }
}