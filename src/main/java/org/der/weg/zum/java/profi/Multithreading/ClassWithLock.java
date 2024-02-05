package org.der.weg.zum.java.profi.Multithreading;

public class ClassWithLock {
    private final Object lock = new Object();

    public void testSync(){
        synchronized (lock){
            System.out.println("In Sensitive space");
        }
    }

    public void testSync2(){
        synchronized (lock){
            System.out.println("In Sensitive space 2");
        }
    }
}
