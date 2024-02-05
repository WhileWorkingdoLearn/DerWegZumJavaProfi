package org.der.weg.zum.java.profi.CollectionFrameWork.SimpleCollection;

import java.util.Objects;

public class LinkedList<T> implements SimpleCollection<T>{


    private static class Node<T> implements Item<T> {

        private final T data;
        private Item<T> nLeft;
        private Item<T> nRight;

        private Node(T data) {
            this.data = data;
        }

        @Override
        public Item<T> next(){
            return this.nRight;
        }

        @Override
        public void setNext(Item<T> item) {
            this.nRight = item;
        }

        @Override
        public Item<T> prev(){
            return this.nLeft;
        }

        @Override
        public void setPrev(Item<T> item) {
            this.nLeft = item;
        }

        public T getData(){return data;}

    }

    private class Iterator implements CollectionIterator<T> {
        private Item<T> lastNode;

        private int index = 0;

        private Iterator(Item<T> node){this.lastNode = node;}
        private Iterator(Item<T> node,int index){this.lastNode = node;this.index = index;}


        @Override
        public boolean hasNext() {
            return this.lastNode.next() != null;
        }

        @Override
        public void next(){
            if(Objects.isNull(this.lastNode.next())) return;
            this.lastNode = this.lastNode.next();
            this.index++;
        }

        @Override
        public boolean hasPrev() {
            return this.lastNode.prev() != null;
        }

        @Override
        public void prev(){
            if(Objects.isNull(this.lastNode.prev())) return;
            this.lastNode = this.lastNode.prev();
            this.index = this.index > 0 ? this.index-- : 0;
        }

        @Override
        public T current() {
            return this.lastNode.getData();
        }


        @Override
        public int getIndex() {
            return this.index;
        }

        @Override
        public void reset() {
                this.index = 0;
        }

    }

    private Item<T> root;

    private Item<T> lastAdded;

    private int listSize = 0;

    private final CollectionIterator<T> iterator;



    public LinkedList(){
        this.iterator = new Iterator(root);
    }

    private interface NodeHandler<T> {
        boolean condition(int cnt, Item<T> node);
        void handle(Item<T> node);
    }

    private void traveler(NodeHandler<T> nodeHandler){
        var node = root;
        int cnt = 0;
            while (!Objects.isNull(node)){
               if (nodeHandler.condition(cnt,node))
                   break;
                node = node.next();
            }
            nodeHandler.handle(node);
    }

    @Override
    public void add(final T data) {
        if(Objects.isNull(data)) return;
        var newNode = new Node<T>(data);
        if(Objects.isNull(root)){
            this.root = newNode;
            this.lastAdded = root;
            return;
        }
        newNode.setPrev(this.lastAdded);
        this.lastAdded.setNext(newNode);
        this.lastAdded = newNode;
        this.listSize++;

    }

    @Override
    public void add(final T data,final int index) {
        if(Objects.isNull(data)) return;
        var newNode = new Node<>(data);
        var node = root;
        int cnt = 0;

        while (!Objects.isNull(node) && cnt < index){
            node = node.next();
            cnt++;
        }
        if(cnt == index) {
            newNode.setPrev(this.lastAdded);
            newNode.setNext(this.lastAdded.next());
            this.lastAdded.setNext(newNode);
            this.lastAdded = newNode;
            this.listSize++;
        }
    }


    @Override
    public void remove(final T data) {
        if(listSize == 0 || Objects.isNull(lastAdded)) return;
        if(Objects.isNull(data)) return;
        var node = root;
        while (!Objects.isNull(node)){
            if(node.getData().equals(data)){
                break;
            }
            node = node.next();
        }
        var prevNode = node.prev();
        var nextNode = node.next();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        listSize--;
    }

    @Override
    public void remove(final int index) {
        if(listSize == 0) return;
        if(index < 0 || index >= listSize) return;
        var node = root;
        int cnt = 0;
        while (!Objects.isNull(node) && cnt < index){
            node = node.next();
            cnt++;
        }
        if(cnt == index){
            var prevNode = node.prev();
            var nextNode = node.next();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        listSize--;
    }

    @Override
    public boolean contains(T data) {
        Item<T> node = root;
        while (node != null){
            if(node.getData().equals(data)){
                return true;
            }
            node = node.next();
        }
        return false;
    }

    @Override
    public T get(int index) {
        return this.iterator.current();
    }

    @Override
    public int size() {
        return this.listSize;
    }

    @Override
    public boolean isEmpty() {
        return this.listSize == 0;
    }

    @Override
    public void clear() {
            this.root = null;
            this.lastAdded  = null;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                ", listSize=" + listSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedList<?> that)) return false;
        return listSize == that.listSize && Objects.equals(root, that.root) && Objects.equals(lastAdded, that.lastAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, lastAdded, listSize);
    }

    public CollectionIterator<T> getIterator(){
        return (CollectionIterator<T>) new Iterator(root);
    }

    public static void main(String[] args) {
        var linkedList = new LinkedList<String>();

    }

}
