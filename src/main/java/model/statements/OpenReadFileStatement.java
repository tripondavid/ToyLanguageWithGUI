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
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements StatementInterface {
    private final ExpressionInterface expression;

    public OpenReadFileStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        MyDictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        ValueInterface val = expression.eval(symTable, state.getHeap());

        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (fileTable.existsKey(stringValue)) throw new MyException("File is already opened");

            try {
                BufferedReader reader = new BufferedReader(new FileReader(stringValue.getValue()));
                fileTable.add(stringValue, reader);
            } catch (IOException e) {
                throw new MyException("File cannot be opened " + e.getMessage());
            }
        } else {
            throw new MyException("OpenReadFile expression is not a string");
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new OpenReadFileStatement(expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeExpression.equals(new StringType())) {
            return table;
        } throw new MyException("Expression not of type String");
    }

    @Override
    public String toString() {
        return "open(" + this.expression.toString() + ")";
    }
}
