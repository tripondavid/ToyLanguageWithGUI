package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class NewLatchStatement implements StatementInterface{
    private final String varName;
    private final ExpressionInterface expression;

    public NewLatchStatement(String varName, ExpressionInterface expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface expVal = expression.eval(state.getSymTable(), state.getHeap());
        IntValue expInt = (IntValue) expVal;
        int number = expInt.getValue();

        int freeLocation = state.getLatchTable().allocate(number);

        if (state.getSymTable().existsKey(varName)) {
            state.getSymTable().update(varName, new IntValue(freeLocation));
        } else {
            state.getSymTable().add(varName, new IntValue(freeLocation));
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewLatchStatement(varName, expression);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface expType = expression.typeCheck(table.deepCopy());
        if (expType.equals(new IntType()))
            return table;
        return null;
    }

    @Override
    public String toString() {
        return "newLatch(" + varName + " " + expression + ")";
    }
}
