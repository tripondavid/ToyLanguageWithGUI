package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.types.TypeInterface;
import model.values.ValueInterface;

public class AssignStatement implements StatementInterface {
    private final String id;
    private final ExpressionInterface expression;

    public AssignStatement(String id, ExpressionInterface expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();

        if (symTable.existsKey(id)) {
            ValueInterface val = expression.eval(symTable, state.getHeap());
            TypeInterface typeID = symTable.get(id).getType();

            if (val.getType().equals(typeID))
                symTable.update(id, val);
            else throw new MyException("declared type of variable" + id + "and type of the assigned expression do not match");
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignStatement(id, expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeVar = table.get(id);
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeVar.equals(typeExpression))
            return table;
        else throw new MyException("Assignment: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }
}
