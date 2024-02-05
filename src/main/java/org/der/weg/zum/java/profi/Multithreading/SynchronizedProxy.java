package org.der.weg.zum.java.profi.Multithreading;

public class SynchronizedProxy {
    private final ThreadUnsafeClass original;

    public SynchronizedProxy(ThreadUnsafeClass original) {
        this.original = original;
    }

    public void doSomething(){
        synchronized (this){
            original.doSomeThing();
        }
    }
}
