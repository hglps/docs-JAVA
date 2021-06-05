package main;

import java.util.HashMap;

public class OperatorFactory {

    private HashMap<String, Operation> operationMap = new HashMap<>();
    {
        operationMap.put("add", new AddCommand());
        operationMap.put("multiply", new MultiplyCommand());
    }

    public Operation getOperation(String operator){
        return operationMap.get(operator);
    }


}
