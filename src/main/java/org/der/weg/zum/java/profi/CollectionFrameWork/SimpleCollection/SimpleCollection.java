package org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection;

public interface SimpleCollection<T> {
    void add(T data);
    void add(T data,int index);
    void remove(T data);
    void remove(int index);

    boolean contains(T data);
    T get(int index);
    int size();
    boolean isEmpty();
    void clear();
}
