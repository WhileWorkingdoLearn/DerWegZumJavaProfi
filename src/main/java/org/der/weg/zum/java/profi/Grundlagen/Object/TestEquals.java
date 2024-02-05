package org.der.weg.zum.java.profi.Grundlagen.Object;

import java.util.Objects;

public class TestEquals {
    private String value = "Hello World";

    private String stringVal = "Test";

    private int intValue = 0;

    public TestEquals(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    private boolean compareAttributes(final TestEquals otherObj){
        return EqualsUtil.nullsafeEquals(this.value,otherObj.value) &&
        EqualsUtil.nullsafeEquals(this.stringVal,otherObj.stringVal) &&
        EqualsUtil.nullsafeEquals(this.intValue,otherObj.intValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEquals that = (TestEquals) o;
        return intValue == that.intValue && Objects.equals(value, that.value) && Objects.equals(stringVal, that.stringVal);
    }

    /*    @Override
    public boolean equals(Object obj) {
        // Null-Akteptanz
       if(Objects.isNull(obj)) return false;
        // Reflexivität
       if(obj == this) return true;
        // Symmetrie
        if (obj.getClass() == this.getClass()) return true;
        //Alternativ         // Transivität
        if (!(obj instanceof TestEquals)) return false;
        // Konsistent
        return compareAttributes((TestEquals) obj);
    }
    * */

    @Override
    public int hashCode() {
        return Objects.hash(value, stringVal, intValue);
    }
}
