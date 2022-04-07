import com.mrgrd56.common.MyList;

public class MyListProgram {
    public static void main(String[] args) {
        var myList = new MyList<>(new Integer[] {1, 2, 3, 5, 22, 333});
        for (var item : myList) {
            System.out.println(item);
        }

        myList.add(1337);
        myList.remove(3);

        myList.forEach(System.out::print);
    }
}
