package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionary;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.procedure.MyProcedureTableInterface;
import model.expressions.ExpressionInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

import java.util.ArrayList;
import java.util.List;

public class CallProcedureStatement implements StatementInterface{
    private String functionName;
    private List<ExpressionInterface> expressions;

    public CallProcedureStatement(String functionName, List<ExpressionInterface> expressions) {
        this.functionName = functionName;
        this.expressions = expressions;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyProcedureTableInterface procedureTable = state.getProcedureTable();
        if (!procedureTable.exists(functionName))
            throw new MyException("Function not found");

        List<String> variables = procedureTable.get(functionName).getFirst();
        StatementInterface statement = procedureTable.get(functionName).getSecond();

        MyDictionaryInterface<String, ValueInterface> newSymTable = new MyDictionary<>();
        for (String var : variables) {
            int index = variables.indexOf(var);
            newSymTable.add(var, expressions.get(index).eval(state.getSymTable(), state.getHeap()));
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CallProcedureStatement(functionName, new ArrayList<>(expressions));
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table;
    }
}
