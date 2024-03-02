package model.types;

import model.values.BoolValue;
import model.values.ValueInterface;

public class BoolType implements TypeInterface {
    @Override
    public ValueInterface getDefault() {
        return new BoolValue(false);
    }

    @Override
    public TypeInterface deepCopy() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }
}
