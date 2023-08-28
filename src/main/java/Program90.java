import java.util.ArrayList;
import java.util.List;

public class Program90 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);

        for (var item : list) {
            list.remove(1);
        }
    }
}
