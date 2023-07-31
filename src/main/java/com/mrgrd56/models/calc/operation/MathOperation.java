package com.mrgrd56.models.calc.operation;

public enum MathOperation implements BiOperation<Double, Double> {
    ADD {
        @Override
        public Double execute(Double firstOperand, Double secondOperand) {
            return firstOperand + secondOperand;
        }
    },
    SUBTRACT {
        @Override
        public Double execute(Double firstOperand, Double secondOperand) {
            return firstOperand - secondOperand;
        }
    },
    MULTIPLY {
        @Override
        public Double execute(Double firstOperand, Double secondOperand) {
            return firstOperand * secondOperand;
        }
    },
    DIVIDE {
        @Override
        public Double execute(Double firstOperand, Double secondOperand) {
            return firstOperand / secondOperand;
        }
    };
}
