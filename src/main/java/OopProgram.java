import com.mrgrd56.somePackage.math.second.MathOperation;
import com.mrgrd56.somePackage.math.second.factory.AdditionOperationFactory;
import com.mrgrd56.somePackage.math.second.factory.MathOperationFactory;

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
