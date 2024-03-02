package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ValueExpression;
import model.types.TypeInterface;
import model.values.IntValue;

public class AwaitLatchStatement implements StatementInterface{
    private String varName;

    public AwaitLatchStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IntValue varVal = (IntValue) state.getSymTable().get(varName);
        int foundIndex = varVal.getValue();

        if (state.getLatchTable().get(foundIndex) != 0)
            state.getExeStack().push(new AwaitLatchStatement(varName));

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AwaitLatchStatement(varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        if (!table.existsKey(varName))
            throw new MyException("Variable not in table(Await Latch)");
        return table;
    }

    @Override
    public String toString() {
        return "awaitLatch(" + varName + ")";
    }
}
