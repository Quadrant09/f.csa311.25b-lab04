package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntQueueTest {

    private IntQueue mQueue;

    @Before
    public void setUp() {
        mQueue = new ArrayIntQueue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(5);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, mQueue.size());
        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());
        mQueue.enqueue(2);
        assertEquals(2, mQueue.size());
        mQueue.dequeue();
        assertEquals(1, mQueue.size());
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        mQueue.enqueue(10);
        assertEquals(Integer.valueOf(10), mQueue.peek());
    }

    @Test
    public void testEnqueueAndDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertEquals(Integer.valueOf(3), mQueue.dequeue());
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testQueueResize() {
        for (int i = 0; i < 20; i++) {
            mQueue.enqueue(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
    }

    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.enqueue(3);
        mQueue.clear();

        assertTrue(mQueue.isEmpty());
        assertNull(mQueue.peek());
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testWrapAround() {
        for (int i = 0; i < 5; i++) {
            mQueue.enqueue(i);
        }
        
        for (int i = 0; i < 3; i++) {
            mQueue.dequeue();
        }
        
        for (int i = 5; i < 8; i++) {
            mQueue.enqueue(i);
        }
        
        assertEquals(Integer.valueOf(3), mQueue.dequeue());
        assertEquals(Integer.valueOf(4), mQueue.dequeue());
        assertEquals(Integer.valueOf(5), mQueue.dequeue());
    }
}
