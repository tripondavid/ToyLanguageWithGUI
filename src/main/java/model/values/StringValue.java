package model.values;

import model.types.StringType;
import model.types.TypeInterface;

public class StringValue implements ValueInterface {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new StringValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StringValue)) return false;
        return this.value.equals(((StringValue) obj).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
