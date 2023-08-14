import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Program79 {
    public static void main(String[] args) {
        DelayQueue<DelayedValue> delayQueue = new DelayQueue<>();

        for (int i = 0; i < 10; i++) {
            int val = i;
            DelayedValue delayed = new DelayedValue() {
                private final long startTime = System.currentTimeMillis();

                @Override
                public int getValue() {
                    return val;
                }

                @Override
                public long getDelay(@NotNull TimeUnit unit) {
                    return unit.convert((startTime + 200 * val) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                }

                @Override
                public int compareTo(@NotNull Delayed o) {
                    if (o instanceof DelayedValue delayedValue) {
                        return Comparator.comparingInt(DelayedValue::getValue).compare(this, delayedValue);
                    }

                    return 0;
                }
            };

            delayQueue.put(delayed);
        }

        int size = delayQueue.size();
        for (int i = 0; i < size; i++) {
            try {
                DelayedValue value = delayQueue.take();
                System.out.println(value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    static abstract class DelayedValue implements Delayed {
        public abstract int getValue();

        @Override
        public String toString() {
            return new ToStringBuilder(this, new StandardToStringStyle() {
                {
                    setUseShortClassName(true);
                    setUseIdentityHashCode(false);
                }

                @Override
                protected String getShortClassName(Class<?> cls) {
                    return DelayedValue.class.getSimpleName();
                }
            })
                    .append(getValue())
                    .toString();
        }
    }
}
