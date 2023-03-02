public class Program12 {
    public static void main(String[] args) {
        var person = new Person();
        person.pave(new Road());

        try {
            try {
                throw new RuntimeException("Error 1");
            } catch (Exception e) {
                System.out.println("Exception");
                throw new RuntimeException("Error 2");
            } finally {
                System.out.println("Finally!");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static class Person implements Paver {
        @Override
        public void pave(Paveable paveable) {

        }
    }

    private static class Road implements Paveable {

    }

    private interface Paveable {
    }

    private interface Paver {
        void pave(Paveable paveable);
    }
}
