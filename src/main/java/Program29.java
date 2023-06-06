import org.apache.commons.lang3.NotImplementedException;

import java.util.Objects;

public class Program29 {
    public static void main(String[] args) {
        new Program29().start();
    }

    public void start() {
        Hello hello = new Hello(42);
        Hello.World world = hello.new World(43);
        KonnichiwaSekai sekai = new KonnichiwaSekai(hello);
    }

    private class Hello {
        private int value = 42;

        public Hello(int value) {
            this.value = value;
        }

        public String foo() {
            throw new NotImplementedException();
        }

        private class World extends Hello {
            public World(int value) {
                super(value);
            }

            @Override
            public String foo() {
                return Objects.toString(Hello.this.value);
            }
        }
    }

    private class KonnichiwaSekai extends Hello.World {
        public KonnichiwaSekai(Hello hello) {
            hello.super(hello.value);
        }

        @Override
        public String foo() {
            return "Japan " + super.foo();
        }
    }

    static class Egg2 {
        protected class Yolk {
            public Yolk() { System.out.println("Egg2.Yolk()"); }
            public void f() { System.out.println("Egg2.Yolk.f()");}
        }
        private Yolk y = new Yolk();
        public Egg2() { System.out.println("New Egg2()"); }
        public void insertYolk(Yolk yy) { y = yy; }
        public void g() { y.f(); }
    }
    public static class BigEgg2 extends Egg2 {
        public class Yolk extends Egg2.Yolk {
            public Yolk() { System.out.println("BigEgg2.Yolk()"); }
            public void f() { System.out.println("BigEgg2.Yolk.f()"); }
        }
        public BigEgg2() { insertYolk(new Yolk()); }

        public static void main(String[] args) {
            Egg2 e2 = new BigEgg2();
            e2.g();
        }
    }
}