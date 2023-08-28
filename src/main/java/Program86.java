import java.math.BigInteger;

public class Program86 {
    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            BigInteger fibonacchiNumber = fibonacchi(i);
            System.out.println("fib(" + i + ") = " + fibonacchiNumber);
        }
    }

    private static BigInteger fibonacchi(int n) {
        if (n < 93) {
            return BigInteger.valueOf(fibonacchiLong(n));
        }

        final BigInteger FIBONACCHI0 = BigInteger.ZERO;
        final BigInteger FIBONACCHI1 = BigInteger.ONE;

        BigInteger n0 = FIBONACCHI0;
        BigInteger n1 = FIBONACCHI1;

        for (int i = 2; i <= n; i++) {
            BigInteger previous = n1;
            n1 = n0.add(n1);
            n0 = previous;
        }

        return n1;
    }

    private static long fibonacchiLong(int n) {
        final long FIBONACCHI0 = 0;
        final long FIBONACCHI1 = 1;

        if (n == 0) {
            return FIBONACCHI0;
        }

        if (n == 1) {
            return FIBONACCHI1;
        }

        long n0 = FIBONACCHI0;
        long n1 = FIBONACCHI1;

        for (int i = 2; i <= n; i++) {
            long previous = n1;
            n1 = n0 + n1;
            n0 = previous;
        }

        return n1;
    }

//    private abstract class EffectiveBigInteger {
//        public abstract EffectiveBigInteger add(EffectiveBigInteger another);
//        public abstract BigInteger value();
//
//        private static class LongBased extends EffectiveBigInteger {
//            private final long value;
//
//            public LongBased(long value) {
//                this.value = value;
//            }
//
//            @Override
//            public EffectiveBigInteger add(EffectiveBigInteger another) {
//                return null;
//            }
//
//            @Override
//            public BigInteger value() {
//                return null;
//            }
//        }
//
//        private static class BigIntegerBased extends EffectiveBigInteger {
//
//        }
//    }

    private static BigInteger fibonacchi1(int n) {
        final BigInteger FIBONACCHI0 = BigInteger.ZERO;
        final BigInteger FIBONACCHI1 = BigInteger.ONE;

        if (n == 0) {
            return FIBONACCHI0;
        }

        if (n == 1) {
            return FIBONACCHI1;
        }

        BigInteger n0 = FIBONACCHI0;
        BigInteger n1 = FIBONACCHI1;

        for (int i = 2; i <= n; i++) {
            BigInteger previous = n1;
            n1 = n0.add(n1);
            n0 = previous;
        }

        return n1;
    }
}
