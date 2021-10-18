package tp4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class DLListReverseTest extends DLListTestBase {
    @Test
    public void reverseFull() {
        appendNElements(dll,4);
        String first=dll.first();
        String last=dll.last();
        dll.reverse();
        assertEquals(first,dll.last());
        assertEquals(last,dll.first());
    }
    
    @Test void reverseEmpty() {
        dll.reverse(); // Just check it doesn't not crash
    }

    @Test void reverseSingle() {
        appendNElements(dll,1);
        dll.reverse(); // Just check it doesn't not crash
    }

    @Test
    public void reverseTwo() {
        DLList dll=new DLList();
        appendNElements(dll,2);
        String first=dll.first();
        String last=dll.last();
        dll.reverse();
        assertEquals(first,dll.last());
        assertEquals(last,dll.first());
    }
}
