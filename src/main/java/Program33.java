import java.util.Scanner;

public class Program33 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the calculator blah blah blah");
        System.out.print("Enter the first number: ");
        double number1 = scanner.nextDouble();
        System.out.print("Enter the second number: ");
        double number2 = scanner.nextDouble();
        System.out.print("Enter the operation ['+', '-', '*', '/']: ");
        String operation = scanner.next();

        double result = switch (operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;
            default -> throw new IllegalArgumentException("Unknown operation '" + operation + "'");
        };

        System.out.printf("Result: %f\n", result);
    }
}
