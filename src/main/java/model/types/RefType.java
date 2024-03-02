package model.types;

import model.values.RefValue;
import model.values.ValueInterface;

public class RefType implements TypeInterface {
    private final TypeInterface inner;

    public RefType(TypeInterface inner) {
        this.inner = inner;
    }

    public TypeInterface getInner() {
        return inner;
    }

    @Override
    public ValueInterface getDefault() {
        return new RefValue(0, inner);
    }

    @Override
    public TypeInterface deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType)
            return this.inner.equals(((RefType) obj).inner);
        return false;
    }

    @Override
    public String toString() {
        return "Ref("+ this.inner.toString() + ")";
    }
}
