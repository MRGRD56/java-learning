package com.mrgrd56.somePackage.math.second.factory;

import com.mrgrd56.somePackage.math.second.AdditionOperation;
import com.mrgrd56.somePackage.math.second.MathOperation;

public class AdditionOperationFactory implements MathOperationFactory {

    @Override
    public MathOperation create(int operand1, int operand2) {
        return new AdditionOperation(operand1, operand2);
    }
}
