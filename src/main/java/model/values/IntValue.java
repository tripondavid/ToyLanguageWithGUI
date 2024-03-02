package model.values;

import model.types.IntType;
import model.types.TypeInterface;

public class IntValue implements ValueInterface {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new IntType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new IntValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntValue)) return false;
        return this.value == ((IntValue) obj).getValue();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
