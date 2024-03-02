package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.TypeInterface;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws MyException;
    StatementInterface deepCopy();
    MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException;
}
