package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.expressions.RelationalExpression;
import model.types.TypeInterface;

public class SwitchStatement implements StatementInterface{
    private ExpressionInterface expression;
    private ExpressionInterface expression1;
    private StatementInterface statement1;
    private ExpressionInterface expression2;
    private StatementInterface statement2;
    private StatementInterface statement3;

    public SwitchStatement(ExpressionInterface expression, ExpressionInterface expression1, StatementInterface statement1, ExpressionInterface expression2, StatementInterface statement2, StatementInterface statement3) {
        this.expression = expression;
        this.expression1 = expression1;
        this.statement1 = statement1;
        this.expression2 = expression2;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StatementInterface finalStatement = new IfStatement(new RelationalExpression(expression, expression1, "=="),
                statement1, new IfStatement(new RelationalExpression(expression, expression2, "=="), statement2, statement3));
        state.getExeStack().push(finalStatement);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new SwitchStatement(expression.deepCopy(), expression1.deepCopy(), statement1.deepCopy(), expression2.deepCopy(), statement2.deepCopy(), statement3.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        return table;
    }
}
