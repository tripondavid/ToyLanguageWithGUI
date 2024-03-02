package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.types.StringType;
import model.types.TypeInterface;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements StatementInterface {
    private final ExpressionInterface expression;

    public CloseReadFileStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        MyDictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        ValueInterface val = expression.eval(symTable, state.getHeap());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (!fileTable.existsKey(stringValue)) throw new MyException("File does not exist");

            try {
                BufferedReader reader = fileTable.get(stringValue);
                reader.close();
                fileTable.remove(stringValue);
            } catch (IOException e) {
                throw new MyException("File can't be closed " + e.getMessage());
            }
        } else {
            throw new MyException("CloseReadFile expression is not a string");
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new CloseReadFileStatement(expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeVal = expression.typeCheck(table);
        if (typeVal.equals(new StringType())) {
            return table;
        } else throw new MyException("Expression not of type String");
    }

    @Override
    public String toString() {
        return "closeReadFile(" + this.expression.toString() + ")";
    }
}
