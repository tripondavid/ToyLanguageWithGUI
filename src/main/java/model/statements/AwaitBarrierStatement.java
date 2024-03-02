package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.barrier.Pair;
import model.adts.dictionary.MyDictionaryInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitBarrierStatement implements StatementInterface{
    private final String varName;
    private static final Lock lock = new ReentrantLock();

    public AwaitBarrierStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        //lock.lock();
        if (!state.getSymTable().existsKey(varName))
            throw new MyException("Variable doesn't exist in Symbol Table(Barrier)");

        ValueInterface val = state.getSymTable().get(varName);
        int foundIndex = ((IntValue) val).getValue();

        if(!state.getBarrierTable().exists(foundIndex))
            throw new MyException("Found index not in Barrier Table");

        Pair<Integer, List<Integer>> pair = state.getBarrierTable().get(foundIndex);
        int nl = pair.getSecond().size();
        int n1 = pair.getFirst();

        if (n1 > nl) {
            if (pair.getSecond().contains(state.getId())) {
                state.getExeStack().push(new AwaitBarrierStatement(varName));
            } else {
                pair.getSecond().add(state.getId());
            }
        }
        //lock.unlock();
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AwaitBarrierStatement(varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface varType = table.get(varName);
        if (varType.equals(new IntType()))
            return table;
        return null;
    }

    @Override
    public String toString() {
        return "awaitBarrier(" + varName + ")";
    }
}
