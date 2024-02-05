package org.der.weg.zum.java.profi.CollectionFrameWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StreamTesting {
    private static class Test {
        private String stringVal = "Test";
        private final int intVal = (int) (100 * Math.random());

        public String getStringVal() {
            return stringVal;
        }

        public void setStringVal(String stringVal) {
            this.stringVal = stringVal;
        }

        public int getIntVal() {
            return intVal;
        }
    }
    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test());
        list.add(new Test());
        list.add(new Test());
        Test[] tests = {new Test(),new Test(),new Test(),new Test()};

       List<Test> filteredList =  Arrays.stream(tests).toList().stream().filter(test -> test.intVal == 0).toList();


    }
}
