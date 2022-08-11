package com.mrgrd56.common;

import java.util.Iterator;

public class StackImpl<T> implements Iterable<T>, Iterator<T> {
//    private int size = 0;
    private Node<T> top;
    private Node<T> iteratee;

    public void push(T value) {
        top = new Node<>(value, top);
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        var value = top.value;
        top = top.next;
        return value;
    }

//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        for (var item : this) {
//            if (Objects.equals(item, o)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

//    @Override
//    public Object[] toArray() {
//        var result = new Object[size];
//        var i = 0;
//        for (var item : this) {
//            result[i] = item;
//
//            i++;
//        }
//
//        return result;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public <T1> T1[] toArray(T1[] a) {
//        var array = toArray();
//        for (var i = 0; i < array.length; i++) {
//            a[i] = (T1) array[i];
//        }
//
//        return a;
//    }
//
//    @Override
//    public boolean add(T t) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends T> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }

    @Override
    public boolean hasNext() {
        return iteratee != null && iteratee.next != null;
    }

    @Override
    public T next() {
        iteratee = iteratee.next;
        return iteratee.value;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
