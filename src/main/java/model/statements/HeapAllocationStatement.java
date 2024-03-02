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

public class HeapAllocationStatement implements StatementInterface {
    private final String varName;
    private final ExpressionInterface expression;

    public HeapAllocationStatement(String varName, ExpressionInterface expression) {
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

        ValueInterface newVal = expression.eval(symTable, heap);
        if (!newVal.getType().equals(((RefType) tableVal.getType()).getInner())) throw new MyException("New expression's value type is not the same as the variable");

        int address = heap.allocate(newVal);
        symTable.update(varName, new RefValue(address, newVal.getType()));

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapAllocationStatement(varName, expression.deepCopy());
    }

    @Override
    public MyDictionaryInterface<String, TypeInterface> typeCheck(MyDictionaryInterface<String, TypeInterface> table) throws MyException {
        TypeInterface typeVar = table.get(varName);
        TypeInterface typeExpression = expression.typeCheck(table);
        if (typeVar.equals(new RefType(typeExpression))) {
            return table;
        } else throw new MyException("Heap Allocation Statement: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "new(" + this.varName + ',' + this.expression.toString() + ')';
    }
}
