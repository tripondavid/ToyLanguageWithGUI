package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.lock.MyLockTableInterface;
import model.adts.stack.MyStackInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

public class UnlockLockStatement implements StatementInterface{
    private final String varName;

    public UnlockLockStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStackInterface<StatementInterface> stack = state.getExeStack();
        MyDictionaryInterface<String, ValueInterface> symbolTable = state.getSymTable();
        MyLockTableInterface lockTable = state.getLockTable();

        if(symbolTable.existsKey(this.varName)) {
            ValueInterface foundIndex = symbolTable.get(this.varName);
            if(foundIndex.getType().equals(new IntType())){
                IntValue foundIndexInt = (IntValue) foundIndex;
                if(lockTable.exists(foundIndexInt.getValue())){
                    if(lockTable.get(foundIndexInt.getValue()).equals(state.getId()))
                        lockTable.update(foundIndexInt.getValue(), -1);
                }else throw new MyException("Lock does not exist");
            } else throw new MyException("Variable: " + this.varName + "has a value that is not int");
        } else throw new MyException("Variable is not in symbol table(lock statement)");

        state.setExeStack(stack);
        state.setSymTable(symbolTable);
        state.setLockTable(lockTable);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new UnlockLockStatement(this.varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table;
    }

    @Override
    public String toString() {
        return "unlock (" + this.varName + ")";
    }
}
