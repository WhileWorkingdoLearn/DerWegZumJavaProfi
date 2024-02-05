package org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection;

public interface Item<T> {

    Item<T> next();

    void setNext(Item<T> item);

    Item<T> prev();

    void setPrev(Item<T> item);
    T getData();
}
