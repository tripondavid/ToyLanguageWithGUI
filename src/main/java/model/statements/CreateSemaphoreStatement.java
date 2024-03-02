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

public class CreateSemaphoreStatement implements StatementInterface{
    private final String varName;
    private final ExpressionInterface expression;

    public CreateSemaphoreStatement(String varName, ExpressionInterface expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface expVal = expression.eval(state.getSymTable(), state.getHeap());
        int number = ((IntValue) expVal).getValue();
        int freeLocation = state.getSemaphoreTable().allocate(new Pair<>(number, new ArrayList<>()));

        if (state.getSymTable().existsKey(varName))
            state.getSymTable().update(varName, new IntValue(freeLocation));
        else throw new MyException("Variable doesn't exist(Semaphore)");
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CreateSemaphoreStatement(varName, expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface varType = table.get(varName);
        TypeInterface expType = expression.typeCheck(table.deepCopy());
        if (!varType.equals(new IntType()) || !expType.equals(new IntType()))
            throw new MyException("Invalid types(Semaphore)");
        return table;
    }

    @Override
    public String toString() {
        return "createSemaphore(" + varName + " " + expression + ")";
    }
}
