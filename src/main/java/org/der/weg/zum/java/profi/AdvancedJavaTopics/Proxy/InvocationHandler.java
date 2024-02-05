package org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class InvocationHandler implements java.lang.reflect.InvocationHandler {
    private final IService service;

    public InvocationHandler(IService service) {
        this.service = service;
    }


    @Override
    public Object invoke(final Object o,final Method method,final Object[] objects) throws Throwable {
        final long startTime = System.nanoTime();
        Object result = null;

        try{
            result = method.invoke(service,objects);
        } catch (InvocationTargetException ite){
            throw ite.getTargetException();
        }
        printException("calculateSomething",System.nanoTime() - startTime);

        return result;
    }


    private void printException(final String methodName,final long l) {
        System.out.println("Method call of " + methodName + " took: " + TimeUnit.NANOSECONDS.toMillis(l) + "ms");
    }
}
