package org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection;

public interface CollectionIterator<T>
{
    boolean hasNext();
    void next();

    boolean hasPrev();

    void prev();
    T current();
    int  getIndex();
    void reset();
}
