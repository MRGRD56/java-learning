public class IntArray {
    private int[] array;

    public IntArray(int length) {
        this(new int[length]);
    }

    public IntArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void resize(int newLength) {
        int[] newArray = new int[newLength];

        int populatedLength = Math.min(array.length, newArray.length);

        for (int i = 0; i < populatedLength; i++) {
            newArray[i] = array[i];
        } // System.arraycopy(array, 0, newArray, 0, populatedLength);

        array = newArray;
    }

    public void insert(int index, int value) {
        resize(array.length + 1);

        // 1 4 8 8 2 2 8
        //      !
        //      4 -> [3]
        // =============
        // 1 4 8 4 8 2 2 8

        for (int i = array.length - 1; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void addLast(int value) {
        insert(array.length, value);
    }

    public void remove(int index) {
        // 1 4 8 4 8 2 2 8
        //       ! <- [3]
        // ===============
        // 1 4 8 8 2 2 8

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }

        resize(array.length - 1);
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(array.length - 1);
    }
}
