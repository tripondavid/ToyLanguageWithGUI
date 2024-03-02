package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.stack.MyStackInterface;
import model.expressions.ExpressionInterface;
import model.expressions.RelationalExpression;
import model.expressions.VariableExpression;
import model.types.IntType;
import model.types.TypeInterface;

public class ForStatement implements StatementInterface{
    private final ExpressionInterface expression1;
    private final ExpressionInterface expression2;
    private final ExpressionInterface expression3;
    private final StatementInterface statement;

    public ForStatement(ExpressionInterface expression1, ExpressionInterface expression2, ExpressionInterface expression3, StatementInterface statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStackInterface<StatementInterface> stack = state.getExeStack();

        StatementInterface newStatement = //new CompoundStatement(
                //new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", this.expression1),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"), this.expression2, "<"),
                                new CompoundStatement(statement, new AssignStatement("v", this.expression3))
                        )
                );
        //);

        stack.push(newStatement);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForStatement(expression1.deepCopy(), expression2.deepCopy(), expression3.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        MyDictionaryInterface<String, TypeInterface> table1 = new VariableDeclarationStatement("v", new IntType()).typeCheck(table.deepCopy());
        TypeInterface vType = table1.get("v");
        TypeInterface expression1Type = this.expression1.typeCheck(table1);
        TypeInterface expression2Type = this.expression1.typeCheck(table1);
        TypeInterface expression3Type = this.expression1.typeCheck(table1);

        if(expression1Type.equals(new IntType())){
            if(expression2Type.equals(new IntType())){
                if(expression3Type.equals(new IntType())){
                    if(vType.equals(new IntType())){
                        return table;
                    } else throw new MyException("v from for statement is not int");
                } else throw new MyException("Expression3 from for statement is not int");
            } else throw new MyException("Expression2 from for statement is not int");
        } else throw new MyException("Expression1 from for statement is not int");
    }

    @Override
    public String toString() {
        return "for(v=" + expression1.toString() +
                ";v<" + expression2.toString() +
                ";v=" + expression3.toString() +
                ") {" + statement.toString() + " }";
    }
}
