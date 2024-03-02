package model.values;

import model.types.RefType;
import model.types.TypeInterface;

public class RefValue implements ValueInterface {
    private final int address;
    private final TypeInterface locationType;

    public RefValue(int address, TypeInterface locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public TypeInterface getType() {
        return new RefType(locationType);
    }

    @Override
    public ValueInterface deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}
