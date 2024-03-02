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

public class WhileStatement implements StatementInterface {
    private final ExpressionInterface expression;
    private final StatementInterface statement;

    public WhileStatement(ExpressionInterface expression, StatementInterface statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStackInterface<StatementInterface> stack = state.getExeStack();
        System.out.println("ajung in while");
        ValueInterface valExpr = expression.eval(state.getSymTable(), state.getHeap());
        if (!valExpr.getType().equals(new BoolType())) throw new MyException("Expression not of type bool");

        if (valExpr.equals(new BoolValue(true))) {
            stack.push(new WhileStatement(expression, statement));
            stack.push(statement);
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeExpression.equals(new BoolType())) {
            statement.typeCheck(table.deepCopy());
            return table;
        } else throw new MyException("Condition not of type bool");
    }

    @Override
    public String toString() {
        return "while(" + this.expression.toString() + ") { " + this.statement.toString() + " }";
    }
}
