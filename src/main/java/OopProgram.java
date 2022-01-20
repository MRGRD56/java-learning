import com.mrgrd56.somePackage.math.second.*;
import com.mrgrd56.somePackage.math.second.factory.*;

public class OopProgram {
    private static MathOperation createOperation(MathOperationFactory operationFactory) {
        return operationFactory.create(2, 3);
    }

    public static void main(String[] args) {
        MathOperationFactory operationFactory = new AdditionOperationFactory();
        MathOperation operation = createOperation(operationFactory);
        var result = operation.calculate();
        System.out.println(result);
    }
}
