package org.der.weg.zum.java.profi.Grundlagen.Object;

import java.util.Objects;

public class SubClass extends BaseClass{
    public final String subvalue = "Child";
    @Override
    public boolean equals(Object otherObj){
        if(Objects.isNull(otherObj)) return false;
        if(this == otherObj) return true;
        if(this.getClass() != otherObj.getClass()) return false;
        if(!super.equals(otherObj)) return false;
        final SubClass subClass = (SubClass) otherObj;
        return compareAttributes(((SubClass) otherObj).subvalue);
    }

    private boolean compareAttributes(String subvalue) {
        return this.subvalue.equals(subvalue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subvalue);
    }
}
