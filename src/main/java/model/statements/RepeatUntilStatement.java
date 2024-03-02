package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.stack.MyStackInterface;
import model.expressions.ExpressionInterface;
import model.types.BoolType;
import model.types.TypeInterface;

public class RepeatUntilStatement implements StatementInterface{
    private StatementInterface statement;
    private ExpressionInterface expression;

    public RepeatUntilStatement(StatementInterface statement, ExpressionInterface expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStackInterface<StatementInterface> stack = state.getExeStack();

        StatementInterface finalStatement = new CompoundStatement(statement, new WhileStatement(expression, statement));
        System.out.println("ajung aici");

        stack.push(finalStatement);
        state.setExeStack(stack);

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new RepeatUntilStatement(statement.deepCopy(), expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface expType = expression.typeCheck(table);
        statement.typeCheck(table.deepCopy());

        if (expType.equals(new BoolType()))
            return table;

        return null;
    }

    @Override
    public String toString() {
        return "repeat(" + statement + "until " + expression + ")";
    }
}
