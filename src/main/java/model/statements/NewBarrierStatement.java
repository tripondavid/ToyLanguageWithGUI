package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.barrier.Pair;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.types.IntType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.ValueInterface;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements StatementInterface{
    private String varName;
    private ExpressionInterface expression;
    private static final Lock lock = new ReentrantLock();

    public NewBarrierStatement(String varName, ExpressionInterface expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        //lock.lock();
        ValueInterface expVal = expression.eval(state.getSymTable(), state.getHeap());
        IntValue expInt = (IntValue) expVal;
        int number = expInt.getValue();


        if (!state.getSymTable().existsKey(varName))
            throw new MyException("Variable doesn't exist in Symbol Table(Barrier)");

        int freeLocation = state.getBarrierTable().allocate(new Pair<>(number, new ArrayList<>()));
        state.getSymTable().update(varName, new IntValue(freeLocation));

        //lock.unlock();
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NewBarrierStatement(varName, expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface expType = expression.typeCheck(table.deepCopy());
        if (expType.equals(new IntType()))
            return table;
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier(" + varName + " " + expression + " )";
    }
}
