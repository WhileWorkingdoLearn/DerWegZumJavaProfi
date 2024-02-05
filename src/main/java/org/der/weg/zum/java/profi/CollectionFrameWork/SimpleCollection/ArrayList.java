package org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection;

public class ArrayList<T> implements SimpleCollection <T>{
    private Object[] data;
    private int index;

    private int MAX = 100;

    public ArrayList(int size,int max){

    }

    public ArrayList(int size){
        this.data = new Object[size];
        this.MAX = size;
    }


    private void shiftData(int index) {

    }

    private void copyArray() {
        Object[] temp = new Object[data.length + (data.length/2)];
        for (int i = 0; i < data.length; i++){
            temp[i] = data[i];
            data[i] = null;
        }
        data = temp;
    }

    @Override
    public void add(T data) {

    }

    @Override
    public void add(T data, int index) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
