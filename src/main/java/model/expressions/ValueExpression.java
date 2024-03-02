package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class ValueExpression implements ExpressionInterface {
    private final ValueInterface value;

    public ValueExpression(ValueInterface value) {
        this.value = value;
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        return value;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ValueExpression(value.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
