package model.types;

import model.values.IntValue;
import model.values.ValueInterface;

public class IntType implements TypeInterface {
    @Override
    public ValueInterface getDefault() {
        return new IntValue(0);
    }

    @Override
    public TypeInterface deepCopy() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
