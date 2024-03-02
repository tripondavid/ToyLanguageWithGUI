package model.statements;

import exception.MyException;
import model.ProgramState;
import model.adts.dictionary.MyDictionaryInterface;
import model.adts.heap.MyHeapInterface;
import model.expressions.ExpressionInterface;
import model.types.RefType;
import model.types.TypeInterface;
import model.values.RefValue;
import model.values.ValueInterface;

public class HeapWritingStatement implements StatementInterface {
    private final String varName;
    private final ExpressionInterface expression;

    public HeapWritingStatement(String varName, ExpressionInterface expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        MyHeapInterface<ValueInterface> heap = state.getHeap();

        if (!symTable.existsKey(varName)) throw new MyException("Variable " + this.varName + " is not defined in symbolTable!");

        ValueInterface tableVal = symTable.get(varName);
        if (!(tableVal.getType() instanceof RefType)) throw new MyException("The value from SymbolTable doesn't have the type RefType!");

        int refAddress = ((RefValue) tableVal).getAddress();
        if (!heap.exists(refAddress)) throw new MyException("Value does not exist on heap");

        ValueInterface valExpr = expression.eval(symTable, heap);
        if (!valExpr.getType().equals(heap.get(refAddress).getType())) throw new MyException("Expression not of variable type");

        heap.put(refAddress, valExpr);

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapWritingStatement(varName, expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeVar = table.get(varName);
        TypeInterface typeExpression = expression.typeCheck(table);

        if (typeVar instanceof RefType referenceType) {
            if (typeExpression.equals(referenceType.getInner())) {
                return table;
            } else throw new MyException("Not the same type on heap modification");
        } else throw new MyException("Variable not of reference type");
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.varName + ", " + this.expression.toString() + ")";
    }
}
