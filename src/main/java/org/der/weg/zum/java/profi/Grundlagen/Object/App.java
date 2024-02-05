package org.der.weg.zum.java.profi.Grundlagen.Object;

public class App {
    public static void main(String[] args) {
        final BaseClass x = new BaseClass();
        final SubClass y = new SubClass();

        System.out.println("x is-a Baseclass : " + (y instanceof BaseClass));
        System.out.println("y is-a Baseclass : " + (x instanceof SubClass));

        System.out.println("getClass():  : " + (x.getClass() == y.getClass()));
    }
}
