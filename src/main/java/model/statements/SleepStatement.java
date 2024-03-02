package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.TypeInterface;

public class SleepStatement implements StatementInterface{
    private int number;

    public SleepStatement(int number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (number > 0) {
            state.getExeStack().push(new SleepStatement(number - 1));
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new SleepStatement(number);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table;
    }
}
