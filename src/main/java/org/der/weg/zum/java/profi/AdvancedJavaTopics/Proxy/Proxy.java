package org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy;

import java.util.concurrent.TimeUnit;

public class Proxy implements IService{

    private final IService proxy;

    public Proxy(final IService proxy) {
        this.proxy = proxy;
    }

    @Override
    public void doSomething() {
        final long startTime = System.nanoTime();
        proxy.doSomething();
        printException("doSomething",System.nanoTime() - startTime);
    }

    private void printException(final String methodName,final long l) {
        System.out.println("Method call of " + methodName + " took: " + TimeUnit.NANOSECONDS.toMillis(l) + "ms");
    }

    @Override
    public String calculateSomething(int value) {
        return null;
    }
}
