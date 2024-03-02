package model.expressions;

import exception.MyException;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class ArithmeticExpression implements ExpressionInterface {
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private int op; // 1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(ExpressionInterface expression1, ExpressionInterface expression2, char op) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        switch (op){
            case '+' -> this.op = 1;
            case '-' -> this.op = 2;
            case '*' -> this.op = 3;
            case '/' -> this.op = 4;
        }
    }

    @Override
    public ValueInterface eval(MyDictionaryInterface<String, ValueInterface> symTable, MyHeapInterface<ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = expression1.eval(symTable, heap);

        if (v1.getType().equals(new IntType())) {
            v2 = expression2.eval(symTable, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();

                switch (op) {
                    case 1 -> {return new IntValue(n1 + n2);}
                    case 2 -> {return new IntValue(n1 - n2);}
                    case 3 -> {return new IntValue(n1 * n2);}
                    case 4 -> {
                        if (n2 == 0) throw new MyException("Division by 0!");
                        return new IntValue(n1 / n2);
                    }
                    default -> throw new MyException("Operation invalid!");
                }
            } else throw new MyException("The second operand is not an integer");
        } else throw new MyException("The first operand is not an integer");
    }

    @Override
    public ExpressionInterface deepCopy() {
        char newOp;
        switch (this.op){
            case 1 -> newOp = '+';
            case 2 -> newOp = '-';
            case 3 -> newOp = '*';
            case 4 -> newOp = '/';
            default -> newOp = ' ';
        }
        return new ArithmeticExpression(expression1.deepCopy(), expression2.deepCopy(), newOp);
    }

    @Override
    public TypeInterface typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface type1, type2;
        type1 = expression1.typeCheck(table);
        type2 = expression2.typeCheck(table);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else throw new MyException("Second operand is not an integer");
        } else throw new MyException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return switch (op) {
            case 1 -> expression1.toString() + "+" + expression2.toString();
            case 2 -> expression1.toString() + "-" + expression2.toString();
            case 3 -> expression1.toString() + "*" + expression2.toString();
            case 4 -> expression1.toString() + '/' + expression2.toString();
            default -> "";
        };
    }
}
