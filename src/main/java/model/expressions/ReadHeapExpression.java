package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.RefType;
import model.types.TypeInterface;
import model.values.RefValue;
import model.values.ValueInterface;

public class ReadHeapExpression implements ExpressionInterface {
    private final ExpressionInterface expression;

    public ReadHeapExpression(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue refValue)) throw new MyException("Expression not of reference type!");

        int address = refValue.getAddress();
        if (!heap.exists(address)) throw new MyException("Not allocated on heap");

        return heap.get(address);
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface type = expression.typeCheck(table);
        if (type instanceof RefType referenceType) {
            return referenceType.getInner();
        } else throw new MyException("Expression not of reference type");
    }

    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }
}
