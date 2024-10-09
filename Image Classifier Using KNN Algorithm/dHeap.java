/*
 * Name: Michael Luo
 * PID:  A18114314
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Michael Luo
 * @since 5/22/24
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    public dHeap() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        d = 2;
        nelems = 0;
        isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */

    public dHeap(int heapSize) {
        heap = (T[]) new Comparable[heapSize];
        d = 2;
        nelems = 0;
        isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) {
            throw new IllegalArgumentException();
        }
        heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems = 0;

    }

    /**
     * Returns number of elements in a heap
     * @return size of the heap
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Returns and removes the root element from the heap.
     * @return root element of the heap
     * @throws NoSuchElementException if heap is empty
     */

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        T removedElement = heap[0];
        heap[0] = heap[nelems - 1];
        heap[nelems - 1] = null;
        nelems--;
        trickleDown(0);
        return removedElement;
    }

    /**
     * Adds the given data to the heap.
     *
     * @param item The element to add.
     * @throws NullPointerException if data is null
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (nelems == heap.length) {
            resize();
        }
        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }

    /**
     * Clears all the elements in the heap.
     */
    @Override
    public void clear() {
        Arrays.fill(heap, null);
        nelems = 0;
    }

    /**
     * Returns the root element of the heap.
     * @return root element of the heap
     * @throws NoSuchElementException if heap is empty.
     */
    @Override
    public T element() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * Returns the parent index given the index of the child node
     * @param index of the child node
     * @return int of the parent index
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

    /**
     * Helper method for add method. Moves the index of the given node to the right spot of the
     * heap.
     * @param index of the node to bubble up
     */
    private void bubbleUp(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = parent(index);
        if (isMaxHeap) {
            if (heap[index].compareTo(heap[parentIndex]) > 0) {
                T temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                bubbleUp(parentIndex);
            }
        } else {
            if (heap[index].compareTo(heap[parentIndex]) < 0) {
                T temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                bubbleUp(parentIndex);
            }
        }
    }

    /**
     * Helper method to remove the root of a heap. Takes in an index and trickles it down to the
     * right spot.
     * @param index of the node that will get trickled down
     */

    private void trickleDown(int index) {
        int greatestChildIndex = greatestChildIndex(index, d);
        if (greatestChildIndex == -1) {
            return;
        }
        if (isMaxHeap) {
            if (heap[greatestChildIndex].compareTo(heap[index]) > 0) {
                T temp = heap[index];
                heap[index] = heap[greatestChildIndex];
                heap[greatestChildIndex] = temp;
                trickleDown(greatestChildIndex);
            }
        } else {
            if (heap[greatestChildIndex].compareTo(heap[index]) < 0) {
                T temp = heap[index];
                heap[index] = heap[greatestChildIndex];
                heap[greatestChildIndex] = temp;
                trickleDown(greatestChildIndex);
            }
        }
    }

    /**
     * helper Method to get the smallest or largest child index of a node given if
     * it is a min-heap or a max-heap
     * @param parentIndex index of the parent node
     * @param children amount of children that a heap can have
     * @return index of the child index that is the smallest or greatest
     */

    private int greatestChildIndex(int parentIndex, int children) {
        int bestChildIndex = -1;
        int firstChildIndex = parentIndex * d + 1;
        if (firstChildIndex >= nelems) {
            return bestChildIndex;
        }
        bestChildIndex = firstChildIndex;
        for (int i = 1; i < children; i++) {
            int currentChildIndex = firstChildIndex + i;
            if (currentChildIndex >= nelems) {
                break;
            }
            if (isMaxHeap) {
                if (heap[currentChildIndex].compareTo(heap[bestChildIndex]) > 0) {
                    bestChildIndex = currentChildIndex;
                }
            } else {
                if (heap[currentChildIndex].compareTo(heap[bestChildIndex]) < 0) {
                    bestChildIndex = currentChildIndex;
                }
            }
        }
        return bestChildIndex;
    }

    /**
     * Helper method to resize a heap if it is full and another element gets added.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] resizedHeap = (T[]) new Comparable[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            resizedHeap[i] = heap[i];
        }
        heap = resizedHeap;
    }

}
