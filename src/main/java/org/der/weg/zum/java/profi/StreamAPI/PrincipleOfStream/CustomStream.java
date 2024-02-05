package org.der.weg.zum.java.profi.StreamAPI.PrincipleOfStream;

import java.util.List;
import java.util.function.Function;


public class CustomStream<T> implements IntermediateSteps<T>, TerminalOperations<T>{

    @Override
    public TerminalOperations<T> filter() {
        return null;
    }

    @Override
    public CustomStream<T> map(Function<?, ?> function) {
        return null;
    }

    @Override
    public CustomStream<T> flatMaps() {
        return null;
    }

    @Override
    public CustomStream<T> peek() {
        return null;
    }

    @Override
    public CustomStream<T> distinct() {
        return null;
    }

    @Override
    public CustomStream<T> sorted() {
        return null;
    }


    @Override
    public TerminalOperations<T> forEach() {
        return null;
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public List<T> collect() {
        return null;
    }
}
