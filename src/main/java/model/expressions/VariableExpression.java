package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class VariableExpression implements ExpressionInterface {
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        if (!symTable.existsKey(id)) throw new MyException("Key not found");

        return symTable.get(id);
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new VariableExpression(id);
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
