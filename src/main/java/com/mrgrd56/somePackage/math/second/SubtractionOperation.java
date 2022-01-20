package com.mrgrd56.somePackage.math.second;

public final class SubtractionOperation extends MathOperation {
    public SubtractionOperation(int operand1, int operand2) {
        super(operand1, operand2);
    }

    @Override
    public int calculate() {
        return getOperand1() - getOperand2();
    }
}
