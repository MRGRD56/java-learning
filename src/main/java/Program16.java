public class Program16 {
    public static void main(String[] args) {
        String str1 = new String("werwer");
        String str2 = str1.intern();

        System.out.println(str1 == str2); // This will print "false"
    }
}
