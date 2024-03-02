package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class VariableDeclarationStatement implements StatementInterface {
    private final String name;
    private final TypeInterface type;

    public VariableDeclarationStatement(String name, TypeInterface type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();

        if (symTable.existsKey(name))
            throw new MyException("Variable is already declared");

        symTable.add(name, type.getDefault());
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new VariableDeclarationStatement(name, type.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        table.add(name, type);
        return table;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
