package com.mrgrd56.models.calc.operation;

import java.util.Objects;

public enum ComparisonOperation implements BiOperation<Double, Boolean> {
    EQUALS {
        @Override
        public Boolean execute(Double firstOperand, Double secondOperand) {
            return Objects.equals(firstOperand, secondOperand);
        }
    },
    GREATER_THAN {
        @Override
        public Boolean execute(Double firstOperand, Double secondOperand) {
            return firstOperand > secondOperand;
        }
    },
    LOWER_THAN {
        @Override
        public Boolean execute(Double firstOperand, Double secondOperand) {
            return null;
        }
    }
}
