package org.der.weg.zum.java.profi.FunctionalInterfacesForCollections;

import java.util.Comparator;
import java.util.Objects;

public class CollectionEquals implements Comparable<CollectionEquals> {
    private final String strValue;
    private final int intValue;

    public CollectionEquals(String strValue, int intValue) {
        this.strValue = strValue;
        this.intValue = intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public boolean equals(Object o) {
        if(Objects.isNull(o)) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        return compareTo((CollectionEquals) o) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strValue, intValue);
    }

    @Override
    public int compareTo(CollectionEquals collectionEquals) {

        return Comparator.comparing(CollectionEquals::getStrValue).thenComparing(CollectionEquals::getIntValue).compare(this,collectionEquals);
    }
}
