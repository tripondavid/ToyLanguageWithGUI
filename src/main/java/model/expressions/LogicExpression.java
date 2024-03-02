package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.BoolType;
import model.types.TypeInterface;
import model.values.BoolValue;
import model.values.ValueInterface;

public class LogicExpression implements ExpressionInterface {
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private final int op; // 1 -> &, 2 -> |

    public LogicExpression(ExpressionInterface expression1, ExpressionInterface expression2, int op) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.op = op;
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = expression1.eval(symTable, heap);

        if (v1.getType().equals(new BoolType())) {
            v2 = expression2.eval(symTable, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue boolValue1 = (BoolValue) v1;
                BoolValue boolValue2 = (BoolValue) v2;
                boolean b1, b2;
                b1 = boolValue1.getValue();
                b2 = boolValue2.getValue();

                switch (op) {
                    case 1 -> {return new BoolValue(b1 & b2);}
                    case 2 -> {return new BoolValue(b1 | b2);}
                    default -> throw new MyException("Operation invalid!");
                }
            } else throw new MyException("The second operand is not a boolean");
        } else throw new MyException("The first operand is not a boolean");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new LogicExpression(expression1.deepCopy(), expression2.deepCopy(), op);
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface type1, type2;
        type1 = expression1.typeCheck(table);
        type2 = expression2.typeCheck(table);

        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not a boolean");
        } else throw new MyException("First operand is not a boolean");
    }
}
