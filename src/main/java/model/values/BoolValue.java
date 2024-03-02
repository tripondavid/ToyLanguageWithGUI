package model.values;

import model.types.BoolType;
import model.types.TypeInterface;

public class BoolValue implements ValueInterface {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new BoolValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BoolValue)) return false;
        return this.value == ((BoolValue) obj).value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
