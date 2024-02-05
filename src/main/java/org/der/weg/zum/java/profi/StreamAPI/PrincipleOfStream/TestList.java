package org.der.weg.zum.java.profi.StreamAPI.PrincipleOfStream;

import org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection.ArrayList;

public class TestList<T> extends ArrayList<T> implements ICustomStream{
    public TestList(int size) {
        super(size);
    }

    @Override
    public CustomStream<T> customStream() {
        return new CustomStream<T>();
    }

    public static void main(String[] args) {
        TestList<String> testList = new TestList<>(100);
        testList.customStream().filter().collect();
    }
}
