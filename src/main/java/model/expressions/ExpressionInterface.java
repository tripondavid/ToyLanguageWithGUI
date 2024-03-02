package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public interface ExpressionInterface {
    ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException;
    ExpressionInterface deepCopy();
    TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException;
}
