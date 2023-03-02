public class TryCatchFinallyProgram {
    public static void main(String[] args) {
        boolean isSuccess = true;

        try {
            System.out.println("Try");
            throw new RuntimeException("Exception");
        } catch (Exception e) {
            isSuccess = false;
            System.out.println("Catch");
            throw e;
        } finally {
            System.out.println("Finally, isSuccess=" + isSuccess);
        }
    }
}
