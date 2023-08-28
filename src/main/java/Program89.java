import java.util.ArrayList;
import java.util.LinkedList;

public class Program89 {
    public static void main(String[] args) throws Exception {
        Boolean a = true;
        Boolean b = new Boolean(true);
        System.out.println(a == b);

        String s1 = "hello ";
        String s2 = "world!";
        String s4 = "hello world!";
        String s3 = s1 + s2;
        System.out.println(s3 == s4);

        System.out.println("-Loading SLC " + Runtime.getRuntime().totalMemory());
//        Program89.class.getClassLoader().loadClass("com.mrgrd56.common.SomeLazyClass");
        Class<?> someLazyClass = Class.forName("com.mrgrd56.common.SomeLazyClass");
        System.out.println(((String[]) someLazyClass.getField("smth").get(null)).length);
        System.out.println(new byte[999999999]);
        System.out.println("-Loaded  SLC " + Runtime.getRuntime().totalMemory());

        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
//        arrayList.remove()
    }
}
