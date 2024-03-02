package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.lock.MyLockTableInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class NewLockStatement implements StatementInterface{
    private final String varName;

    public NewLockStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symbolTable = state.getSymTable();
        MyLockTableInterface lockTable = state.getLockTable();

        if(symbolTable.existsKey(this.varName)){
            ValueInterface varValue = symbolTable.get(this.varName);
            if(varValue.getType().equals(new IntType())) {
                int newFreeLocation = lockTable.allocate(-1);
                symbolTable.update(this.varName, new IntValue(newFreeLocation));
            } else throw new MyException("The value associated with the variable from newLock is not of type int");
        } else throw new MyException("the variable in new lock statement is not in symbol table.");

        state.setSymTable(symbolTable);
        state.setLockTable(lockTable);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewLockStatement(this.varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface varType = table.get(this.varName);
        if(varType.equals(new IntType())){
            return table;
        } else throw new MyException("Variable not of type int");
    }

    @Override
    public String toString(){
        return "newLock(" + this.varName + ")";
    }
}
