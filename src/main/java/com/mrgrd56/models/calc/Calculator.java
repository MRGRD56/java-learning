package com.mrgrd56.models.calc;

import com.mrgrd56.models.calc.operation.ComparisonOperation;
import com.mrgrd56.models.calc.operation.MathOperation;
import com.mrgrd56.models.calc.operation.BiOperation;
import dev.b37.mgutils.concurrent.Lazy;
import org.jetbrains.annotations.NotNull;

public class Calculator {
    private Calculator() { }

    private static final Lazy<Calculator> instance = new Lazy<>(Calculator::new);

    @NotNull
    public static Calculator getInstance() {
        return instance.get();
    }

    public <O, R> R calculate(O firstOperand, O secondOperand, BiOperation<O, R> operation) {
        return operation.execute(firstOperand, secondOperand);
    }

    public static void main(String[] args) {
        Calculator calculator = Calculator.getInstance();
        double result = calculator.calculate(0.1, 0.2, MathOperation.ADD);
        boolean isGreater = calculator.calculate(result, 0.3, ComparisonOperation.GREATER_THAN);
        System.out.println("result = " + result);
        System.out.println("isGreater = " + isGreater);
    }
}
