import java.util.function.Consumer;

public class Program10 {
    public static void main(String[] args) {
        var array = new Integer[] {1, 5, 2, 7, 3, 5, 8, 3, 9, -1, -5};

        for (var i = 1; i < array.length; i++) {
            for (var j = 1; j < array.length; j++) {
                if (array[j] < array[j - 1]) {
                    var first = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = first;
                }
            }
        }

        foreach(array, item -> {
            System.out.print(item + " ");
        });
    }

    public static <T> void foreach(T[] array, Consumer<T> action) {
        foreach(array, action, 0);
    }

    private static <T> void foreach(T[] array, Consumer<T> action, int i) {
        action.accept(array[i]);
        if (i < array.length - 1) {
            foreach(array, action, i + 1);
        }
    }
}

// Iterator & Iterable
// (Buffered)InputStream & (Buffered)OutputStream
// Reader
// ArrayList vs LinkedList, реализация LinkedList
// try with resources