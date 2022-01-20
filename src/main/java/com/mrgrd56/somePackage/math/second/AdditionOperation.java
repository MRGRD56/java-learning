package com.mrgrd56.somePackage.math.second;

public final class AdditionOperation extends MathOperation {
    public AdditionOperation(int operand1, int operand2) {
        super(operand1, operand2);
    }

    @Override
    public int calculate() {
        return getOperand1() + getOperand2();
    }
}
