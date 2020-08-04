package edu.uncc.cci.is.map_colouring.utility;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] values;
    private int counter;

    public ArrayIterator(T[] values) {
        this.values = values;
        counter = 0;
    }

    public boolean hasNext() {
        return counter < values.length;
    }

    public T next() {
        return values[counter++];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}