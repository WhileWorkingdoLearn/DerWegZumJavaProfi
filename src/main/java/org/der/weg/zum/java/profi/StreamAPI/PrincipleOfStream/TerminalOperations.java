package org.der.weg.zum.java.profi.StreamAPI.PrincipleOfStream;

import java.util.List;

public interface TerminalOperations<T> {
    TerminalOperations<T> forEach();
    T[] toArray();
    List<T> collect();
}
