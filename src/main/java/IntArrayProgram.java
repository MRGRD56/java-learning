public class IntArrayProgram {
    public static void main(String[] args) {
        IntArray intArray = new IntArray(new int[] {4, 20, 3, 6, 2});
        intArray.addFirst(5);
        intArray.addLast(10);
        intArray.insert(3, 33);

        intArray.remove(3);
        intArray.removeLast();
        intArray.removeFirst();

        Object __null = null;
    }
}
