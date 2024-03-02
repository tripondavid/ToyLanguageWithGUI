package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ValueExpression;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class CountDownLatchStatement implements StatementInterface{
    private final String varName;

    public CountDownLatchStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IntValue varVal = (IntValue) state.getSymTable().get(varName);
        int foundIndex = varVal.getValue();

        if (state.getLatchTable().exists(foundIndex) && state.getLatchTable().get(foundIndex) > 0) {
            state.getLatchTable().put(foundIndex, state.getLatchTable().get(foundIndex) - 1);
            StatementInterface outStatement = new PrintStatement(new ValueExpression(new IntValue(state.getId())));
            state.getExeStack().push(outStatement);
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CountDownLatchStatement(varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        if (!table.existsKey(varName))
            throw new MyException("Variable not in table(CountDownLatch)");

        return table;
    }
}
