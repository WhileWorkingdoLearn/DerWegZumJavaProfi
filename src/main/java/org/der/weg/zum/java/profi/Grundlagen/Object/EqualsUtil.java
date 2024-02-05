package org.der.weg.zum.java.profi.Grundlagen.Object;

public class EqualsUtil {
    public static boolean nullsafeEquals(final Object obj1,final Object obj2){
        return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
    }
}
