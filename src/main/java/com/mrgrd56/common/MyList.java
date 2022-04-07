package com.mrgrd56.common;

import java.util.*;

public class MyList<T> implements List<T>, Iterator<T> {
    private int iteratorIndex = 0;
    private int size;
    private T[] array;

    public MyList(T[] array) {
        this.array = array;
        size = array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public boolean contains(Object o) {
        for (var item : array) {
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
        return Arrays.stream(array).limit(size).toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        var isRemoved = false;
        for (var i = 0; i < size; i++) {
            var item = array[i];
            if (isRemoved) {
                array[i - 1] = array[i];
            } else if (Objects.equals(item, o)) {
                array[i] = null;
                size--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return c.stream().allMatch(this::add);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().allMatch(this::remove);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        return array[index] = element;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        var item = get(index);
        var isRemoved = remove(item);
        return isRemoved ? item : null;
    }

    @Override
    public int indexOf(Object o) {
        for (var i = 0; i < size; i++) {
            var item = get(i);
            if (Objects.equals(item, o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (var i = size - 1; i > 0; i--) {
            var item = get(i);
            if (Objects.equals(item, o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return size() > iteratorIndex;
    }

    @Override
    public T next() {
        return get(iteratorIndex++);
    }
}
