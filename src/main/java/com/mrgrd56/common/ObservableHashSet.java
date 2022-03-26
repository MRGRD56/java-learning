package com.mrgrd56.common;

import java.util.HashSet;
import java.util.function.Consumer;

public class ObservableHashSet<T> extends HashSet<T> {
    private final Consumer<ObservableHashSet<T>> onCollectionChanged;

    public ObservableHashSet(Consumer<ObservableHashSet<T>> onCollectionChanged) {
        super(256);
        this.onCollectionChanged = onCollectionChanged;
    }

    @Override
    public boolean add(T t) {
        var result = super.add(t);
        onCollectionChanged.accept(this);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        var result = super.remove(o);
        onCollectionChanged.accept(this);
        return result;
    }
}
