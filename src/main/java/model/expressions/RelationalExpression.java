package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.BoolType;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.ValueInterface;

public class RelationalExpression implements ExpressionInterface {
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private final String op;

    public RelationalExpression(ExpressionInterface expression1, ExpressionInterface expression2, String op) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.op = op;
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = expression1.eval(symTable, heap);

        if (v1.getType().equals(new IntType())) {
            v2 = expression2.eval(symTable, heap);

            if (v2.getType().equals(new IntType())) {
                IntValue intValue1 = (IntValue) v1;
                IntValue intValue2 = (IntValue) v2;

                int n1, n2;
                n1 = intValue1.getValue();
                n2 = intValue2.getValue();

                switch (op) {
                    case "<" -> {
                        return new BoolValue(n1 < n2);
                    }
                    case "<=" -> {
                        return new BoolValue(n1 <= n2);
                    }
                    case "==" -> {
                        return new BoolValue(n1 == n2);
                    }
                    case "!=" -> {
                        return new BoolValue(n1 != n2);
                    }
                    case ">" -> {
                        return new BoolValue(n1 > n2);
                    }
                    case ">=" -> {
                        return new BoolValue(n1 >= n2);
                    }
                    default -> throw new MyException("Operation invalid!");
                }
            } else throw new MyException("The second operand is not an integer");
        } else throw new MyException("The first operand is not an integer");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new RelationalExpression(expression1.deepCopy(), expression2.deepCopy(), op);
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface type1, type2;
        type1 = expression1.typeCheck(table);
        type2 = expression2.typeCheck(table);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not an integer");
        } else throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + this.op + " " + this.expression2.toString();
    }
}
