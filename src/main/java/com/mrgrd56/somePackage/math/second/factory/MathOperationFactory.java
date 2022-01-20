package com.mrgrd56.somePackage.math.second.factory;

import com.mrgrd56.somePackage.math.second.MathOperation;

public interface MathOperationFactory {
    MathOperation create(int operand1, int operand2);
}
