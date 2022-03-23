public class EightBits {
    private static boolean intToBoolean(int number) {
        if (number < 0 || number > 1) {
            throw new UnsupportedOperationException("Number must be 0 or 1");
        }

        return number == 1;
    }

    private static byte booleanToByte(boolean value) {
        return (byte) (value ? 1 : 0);
    }

    private static int invertByte(int value) {
        return value ^ 0b1111_1111;
    }

    private static final int BIT1 = 0b0000_0001;
    private static final int BIT2 = 0b0000_0010;
    private static final int BIT3 = 0b0000_0100;
    private static final int BIT4 = 0b0000_1000;
    private static final int BIT5 = 0b0001_0000;
    private static final int BIT6 = 0b0010_0000;
    private static final int BIT7 = 0b0100_0000;
    private static final int BIT8 = 0b1000_0000;

    private byte byteData = 0b0000_0000;

    public byte getByteData() {
        return byteData;
    }

    public boolean getBit1() {
        return intToBoolean(byteData & BIT1);
    }

    public boolean getBit2() {
        return intToBoolean((byteData & BIT2) >> 1);
    }

    public boolean getBit3() {
        return intToBoolean((byteData & BIT3) >> 2);
    }

    public boolean getBit4() {
        return intToBoolean((byteData & BIT4) >> 3);
    }

    public boolean getBit5() {
        return intToBoolean((byteData & BIT5) >> 4);
    }

    public boolean getBit6() {
        return intToBoolean((byteData & BIT6) >> 5);
    }

    public boolean getBit7() {
        return intToBoolean((byteData & BIT7) >> 6);
    }

    public boolean getBit8() {
        return intToBoolean((byteData & BIT8) >> 7);
    }

    public void setBit1(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT1) + booleanToByte(value)));
    }

    public void setBit2(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT2) + (booleanToByte(value) << 1)));
    }

    public void setBit3(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT3) + (booleanToByte(value) << 2)));
    }

    public void setBit4(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT4) + (booleanToByte(value) << 3)));
    }

    public void setBit5(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT5) + (booleanToByte(value) << 4)));
    }

    public void setBit6(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT6) + (booleanToByte(value) << 5)));
    }

    public void setBit7(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT7) + (booleanToByte(value) << 6)));
    }

    public void setBit8(boolean value) {
        byteData = (byte) (byteData & (invertByte(BIT8) + (booleanToByte(value) << 7)));
    }
}
