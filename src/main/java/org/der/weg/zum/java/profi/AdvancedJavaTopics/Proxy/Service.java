package org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy;

import java.util.concurrent.TimeUnit;

public class Service implements IService{

    @Override
    public void doSomething() {
        System.out.println("do Something");
    }

    @Override
    public String calculateSomething(int value) {
        System.out.println("calculate something");

        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException ignored){

        }
        return "" + value;
    }
}
