package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.list.MyListInterface;
import model.expressions.ExpressionInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class PrintStatement implements StatementInterface {
    private final ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyListInterface<ValueInterface> out = state.getOut();
        out.add(expression.eval(state.getSymTable(), state.getHeap()));

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        expression.typeCheck(table);
        return table;
    }

    @Override
    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }
}
