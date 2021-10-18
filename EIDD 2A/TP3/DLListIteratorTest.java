package tp4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class DLListIteratorTest extends DLListTestBase {

    private void anyIterator(Iterator<String> iter,int count) {
        for (int i=1;i<=count;i++) {
            assertTrue(iter.hasNext(),String.format("Getting %s",element(i)));
            assertEquals(element(i), iter.next());
        }
        assertFalse(iter.hasNext(),"There should be nothing left");
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    public void plainIterator() {
        final int count=3;
        appendNElements(dll, count);
        anyIterator(dll.iterator(),count);
    }

//    @Test
//    public void staticIterator() {
//        appendNElements(dll, 3);
//        anyIterator(dll.staticIterator());
//    }
//
//    @Test
//    public void localIterator() {
//        appendNElements(dll, 3);
//        anyIterator(dll.localIterator());
//    }
//
//    @Test
//    public void anonymousIterator() {
//        appendNElements(dll, 3);
//        anyIterator(dll.anonymousIterator());
//    }

    @Test
    public void iteratorEmpty() {
        var iterator=dll.iterator();
        while (iterator.hasNext()) {
            fail("There should be no iteration");
            iterator.next();
        }
    }

    @Test
    public void independentIterators() {
        // Work two iterators side by side to demonstrate independence
        appendNElements(dll, 3);
        var i1 = dll.iterator();
        var i2 = dll.iterator();

        // Step i1 twice
        assertTrue(i1.hasNext());
        assertEquals(element(1), i1.next());
        assertTrue(i1.hasNext());
        assertEquals(element(2), i1.next());

        // Step i2 until end, should also start on 1 (and not 3)
        assertTrue(i2.hasNext());
        assertEquals(element(1), i2.next());
        assertTrue(i2.hasNext());
        assertEquals(element(2), i2.next());
        assertTrue(i2.hasNext());
        assertEquals(element(3), i2.next());
        assertFalse(i2.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            i2.next();
        });

        // I1 not finished yet, shoud still have one step to end.
        assertTrue(i1.hasNext());
        assertEquals(element(3), i1.next());
        assertFalse(i1.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            i1.next();
        });
    }

    @Test
    public void forLoop() {
        final int size = 5;
        appendNElements(dll, size);
        int i = 0;
        for (String is : dll) { // uses the iterator implicitly
            i += 1;
            assertEquals(is, element(i));
        }
        assertEquals(size,i);
    }

    @Test
    public void forLoopEmpty() {
        for (String is : dll) { // uses the iterator implicitly
            fail("There should be no iteration");
            assertEquals(is, "XYZ"); // never executed, just to suppress warning
        }
    }

    @Test
    public void removeHead() {
        appendNElements(dll, 3);
        var iterator=dll.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(dll.first(),element(2));
    }
    
    @Test
    public void removeTail() {
        appendNElements(dll, 3);
        var iterator=dll.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }; // go to last
        assertFalse(iterator.hasNext());
        iterator.remove(); // remove last
        assertEquals(dll.last(),element(2));
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void removeNextOnce() {
        appendNElements(dll, 5);
        var iterator=dll.iterator();
        iterator.next();
        iterator.next(); 
        iterator.remove(); // remove second element
        assertThrows(IllegalStateException.class, () -> {iterator.remove();});
    }
        
    @Test
    public void removeAll() {
        appendNElements(dll, 5);
        var iterator=dll.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertTrue(dll.isEmpty());
    }
    
    @Test
    public void removeRandom() {
        // fill list
        String[] elems = { "A1", "X2", "X3", "A4", "X5", "A6", "A7", "X8", "X9" };
        for (String s : elems) {
            dll.append(s);
        }
        // remove all "X":
        var iterator = dll.iterator();
        while (iterator.hasNext()) {
            String payload=iterator.next();
            System.out.printf("%s ",payload);
            if (payload.startsWith("X")) {
                iterator.remove();
            }
        }

        // there should be only 4 "A" remaining
        for (int i = 0; i < 4; i++) {
            assertTrue(dll.pop().startsWith("A"));
        }
        // Should be empty now
        assertTrue(dll.isEmpty());
    }
}
