package com.mrgrd56.common;

import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

public class CustomLinkedList<T> implements Collection<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    public void addFirst(T t) {
        first = new Node<>(t, first);

        if (last == null) {
            last = first;
        }

        size++;
    }

    public void addLast(T t) {
        last = new Node<>(t, last);

        if (first == null) {
            first = last;
        }

        size++;
    }

    public T removeFirst() {
        if (first == null) {
            return null;
        }

        var removed = first;
        first = first.next;
        size--;
        return removed.value;
    }

    public T removeLast() {
        if (last == null) {
            return null;
        }

        var removed = last;
        last = last.next;
        size--;
        return removed.value;
    }

    public T getFirst() {
        return Node.value(first);
    }

    public T getLast() {
        return Node.value(last);
    }

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
            if (item == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(first);
    }

    private Iterator<Node<T>> nodeIterator() {
        return new NodeIterator<>(first);
    }

    private Iterable<Node<T>> nodeIterable() {
        return this::nodeIterator;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        var i = 0;
        for (var item : this) {
            a[i++] = (T1) item;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (var node : nodeIterable()) {
            if (node == o) {
            }
        }

        throw new NotImplementedException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
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

    }

    private static class Node<T> {
        private final T value;
        private final Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this(value, null);
        }

        private static <T> T value(Node<T> node) {
            return node == null ? null : node.value;
        }

        private static <T> Node<T> next(Node<T> node) {
            return node == null ? null : node.next;
        }
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private final NodeIterator<T> nodeIterator;

        public LinkedListIterator(Node<T> firstItem) {
            this.nodeIterator = new NodeIterator<>(firstItem);
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public T next() {
            return Node.value(nodeIterator.next());
        }
    }

    private static class NodeIterator<T> implements Iterator<Node<T>> {
        private Node<T> nextItem;

        public NodeIterator(Node<T> firstItem) {
            this.nextItem = firstItem;
        }

        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        @Override
        public Node<T> next() {
            if (nextItem == null) {
                return null;
            }

            var current = nextItem;
            nextItem = nextItem.next;
            return current;
        }
    }
}
