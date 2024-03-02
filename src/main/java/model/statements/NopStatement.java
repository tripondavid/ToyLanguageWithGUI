package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.TypeInterface;

public class NopStatement implements StatementInterface {

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NopStatement();
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table;
    }

    @Override
    public String toString() {
        return "\n";
    }
}
