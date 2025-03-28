package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 *
 * Fixed bugs:
 * 1. `isEmpty` logic was incorrect.
 * 2. `peek` could throw an exception on an empty queue.
 * 3. `ensureCapacity` had a faulty element-copying loop.
 *
 * @author Alex Lockwood
 * @author Ye Lu
 */
public class ArrayIntQueue implements IntQueue {

    private int[] elementData;
    private int head;
    private int size;
    private static final int INITIAL_SIZE = 10;

    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    public void clear() {
        Arrays.fill(elementData, 0);
        size = 0;
        head = 0;
    }

    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    public boolean enqueue(Integer value) {
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }
//------------------
    public boolean isEmpty() {
        //size >= 0
        return size == 0;
    }
//------------------
    public Integer peek() {
        //
        if (isEmpty()) {
            return null;
        }
        //
        return elementData[head];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[(head + i) % oldCapacity];
            }
            elementData = newData;
            head = 0;
        }
    }
}
