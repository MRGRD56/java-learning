package com.mrgrd56.somePackage.math.second;

public abstract class MathOperation {
    private final int operand1;
    private final int operand2;

    protected MathOperation(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public abstract int calculate();
}
