public class Program9 {
    public static void main(String[] args) {
        long valLong = 99999999999L;
        int valInt1 = (int) valLong;
        int valInt2 = Long.valueOf(valLong).intValue();

        int qwe = 0x12_34_56_78;
        long qwe2 = 0x12_34_56_78_9A_BCL;

        int qwe2Int = (int) qwe2;
        String qwe2IntString = Integer.toString(qwe2Int, 16);
    }
}
