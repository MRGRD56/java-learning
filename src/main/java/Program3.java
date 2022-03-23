public class Program3 {
    public static void main(String[] args) {
//        int intVal = 123;
//        Integer integerVal = Integer.valueOf(123).intValue();
//        int intValInteger = Integer.valueOf(123).intValue();
//        Integer integerValInt = Integer.valueOf(123).
        var num2 = 0b1001_0110;
        var num8 = 056234;
        var num10 = 1_231_241;
        var num16 = 0xff_ff_fc;
        float numFloat = 0.123F;

        var okCode = HttpCode.OK.getMessage();
        System.out.println(okCode);

        var eightBits = new EightBits();
        eightBits.setBit1(false);
        eightBits.setBit2(true);
        eightBits.setBit3(false);
        eightBits.setBit4(false);
        eightBits.setBit5(true);
        eightBits.setBit6(false);
        eightBits.setBit7(true);
        eightBits.setBit8(true);
        System.out.println(Integer.toString(eightBits.getByteData(), 2));
    }
}
