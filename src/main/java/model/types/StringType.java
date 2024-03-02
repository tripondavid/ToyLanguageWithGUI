package model.types;

import model.values.StringValue;
import model.values.ValueInterface;

public class StringType implements TypeInterface {
    @Override
    public ValueInterface getDefault() {
        return new StringValue("EMPTY STRING");
    }

    @Override
    public TypeInterface deepCopy() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }
}
