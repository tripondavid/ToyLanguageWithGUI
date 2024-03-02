package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.stack.MyStackInterface;
import model.expressions.ExpressionInterface;
import model.types.BoolType;
import model.types.TypeInterface;
import model.values.BoolValue;
import model.values.ValueInterface;

public class IfStatement implements StatementInterface {
    private final ExpressionInterface expression;
    private final StatementInterface thenStatement;
    private final StatementInterface elseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface thenStatement, StatementInterface elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        MyStackInterface<StatementInterface> stack = state.getExeStack();
        ValueInterface condition = expression.eval(symTable, state.getHeap());

        if (!condition.getType().equals(new BoolType())) throw new MyException("Conditional expression is not a boolean");

        if (condition.equals(new BoolValue(true)))
            stack.push(thenStatement);
        else
            stack.push(elseStatement);

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeExpression.equals(new BoolType())) {
            thenStatement.typeCheck(table.deepCopy());
            elseStatement.typeCheck(table.deepCopy());
            return table;
        } else throw new MyException("The condition of if has not the type bool");
    }

    @Override
    public String toString() {
        return "(if(" + expression.toString() + ") then(" + thenStatement.toString() + ") else(" + elseStatement.toString() + "))";
    }
}
