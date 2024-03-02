package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.expressions.ExpressionInterface;
import model.types.IntType;
import model.types.StringType;
import model.types.TypeInterface;
import model.values.IntValue;
import model.values.StringValue;
import model.values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements StatementInterface {
    private final ExpressionInterface expression;
    private final String varName;

    public ReadFileStatement(ExpressionInterface expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        MyDictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        if (!symTable.existsKey(varName)) throw new MyException("varName " + this.varName + " is not defined in symbol table");

        ValueInterface val = symTable.get(varName);
        if (!val.getType().equals(new IntType())) throw new MyException("ReadFile value is not an integer");

        ValueInterface valExpr = expression.eval(symTable, state.getHeap());
        if (!valExpr.getType().equals(new StringType())) throw new MyException("ReadFile expression's value type is not a string");

        StringValue stringValue = (StringValue) valExpr;
        if (fileTable.existsKey(stringValue)) {
            try {
                BufferedReader reader = fileTable.get(stringValue);
                String line = reader.readLine();
                IntValue intValue;
                if (line == null) {
                    intValue = new IntValue(0);
                } else {
                    intValue = new IntValue(Integer.parseInt(line));
                }
                symTable.update(varName, intValue);
            } catch (IOException e) {
                throw new MyException("Can't read from file");
            }
        }
        else throw new MyException("ReadFile expression's string value is not defined in file table");

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), varName);
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeVar = table.get(varName);
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeExpression.equals(new StringType())) {
            if (typeVar.equals(new IntType()))
                return table;
            else throw new MyException("Variable not of type int");
        } else throw new MyException("Expression not of type String");
    }

    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.varName + ")";
    }
}
