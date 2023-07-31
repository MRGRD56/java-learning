package com.mrgrd56.models.calc.operation;

public interface BiOperation<O, R> {
    R execute(O firstOperand, O secondOperand);
}
