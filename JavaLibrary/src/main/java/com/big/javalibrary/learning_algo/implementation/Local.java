package com.big.javalibrary.learning_algo.implementation;

import java.util.Arrays;

public class Local<E> {
    private int size;
    private int position = 0;
    private E[] items;

    public Local(int size) {
        this.size = size;
        items = (E[])new Object[size];
    }

    public boolean addItem(E x) {
        if (position == size) {
            items = Arrays.copyOf(items, size * 2);
            size *= 2;
        }
        if(isAvailable(x))
            return false;

        items[position++] = x;
        return true;
    }

    public boolean removeItem(E y) {
        if (items.length == 0)
            return false;
        boolean isFound = false;
        for (int i = 0; i < items.length; i++) {
            if (isFound || items[i].equals(y)) {
                isFound = true;
                if (i + 1 < items.length) {
                    items[i] = items[i + 1];
                } else {
                    items[i] = null;
                }
            }
        }
        return isFound;
    }

    public boolean isAvailable(E x) {
        if (items.length == 0) {
            return false;
        }

        for (E item : items) {
            if (item != null && item.equals(x)) {
                return true;
            }
        }
        return false;
    }

    public E[] getItems() {
        return items;
    }

}
