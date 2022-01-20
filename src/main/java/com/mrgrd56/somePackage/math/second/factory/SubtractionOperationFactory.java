package com.mrgrd56.somePackage.math.second.factory;

import com.mrgrd56.somePackage.math.second.MathOperation;
import com.mrgrd56.somePackage.math.second.SubtractionOperation;

public class SubtractionOperationFactory implements MathOperationFactory {

    @Override
    public MathOperation create(int operand1, int operand2) {
        return new SubtractionOperation(operand1, operand2);
    }
}
