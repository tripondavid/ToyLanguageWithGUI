package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class AcquireSemaphoreStatement implements StatementInterface{
    private final String varName;

    public AcquireSemaphoreStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (!state.getSymTable().existsKey(varName))
            throw new MyException("Variable doesn't exist(Semaphore)");

        ValueInterface varVal = state.getSymTable().get(varName);
        int foundIndex = ((IntValue) varVal).getValue();

        if (!state.getSemaphoreTable().exists(foundIndex))
            throw new MyException("Found index not in Semaphore Table");

        int n1 = state.getSemaphoreTable().get(foundIndex).getFirst();
        int nl = state.getSemaphoreTable().get(foundIndex).getSecond().size();
        if (n1 > nl) {
            if (!state.getSemaphoreTable().get(foundIndex).getSecond().contains(state.getId()))
                state.getSemaphoreTable().get(foundIndex).getSecond().add(state.getId());
        } else
            state.getExeStack().push(new AcquireSemaphoreStatement(varName));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AcquireSemaphoreStatement(varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        if (!table.get(varName).equals(new IntType()))
            throw new MyException("Invalid type(Semaphore)");
        return table;
    }
}
