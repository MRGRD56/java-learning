import java.util.List;

public final class ImmutableClass {
    public static void main(String[] args) {
        var immutable = new ImmutableClass();
        var anotherImmutable = immutable.withA(234);
    }

    public ImmutableClass() {
        this.a = 0;
        this.strings = null;
    }

    public ImmutableClass(int a, List<String> strings) {
        this.a = a;
        this.strings = List.copyOf(strings);
    }

    private final int a;
    private final List<String> strings;

    public int getA() {
        return a;
    }

    public ImmutableClass withA(int a) {
        return new ImmutableClass(a, strings);
    }

    public List<String> getStrings() {
        return strings == null ? null : List.copyOf(strings);
    }

    public ImmutableClass withStrings(List<String> strings) {
        return new ImmutableClass(a, strings);
    }
}
