package com.mrgrd56.somePackage.math.first;

public final class SubtractionOperation implements MathOperation {
    private final int operand1;
    private final int operand2;

    public SubtractionOperation(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int getOperand1() {
        return operand1;
    };

    @Override
    public int getOperand2() {
        return operand2;
    }

    @Override
    public int calculate() {
        return operand1 - operand2;
    }
}
