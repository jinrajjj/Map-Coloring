package edu.uncc.cci.is.map_colouring.model;

import java.util.Iterator;
import java.util.List;

import edu.uncc.cci.is.map_colouring.utility.ArrayIterator;

public class Domain implements Iterable<Object> {
    private Object[] values;

    public Domain(List<?> values) {
        this.values = new Object[values.size()];
        for (int i = 0; i < values.size(); i++)
            this.values[i] = values.get(i);
    }

    public Domain(Object[] values) {
        this.values = new Object[values.length];
		System.arraycopy(values, 0, this.values, 0, values.length);
    }

    public int size() {
        return values.length;
    }

    public boolean isEmpty() {
        return values.length == 0;
    }

    @Override
    public Iterator<Object> iterator() {
        return new ArrayIterator<>(values);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Domain) {
            Domain d = (Domain) obj;
            if (d.size() != values.length)
                return false;
            else
                for (int i = 0; i < values.length; i++)
                    if (!values[i].equals(d.values[i]))
                        return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 9;
        int multiplier = 13;
        for (Object value : values) hash = hash * multiplier + value.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        boolean comma = false;
        for (Object value : values) {
            if (comma)
                result.append(", ");
            result.append(value.toString());
            comma = true;
        }
        result.append("}");
        return result.toString();
    }
}
