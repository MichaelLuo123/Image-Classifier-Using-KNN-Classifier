import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class dHeapTest {
    @Test
    public void defaultConstructorTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        assertEquals(0, dheap1.size());
    }

    @Test
    public void capacityConstructorTest() {
        dHeap<Integer> dheap1 = new dHeap<>(5);
        assertEquals(0, dheap1.size());
    }

    @Test
    public void daryHeapConstructorTest() {
        dHeap<Integer> dheap1 = new dHeap<>(3, 5, true);
        assertEquals(0, dheap1.size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dHeap<Integer> dheap2 = new dHeap<>(0, 2, true);
        });
        dHeap<Integer> dheap2 = new dHeap<>(3, 5, false);
        assertEquals(0, dheap2.size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dHeap<Integer> dheap3 = new dHeap<>(0, 2, false);
        });
    }

    @Test
    public void sizeTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        assertEquals(0, dheap1.size());
        dheap1.add(5);
        assertEquals(1, dheap1.size());
        dheap1.remove();
        assertEquals(0, dheap1.size());
        dHeap<Integer> dheap2 = new dHeap<>(3, 5, false);
        assertEquals(0, dheap2.size());
        dheap2.add(5);
        assertEquals(1, dheap2.size());
        dheap2.remove();
        assertEquals(0, dheap2.size());
    }

    @Test
    public void addTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        dheap1.add(2);
        dheap1.add(4);
        dheap1.add(6);
        assertEquals(6, dheap1.remove());
        assertEquals(4, dheap1.remove());
        assertEquals(2, dheap1.remove());
        Assertions.assertThrows(NullPointerException.class, () -> {
            dHeap<Integer> dheap2 = new dHeap<>();
            dheap2.add(null);
        });
        dHeap<Integer> dheap2 = new dHeap<>(2, 10, false);
        dheap2.add(2);
        dheap2.add(4);
        dheap2.add(6);
        assertEquals(2, dheap2.remove());
        assertEquals(4, dheap2.remove());
        assertEquals(6, dheap2.remove());

        //test resize
        dHeap<Integer> dheap3 = new dHeap<>(2);
        dheap3.add(1);
        dheap3.add(2);
        dheap3.add(4);
        dheap3.add(2);
        assertEquals(4, dheap3.size());
    }

    @Test
    public void removeTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        dheap1.add(21);
        dheap1.add(4);
        assertEquals(21, dheap1.remove());
        dheap1.add(42);
        dheap1.add(200);
        assertEquals(200, dheap1.remove());
        assertEquals(42, dheap1.remove());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            dHeap<Integer> dheap2 = new dHeap<>();
            dheap2.remove();
        });

        dHeap<Integer> dheap2 = new dHeap<>(2, 10, false);
        dheap2.add(2000);
        dheap2.add(7);
        dheap2.add(200);
        assertEquals(7, dheap2.remove());
        assertEquals(200, dheap2.remove());
        dheap2.add(1);
        assertEquals(1, dheap2.remove());

        dHeap<Integer> dheap3 = new dHeap<>(2, 10, true);
        dheap3.add(10);
        dheap3.add(20);
        dheap3.add(15);
        dheap3.add(30);
        dheap3.add(25);

        while (dheap3.size() > 0) {
            System.out.println(dheap3.remove());
        }
        dheap3.add(10);
        dheap3.add(20);
        dheap3.add(15);
        dheap3.add(30);
        dheap3.add(25);
        assertEquals(30, dheap3.element());

        dHeap<Integer> dheap4 = new dHeap<>(2, 10, true);
        dheap4.add(1000);
        dheap4.add(999);
        dheap4.add(1000);
        dheap4.add(998);
        dheap4.add(999);
        dheap4.add(1001);
        assertEquals(1001, dheap4.element());
        dheap4.remove();
        assertEquals(1000, dheap4.element());
        dheap4.remove();
        assertEquals(1000, dheap4.element());
        dheap4.remove();
        assertEquals(999, dheap4.element());
        dheap4.remove();
        assertEquals(999, dheap4.element());
        dheap4.remove();
        assertEquals(998, dheap4.element());

        dHeap<Integer> dheap5 = new dHeap<>(2, 10, false);
        dheap5.add(0);
        dheap5.add(1);
        dheap5.add(1);
        dheap5.add(2);
        dheap5.add(2);
        dheap5.add(2);
        assertEquals(0, dheap5.element());
        dheap5.remove();
        assertEquals(1, dheap5.element());
        dheap5.remove();
        assertEquals(1, dheap5.element());
        dheap5.remove();
        assertEquals(2, dheap5.element());
        dheap5.remove();
        assertEquals(2, dheap5.element());
        dheap5.remove();
        assertEquals(2, dheap5.element());
        dheap5.remove();
        assertEquals(0, dheap5.size());
    }
        @Test
    public void clearTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        dheap1.add(21);
        dheap1.add(4);
        assertEquals(2, dheap1.size());
        dheap1.clear();
        assertEquals(0, dheap1.size());
        dheap1.add(2);
        assertEquals(2, dheap1.remove());

        dHeap<Integer> dheap2 = new dHeap<>(2, 10, false);
        dheap2.add(21);
        dheap2.add(4);
        assertEquals(2, dheap2.size());
        dheap2.clear();
        assertEquals(0, dheap2.size());
        dheap2.add(2);
        dheap2.add(5);
        dheap2.add(1);
        dheap2.add(3);
        dheap2.add(2);
        assertEquals(1, dheap2.remove());
        assertEquals(2, dheap2.element());
        assertEquals(2, dheap2.remove());
        assertEquals(2, dheap2.element());

        dHeap<Integer> dheap3 = new dHeap<>(2, 10, false);
        dheap3.add(2505);
        dheap3.add(2506);
        dheap3.add(2507);
        dheap3.clear();
        dheap3.add(2505);
        dheap3.add(2506);
        dheap3.add(2507);
        assertEquals(2505, dheap3.element());
        assertEquals(2505, dheap3.remove());
        assertEquals(2506, dheap3.remove());
        assertEquals(2507, dheap3.element());
        assertEquals(2507, dheap3.remove());
    }

    @Test
    public void elementTest() {
        dHeap<Integer> dheap1 = new dHeap<>();
        dheap1.add(21);
        dheap1.add(200);
        dheap1.add(2);
        assertEquals(200, dheap1.element());
        dheap1.remove();
        assertEquals(21, dheap1.element());
        dheap1.remove();
        assertEquals(2, dheap1.element());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            dHeap<Integer> dheap2 = new dHeap<>();
            dheap2.element();
        });

        dHeap<Integer> dheap2 = new dHeap<>(2, 2, false);
        dheap2.add(1);
        dheap2.add(17);
        dheap2.add(8);
        assertEquals(1, dheap2.element());
        dheap2.remove();
        assertEquals(8, dheap2.element());
        dheap2.remove();
        assertEquals(17, dheap2.element());
    }
}

