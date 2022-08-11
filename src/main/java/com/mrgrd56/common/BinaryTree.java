package com.mrgrd56.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

@Deprecated
public class BinaryTree<T> implements Collection<T>, Iterator<T> {
    private int size = 0;
    private final Node<T> root = new Node<>();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (var item : this) {
            if (Objects.equals(item, o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        var array = toArray();
        for (var i = 0; i < array.length; i++) {
            a[i] = (T1) array[i];
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root.value = null;
        root.left = null;
        root.right = null;
        size = 0;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node() {
        }

        Node(T value) {
            this.value = value;
        }

        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
