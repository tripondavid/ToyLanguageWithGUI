package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.expressions.VariableExpression;
import model.types.BoolType;
import model.types.TypeInterface;

public class ConditionalAssignmentStatement implements StatementInterface{
    private String varName;
    private ExpressionInterface expression1;
    private ExpressionInterface expression2;
    private ExpressionInterface expression3;

    public ConditionalAssignmentStatement(String varName, ExpressionInterface expression1, ExpressionInterface expression2, ExpressionInterface expression3) {
        this.varName = varName;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StatementInterface finalStatement = new IfStatement(expression1, new AssignStatement(varName, expression2), new AssignStatement(varName, expression3));
        state.getExeStack().push(finalStatement);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ConditionalAssignmentStatement(varName, expression1.deepCopy(), expression2.deepCopy(), expression3.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface varType = new VariableExpression(varName).typeCheck(table.deepCopy());
        TypeInterface type1 = expression1.typeCheck(table.deepCopy());
        TypeInterface type2 = expression2.typeCheck(table.deepCopy());
        TypeInterface type3 = expression3.typeCheck(table.deepCopy());
        if (type1.equals(new BoolType()) && type2.equals(varType) && type3.equals(varType)) {
            return table;
        }
        throw new MyException("Invalid types(Conditional Assignment)");
    }
}
