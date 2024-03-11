package com.dxtr;

/**
 * MyArrayList
 */
public class MyArrayList<E> {

    private int size;
    private int capacity;
    private E[] arr;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.arr = (E[]) new Object[this.capacity];
    }

    public MyArrayList() {
        this(10);

    }

    public void add(E e) {
        if (this.capacity <= this.size) {
            this.capacity = this.capacity + this.capacity / 2;
            E[] temp = (E[]) new Object[this.capacity];
            for (int i = 0; i < this.size - 1; i++) {
                temp[i] = this.arr[i];
            }
            this.arr = temp;
        }

        this.arr[this.size] = e;
        ++this.size;
    }

    public E set(int pos, E e) {
        if (pos >= this.size && pos < 0) {
            throw new IndexOutOfBoundsException();
        }

        E temp = this.arr[pos];
        this.arr[pos] = e;
        return temp;
    }

    public E get(int idx) {
        return (E) arr[idx];
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

}
