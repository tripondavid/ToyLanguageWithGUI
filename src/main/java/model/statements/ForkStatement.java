package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionary;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.stack.MyStack;
import model.adts.stack.MyStackInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

import java.util.Map;

public class ForkStatement implements StatementInterface {
    private final StatementInterface forkStatement;

    public ForkStatement(StatementInterface forkStatement) {
        this.forkStatement = forkStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStackInterface<StatementInterface> newStack = new MyStack<>();
        newStack.push(forkStatement);
        MyDictionaryInterface<String, ValueInterface> newSymTable;
        newSymTable = deepCopySymTable(state.getSymTable());

        ProgramState newProgramState = new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
        newProgramState.setID();

        return newProgramState;
    }

    private MyDictionaryInterface<String, ValueInterface> deepCopySymTable(MyDictionaryInterface<String, ValueInterface> oldSymTable) {
        MyDictionaryInterface<String, ValueInterface> newSymTable = new MyDictionary<>();

        for (Map.Entry<String, ValueInterface> elem : oldSymTable.getContent().entrySet()) {
            String newKey = elem.getKey();
            ValueInterface newVal = elem.getValue().deepCopy();
            newSymTable.add(newKey, newVal);
        }

        return newSymTable;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForkStatement(forkStatement.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        forkStatement.typeCheck(table.deepCopy());
        return table;
    }

    @Override
    public String toString() {
        return "fork(" + this.forkStatement.toString() + ")";
    }
}
